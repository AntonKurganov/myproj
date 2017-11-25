package ua.company.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.shop.security.CustomUser;
import ua.company.shop.security.UserRole;
import ua.company.shop.security.UserService;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ProgController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String regOk(Model model){
        File folder = new File("src/main/webapp/resources/images/mainpage");
        File[] images = folder.listFiles();
        Map<String, String> map = new LinkedHashMap<>();
        assert images != null;
        for (File f : images){
            map.put("resources/images/mainpage/" + f.getName(), noNumbers(f.getName()));
        }
        model.addAttribute("map", map);
        initializer(model);
        return "index";
    }
    private String noNumbers(String with){
        char[] array = with.toCharArray();
        StringBuilder sb = new StringBuilder(with.length());
        for (char c : array){
            if (c =='.')
                break;
            if (!Character.isDigit(c))
                sb.append(c);
        }
        return sb.toString();
    }

    @RequestMapping(value = "login")
    public String login(Model model){
        initializer(model);
        return "login";
    }

    @RequestMapping(value = "newUser", method = RequestMethod.POST)
    public String update(   Model model,
                            @RequestParam String name,
                            @RequestParam String password,
                            @RequestParam String email,
                            @RequestParam String phone) {
        if (userService.existsByLogin(name)) {
            model.addAttribute("exists", true);
            return "register";
        }

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);

        CustomUser dbUser = new CustomUser(name, passHash, UserRole.USER, email, phone);
        userService.addUser(dbUser);
        initializer(model);
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        initializer(model);
        return "/register";
    }

//    @RequestMapping("/unauthorized")
//    public String unauthorized(Model model){
//        initializer(model);
//        return "unauthorized";
//    }

    @RequestMapping("/erroror")
    public String error(Model model) {
        return "erroror";
    }


    private void initializer(Model model){
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUser customUser = userService.getUserByLogin(user.getUsername());
            model.addAttribute("name", ModelInitializer.userInitializer(customUser));
        } catch (Exception e){
            model.addAttribute("name", "guest");
        }
    }


}