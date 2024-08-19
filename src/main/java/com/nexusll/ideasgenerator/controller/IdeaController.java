package com.nexusll.ideasgenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IdeaController {
    @RequestMapping("")
    public String ideaGenerates(){
        return "ideaGenerates";
    }
}
