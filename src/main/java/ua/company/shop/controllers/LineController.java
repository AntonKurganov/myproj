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
import ua.company.shop.domain.Line;
import ua.company.shop.security.CustomUser;
import ua.company.shop.security.UserService;
import ua.company.shop.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LineController {

    private final LineService lineService;
    private final UserService userService;

    @Autowired
    public LineController(LineService lineService, UserService userService) {
        this.lineService = lineService;
        this.userService = userService;
    }

    @RequestMapping("/lines")
    public String reels(Model model, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "lines";
    }

    @RequestMapping(value = "newLine")
    public String newLine(Model model, Integer pointer,  @RequestParam String name,
                              @RequestParam double price,
                              @RequestParam String country,
                              @RequestParam int lineLength,
                              @RequestParam double brStrength,
                              @RequestParam double width,
                              @RequestParam String photoLink) {
        lineService.addLine(new Line(new Good(name, price, country, photoLink), lineLength, brStrength, width));
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        return "redirect:/lines";
    }

    @RequestMapping(value = "deleteLine")
    public String deleteLine(Model model, Integer pointer, @RequestParam int id) {
        List<Line> list = lineService.list();
        for (Good s : list){
            if (s.getId()==id)
                lineService.deleteLine((Line) s);
        }
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(list);
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "redirect:/lines";
    }

    @RequestMapping(value = "/linesFromCountry")
    public String listByCountry(Model model, Integer pointer, @RequestParam String country){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineService.list(country));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/lines";
    }

    @RequestMapping(value = "/linesByPrice")
    public String listByPrice(Model model, Integer pointer, @RequestParam double min, @RequestParam double max){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineService.list(min, max));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/lines";
    }

    @RequestMapping(value = "/linesByLength")
    public String listByLength(Model model, Integer pointer, @RequestParam int min, @RequestParam int max){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineService.listByLength(min, max));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/lines";
    }

    @RequestMapping(value = "/linesByWidth")
    public String listByWidth(Model model, Integer pointer, @RequestParam double min, @RequestParam double max){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineService.listByWidth(min, max));
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/lines";
    }

    @RequestMapping(value = "/linesByStrength")
    public String listByStrength(Model model, Integer pointer, @RequestParam double min, @RequestParam double max){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineService.listByBrStrength(min, max));
        System.out.println("FROM " + min + " TO " + max + " THERE ARE " + goodList.size() + " LINES");
        ModelInitializer.onlyNine((ArrayList<Good>) goodList, act, model);
        return "/lines";
    }

    @RequestMapping(value = "/line_{goodId}")
    public String moreDetails(@PathVariable(value = "goodId") long goodId, Model model, Integer pointer){
        int act = pointer == null ? 1 : pointer;
        initializer(model, act);
        Line line = null;

        for (Good g : lineService.list()){
            if (goodId==g.getId()){
                line = (Line) g;
                break;
            }
        }

        Map<String, String> characteristics = ModelInitializer.map(line);

        assert line != null;
        characteristics.put("Length", line.getLineLength() + " m");
        characteristics.put("Width", line.getWidth() + "mm");
        characteristics.put("Breaking Strength", line.getBrStrength() + " kg");

        model.addAttribute("characteristics", characteristics);
        model.addAttribute("good", line);

        return "{goodType}_{goodId}";
    }

    private void initializer(Model model, Integer pointer) {

        List<Line> lineList = lineService.list();
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(lineList);

        //blocks # 1-3: list of spinnings, list of countries and min&max prices -------------
        if (lineList.size()!=0)
            ModelInitializer.initialize(model, (ArrayList<Good>) goodList, pointer);
        else
            model.addAttribute("goods", goodList);

        //block # 4 min and max length ----------------------------------------------------------
        int maxLength = 0;
        for (Line l : lineList) {
            if (l.getLineLength() > maxLength)
                maxLength = l.getLineLength();
        }
        int minLength = maxLength;
        for (Line l : lineList) {
            if (l.getLineLength() < minLength)
                minLength = l.getLineLength();
        }
        model.addAttribute("maxLength", maxLength);
        model.addAttribute("minLength", minLength);

        //block # 5 min and max width
        double maxWidth = 0;
        for (Line l : lineList) {
            if (l.getWidth() > maxWidth)
                maxWidth = l.getWidth();
        }
        double minWidth = maxWidth;
        for (Line l : lineList) {
            if (l.getWidth() < minWidth)
                minWidth = l.getWidth();
        }
        model.addAttribute("maxWidth", maxWidth);
        model.addAttribute("minWidth", minWidth);

        //block #6 min and max breaking strength
        double maxStrength = 0;
        for (Line l : lineList) {
            if (l.getBrStrength() > maxStrength)
                maxStrength = l.getBrStrength();
        }
        double minStrength = maxStrength;
        for (Line l : lineList) {
            if (l.getBrStrength() < minStrength)
                minStrength = l.getBrStrength();
        }
        model.addAttribute("maxStrength", maxStrength);
        model.addAttribute("minStrength", minStrength);

        //block #7 user name ----------------------------------------------------------------
        CustomUser customUser = null;
        String role = null;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            customUser = userService.getUserByLogin(user.getUsername());
            role = customUser.getRole().toString();
        } catch (ClassCastException ignored){}
        model.addAttribute("name", ModelInitializer.userInitializer(customUser));
        model.addAttribute("role", role);
        model.addAttribute("goodType", "line");

    }

}
