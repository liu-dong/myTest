package com.dong.web.uploadAndDownload.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/uploadAndDownload")
public class UploadAndDownloadController {

    @RequestMapping(value = "/fileUpload")
    public Map<String, Object> fileUpload(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success",true );
        return result;
    }
}
