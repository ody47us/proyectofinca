package com.fincas.app.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewsController {
    
    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("title", "Reto3");
        return "home";
    }
    @GetMapping("/farms")
    public String farmsView(Model model){
        model.addAttribute("title", "Farms");
        return "farms";
    }
    @GetMapping("/clients")
    public String clientsView(Model model){
        model.addAttribute("title", "Clients");
        return "clients";
    }
    @GetMapping("/messages")
    public String messagesView(Model model){
        model.addAttribute("title", "Messages");
        return "messages";
    }
    @GetMapping("/categories")
    public String categoriesView(Model model){
        model.addAttribute("title", "Categories");
        return "categories";
    }
}