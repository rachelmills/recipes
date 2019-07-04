package com.mills.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rachelmills on 29/6/19.
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        System.out.println("stop once ...");
        return "index";
    }
}
