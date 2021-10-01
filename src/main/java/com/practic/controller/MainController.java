package com.practic.controller;

import com.practic.repos.ModelRepo;
import com.practic.repos.SetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class MainController {


    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private SetRepo setRepo;


    @GetMapping("/ar/{set_id}")
    public String main(
            Model model,
            @PathVariable Long set_id
            ){
        model.addAttribute("models", modelRepo.findBySet_id(set_id));
        return "ar";
    }



    @GetMapping("/")
    public String greetings(
            Model model
    ){

        model.addAttribute("sets", setRepo.findAll());
        return "greetings";
    }
}
