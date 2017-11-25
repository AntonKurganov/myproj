package ua.company.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Reel;
import ua.company.shop.domain.ReelType;
import ua.company.shop.security.CustomUser;
import ua.company.shop.security.UserService;
import ua.company.shop.services.ReelService;

import java.util.*;

@Controller
public class ReelController {

    private ReelService reelService;
    private UserService userService;

    @Autowired
    public ReelController(ReelService reelService, UserService userService) {
        this.reelService = reelService;
        this.userService = userService;
    }

    @RequestMapping("reels")
    public String allSpinnings(Model model, Integer pointer) {
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "reels";
    }

    @RequestMapping(value = "newReel")
    public String newReel(Model model, Integer pointer, @RequestParam String name,
                              @RequestParam double price,
                              @RequestParam String country,
                              @RequestParam int bearingNumber,
                              @RequestParam ReelType type,
                              @RequestParam String photoLink) {
        reelService.addReel(new Reel(new Good(name, price, country, photoLink), type, bearingNumber));
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "redirect:/reels";
    }

    @RequestMapping(value = "deleteReel")
    public String deleteSpinning(Model model, Integer pointer, @RequestParam int id) {
        List<Reel> list = reelService.list();
        for (Reel r : list){
            if (r.getId()==id)
                reelService.deleteReel(r);
        }
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "redirect:/reels";
    }

    @RequestMapping(value = "/reelsFromCountry")
    public String listByCountry(Model model, Integer pointer, @RequestParam String country){
        int act = pointer == null ? 1 : pointer;
        initializer(model, pointer == null ? 1 : pointer);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(reelService.list(country));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/reels";
    }

    @RequestMapping(value = "/reelsByPrice")
    public String listByPrice(Model model, Integer pointer, @RequestParam double min, @RequestParam double max){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(reelService.list(min, max));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/reels";
    }

    @RequestMapping(value = "reelsByBN")
    public String listByBN(Model model, Integer pointer, @RequestParam int bearingNumber){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(reelService.listByBN(bearingNumber));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/reels";
    }

    @RequestMapping(value = "reelsByType")
    public String listByBN(Model model, Integer pointer, @RequestParam String type){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(reelService.listByType(type));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/reels";
    }

    @RequestMapping(value = "/reel_{goodId}")
    public String moreDetails(@PathVariable(value = "goodId") long goodId, Model model){
        Reel reel = null;

        for (Good g : reelService.list()){
            if (goodId==g.getId()){
                reel = (Reel) g;
                break;
            }
        }

        Map<String, String> characteristics = ModelInitializer.map(reel);
        assert reel != null;
        characteristics.put("Bearing number", reel.getBearings() + " bearing" + (reel.getBearings()!=1 ? "s" : ""));
        characteristics.put("Type", reel.getType());

        model.addAttribute("characteristics", characteristics);
        model.addAttribute("good", reel);
        initializer(model, 1);

        return "{goodType}_{goodId}";
    }

    private void initializer(Model model, int pointer){

        List<Reel> reelList = reelService.list();
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(reelList);

        //blocks # 1-3: list of reels, list of countries and min&max prices -------
        if (reelList.size()!=0)
            ModelInitializer.initialize(model, (ArrayList<Good>) goodList, pointer);
        else
            model.addAttribute("goods", goodList);

        //block # 4 bearing number ------------------------------------------------
        Set<Integer> bearings = new TreeSet<>();
        for (Reel r : reelList){
            bearings.add(r.getBearings());
        }
        model.addAttribute("bearings", bearings);

        //block # 5 reel types ----------------------------------------------------
        Set<String> types = new HashSet<>();
        for (Reel r : reelList){
            types.add(r.getType());
        }
        model.addAttribute("types", types);

        //block # 6 user name -----------------------------------------------------
        CustomUser customUser = null;
        String role = null;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            customUser = userService.getUserByLogin(user.getUsername());
            role = customUser.getRole().toString();
        } catch (ClassCastException ignored){}
        model.addAttribute("name", ModelInitializer.userInitializer(customUser));
        model.addAttribute("role", role);

    }

}
