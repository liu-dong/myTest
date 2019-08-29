package com.dong.web.common.controller;

import jdk.nashorn.internal.objects.Global;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@RequestMapping(value = "/common")
public class CommonController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

//    @RequestMapping(value = "{folder}")
//    @RequestMapping(value = "{folder}")
    @RequestMapping(value = "{page}/{folder}")
    public ModelAndView fileUpload(@PathVariable("folder") String folder, @PathVariable("page") String page){
//        return "WEB-INF/page/common/"+folder+".jsp";
//        return "WEB-INF/page/common/"+folder;
//        return "WEB-INF/page/"+page+"/"+folder+".jsp";
        ModelAndView view = new ModelAndView();
        view.setViewName(page+"/"+folder+".jsp");
        return view;
    }

    /**
     * 不同单位类型返回不同的页面 ,返回的是对应的html页面
     * @param folder
     * @param url
     * @return
     */
    @RequestMapping("{folder}/{url}.html")
    public ModelAndView pageForHtml(@PathVariable("folder") String folder, @PathVariable("url") String url){
        ModelAndView view=new ModelAndView();
        view.addObject("systemName","测试项目");
        view.addObject("showLoginTime", sdf.format(new Date()));//在页面显示登录时间
        view.setViewName(folder+"/" + url+".html");
        /*String re= (String)session.getAttribute(Global.);
        if(!StringUtils.isEmpty(re)){
            view.setViewName(folder+"/"+ re +"/"+ url+".html");
        }*/
        return view;
    }

    /*@RequestMapping("{type}/{folder}/{url}.html")
    public ModelAndView pageForHtmlNew(@PathVariable("type") String type,@PathVariable("folder") String folder,@PathVariable("url") String url,HttpServletRequest request){
        ModelAndView view=new ModelAndView();
        view.addObject("systemName",env.getProperty("systemName"));
        view.addObject("needVerificationCode",env.getProperty("needVerificationCode"));
        view.addObject("showLoginTime", DateUtils.formart(new Date(), "yyyy年MM月dd日 HH时mm分ss秒"));//在页面显示登录时间
        view.setViewName(type+"/"+folder+ "/" + url + ".html");
        String re= (String)request.getSession(false).getAttribute(Global.DIRECTORY);
        if(!StringUtils.isEmpty(re)){
            view.setViewName(type+"/"+folder+ "/" + re+"/"+url + ".html");
        }
        return view;
    }
    *//**
     * 不同单位类型返回不同的页面 ,返回的是对应的jsp页面
     * @param folder
     * @param url
     * @return
     *//*
    @RequestMapping("{folder}/{url}.xtml")
    public ModelAndView pageForXtml(@PathVariable("folder") String folder, @PathVariable("url") String url, HttpServletRequest request){
        ModelAndView view=new ModelAndView();
        view.addObject("systemName",env.getProperty("systemName"));
        view.addObject("needVerificationCode",env.getProperty("needVerificationCode"));
        view.addObject("showLoginTime", DateUtils.formart(new Date(), "yyyy年MM月dd日 HH时mm分ss秒"));//在页面显示登录时间
        view.setViewName(folder+"/" + url);
        String re= (String)request.getSession(false).getAttribute(Global.DIRECTORY);
        if(!StringUtils.isEmpty(re)){
            view.setViewName(folder+"/"+ re +"/"+ url);
        }
        return view;
    }*/
}
