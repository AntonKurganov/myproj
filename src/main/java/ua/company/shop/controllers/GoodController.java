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
import ua.company.shop.domain.GoodImplementation;
import ua.company.shop.domain.GoodType;
import ua.company.shop.security.CustomUser;
import ua.company.shop.security.UserService;
import ua.company.shop.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GoodController {

    private final GoodService goodService;
    private final UserService userService;

    @Autowired
    public GoodController(GoodService goodService, UserService userService) {
        this.goodService = goodService;
        this.userService = userService;
    }

    @RequestMapping(value = "{otherGoods}s")
    public String allGoods(Model model, Integer pointer, @PathVariable("otherGoods") String goodType){
        int act = pointer == null ? 1 : pointer;
        try {
            initializer(model, act, goodType);
            return "{otherGoods}s";
        } catch (Exception e){
            return "error";
        }
    }

    @RequestMapping(value = "new{goodType}")
    public String newSpinning(Model model, Integer pointer, @PathVariable("goodType") String goodType,
                              @RequestParam String name,
                              @RequestParam double price,
                              @RequestParam String country,
                              @RequestParam String photoLink) {
        goodService.addGood(new GoodImplementation(new Good(name, price, country, photoLink), GoodType.valueOf(goodType.toUpperCase())));
        int act = pointer == null ? 1 : pointer;
        initializer(model, act, goodType);
        model.addAttribute("otherGoods", goodType);
        return "redirect:/{otherGoods}s";
    }

    @RequestMapping(value = "delete{goodType}")
    public String deleteSpinning(Model model, Integer pointer, @RequestParam int id, @PathVariable String goodType) {
        List<GoodImplementation> list = goodService.list();
        for (Good s : list) {
            if (s.getId() == id){
                goodService.deleteGood((GoodImplementation) s);
            }
        }
        int act = pointer == null ? 1 : pointer;
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(list);
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        model.addAttribute("otherGoods", goodType);
        return "redirect:/{otherGoods}s";
    }

    @RequestMapping(value = "/{goodType}sfromCountry")
    public String listByCountry(Model model, @RequestParam String country, Integer pointer, @PathVariable String goodType){
        int act = pointer == null ? 1 : pointer;
        GoodType gt = GoodType.valueOf(goodType.toUpperCase());
        initializer(model,1, goodType);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(goodService.list(country, gt));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/{otherGoods}s";
    }

    @RequestMapping(value = "/{goodType}sByPrice")
    public String listByPrice(Model model, @RequestParam double min, @RequestParam double max, Integer pointer, @PathVariable String goodType){
        int act = pointer == null ? 1 : pointer;
        GoodType gt = GoodType.valueOf(goodType.toUpperCase());
        initializer(model,1, goodType);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(goodService.list(min, max, gt));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/{otherGoods}s";
    }

    @RequestMapping(value = "/good_{goodId}")
    public String moreDetails(@PathVariable(value = "goodId") long goodId, Model model){
        GoodImplementation good = null;
        for (GoodImplementation g : goodService.list()){
            if (goodId==g.getId()){
                good = g;
                break;
            }
        }

        Map<String, String> characteristics = ModelInitializer.map(good);

        model.addAttribute("characteristics", characteristics);
        model.addAttribute("good", good);
        userInitializer(model);

        return "{goodType}_{goodId}";
    }

    private void initializer(Model model, Integer pointer, String goodType){
        List<GoodImplementation> list = goodService.list(GoodType.valueOf(goodType.toUpperCase()));
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(list);
        model.addAttribute("goodType", GoodType.valueOf(goodType.toUpperCase()).parseType());

        //blocks # 1-3: list of spinnings, list of countries and min&max prices -------------
        if (list.size()!=0)
            ModelInitializer.initialize(model, (ArrayList<Good>) goodList, pointer);
        else
            model.addAttribute("goods", goodList);
        userInitializer(model);
    }

    private void userInitializer(Model model){
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
