package ua.company.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Spinning;
import ua.company.shop.security.CustomUser;
import ua.company.shop.security.UserService;
import ua.company.shop.services.SpinningService;

import java.util.*;

@Controller
public class SpinningController {

    private SpinningService spinningService;
    private UserService userService;

    @Autowired
    public SpinningController(SpinningService spinningService, UserService userService) {
        this.spinningService = spinningService;
        this.userService = userService;
    }

    @RequestMapping(value = "spinnings")
    public String allSpinnings(Model model, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "spinnings";
    }

    @RequestMapping(value = "newSpinning", method = RequestMethod.POST)
    public String newSpinning(Model model, Integer pointer,  @RequestParam String name,
                              @RequestParam double price,
                              @RequestParam double length,
                              @RequestParam int weight,
                              @RequestParam String country,
                              @RequestParam String photoLink) {
        System.out.println("XTRFCYUIOIBVCXZXCFGHJK");
        try {
            System.out.println("OK");
            spinningService.addSpinning(new Spinning(new Good(name, price, country, photoLink), weight, length));
            int act = pointer == null ? 1 : pointer;
            initializer(model, act);
            return "redirect:/spinnings";
        }catch (NumberFormatException e){
            System.out.println("NE OK");
            int act = pointer == null ? 1 : pointer;
            initializer(model, act);
            return "/error";
        }
    }

    @RequestMapping(value = "deleteSpinning")
    public String deleteSpinning(Model model, Integer pointer, @RequestParam int id) {
        List<Spinning> list = spinningService.list();
        for (Good s : list){
            if (s.getId()==id)
                spinningService.deleteSpinning((Spinning) s);
        }
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(list);
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "redirect:/spinnings";
    }

    @RequestMapping(value = "/spinningsFromCountry")
    public String listByCountry(Model model, @RequestParam String country, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(spinningService.list(country));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/spinnings";
    }

    @RequestMapping(value = "/spinningsByPrice")
    public String listByPrice(Model model, @RequestParam double min, @RequestParam double max, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(spinningService.list(min, max));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/spinnings";
    }

    @RequestMapping(value = "/spinningsByLength")
    public String listByLength(Model model, @RequestParam double minLength, @RequestParam double maxLength, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(spinningService.listByLength(minLength, maxLength));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/spinnings";
    }

    @RequestMapping(value = "/spinning_{goodId}")
    public String moreDetails(@PathVariable(value = "goodId") long goodId, Model model, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        Spinning spinning = null;

        for (Good g : spinningService.list()){
            if (goodId==g.getId()){
                spinning = (Spinning) g;
                break;
            }
        }

        Map<String, String> characteristics = ModelInitializer.map(spinning);

        assert spinning != null;
        characteristics.put("Length", spinning.getLength() + " m");
        characteristics.put("Weight", spinning.getWeight() + " grams");

        model.addAttribute("characteristics", characteristics);
        model.addAttribute("good", spinning);

        return "{goodType}_{goodId}";
    }

    private void initializer(Model model, Integer pointer){

        List<Spinning> spinningList = spinningService.list();
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(spinningList);

        //blocks # 1-3: list of spinnings, list of countries and min&max prices -------------
        if (spinningList.size()!=0)
            ModelInitializer.initialize(model, (ArrayList<Good>) goodList, pointer);
        else
            model.addAttribute("goods", goodList);

        //block # 4 minimal length ----------------------------------------------------------
        double minLength = spinningList.get(0).getLength();
        for (int i = 1; i < spinningList.size(); i++){
            double temp = spinningList.get(i).getLength();
            if (minLength > temp)
                minLength = temp;
        }
        model.addAttribute("minLength", minLength);

        //block #5 user name ----------------------------------------------------------------
        CustomUser customUser = null;
        String role = null;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            customUser = userService.getUserByLogin(user.getUsername());
            role = customUser.getRole().toString();
        } catch (ClassCastException ignored){}
        model.addAttribute("name", ModelInitializer.userInitializer(customUser));
        model.addAttribute("role", role);
        model.addAttribute("goodType", "spinning");

    }

}
