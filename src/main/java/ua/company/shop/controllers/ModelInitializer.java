package ua.company.shop.controllers;

import org.springframework.ui.Model;
import ua.company.shop.domain.Good;
import ua.company.shop.domain.Line;
import ua.company.shop.domain.Reel;
import ua.company.shop.domain.Spinning;
import ua.company.shop.security.CustomUser;

import java.io.File;
import java.util.*;

class ModelInitializer {

    static void initialize(Model model, ArrayList<Good> goodList, int pointer){

        //block #1 list of all <Good> ---------------------------------------------
        for (Good good : goodList){
            if (!good.getPhotoLink().contains("http")){
                File f = new File("src/main/webapp/" + good.getPhotoLink());
                if (!f.exists() || good.getPhotoLink().equals("")){
                    good.setPhotoLink("resources/images/default.jpg");
                }
            }
        }

        //block # 2 list of countries ---------------------------------------------
        Set<String> countries = new TreeSet<>();
        for (Good g : goodList){
            countries.add(g.getCountry());
        }
        model.addAttribute("countries", countries);

        //block # 3 minimal and maximal prices ------------------------------------
        double max = 0;
        for (Good g : goodList){
            if (g.getPrice() > max)
                max = g.getPrice();
        }
        double min = max;
        for (Good g : goodList){
            if (g.getPrice() < min)
                min = g.getPrice();
        }
        model.addAttribute("min", min);
        model.addAttribute("max", max);

        onlyNine(goodList, pointer, model);
    }

    static String userInitializer(CustomUser customUser){
        try{
            if (!customUser.getLogin().equals(""))
                return customUser.getRole().toString() + " " + customUser.getLogin();
            else
                return "guest";
        } catch (Exception e){
            return "guest";
        }
    }

    static LinkedHashMap<String, String> map(Good good){
        Map<String, String> characteristics = new LinkedHashMap<>();
        characteristics.put("Name", good.getName());
        characteristics.put("Price", good.getPrice() + " ₴");
        characteristics.put("Country", good.getCountry());
        return (LinkedHashMap<String, String>) characteristics;
    }

    static void onlyNine(ArrayList<Good> goodList, int pointer, Model model){

        int listSize = goodList.size();//1
        int numberOfPages = listSize%9==0 ? listSize/9 : listSize/9+1;//1
        int[] pages = new int[numberOfPages < 9?numberOfPages : 9];//1
        if (pointer>1){
            for (int i = 0; i < (numberOfPages < 9?numberOfPages : 9); i++){
                pages[i] = pointer-1+i;
            }
        } else {
            for (int i = 0; i < (numberOfPages < 9?numberOfPages : 9); i++){
                pages[i] = i+1;
            }
        }
        try{
            if (pages[1]!=2 && pages[1]==numberOfPages){
                for (int i = 0; i < (numberOfPages < 9?numberOfPages : 9); i++){
                    pages[i] = pointer + i - 2;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ignored){
        }

        if (goodList.get(0) instanceof Spinning)
            model.addAttribute("spinnings", goodList);
        else if (goodList.get(0) instanceof Reel)
            model.addAttribute("reels", goodList);
        else if (goodList.get(0) instanceof Line)
            model.addAttribute("lines", goodList);
        else
            model.addAttribute("goods", goodList);

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("pages", pages);
        model.addAttribute("pointer", pointer);
        //returns list of goods that consists only from 9 elements required according to pointer
        goodList.removeIf(g -> (goodList.indexOf(g) < (pointer - 1) * 9 || goodList.indexOf(g) > pointer * 9 - 1));
    }

}
