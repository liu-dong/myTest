package com.dong.web.common.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "/goFileUpload")
    public String goFileUpload(HttpRequest request){
        String res = request.toString();
        System.out.println(res);
        return "common/fileUpload";
    }
}
