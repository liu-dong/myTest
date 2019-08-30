package com.dong.web.common.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/common")
public class CommonController {

    @Resource
    private Environment env;

    /**
     * 上传附件到本地
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    public Map<String, Object> upload(MultipartFile file, String username) throws IllegalStateException, IOException {
        Map<String, Object> result = new HashMap<>();
        String path = env.getProperty("file.folder");
        File filePath = new File(path);
        // 如果文件夹不存在则创建
        if (!filePath.exists() && !filePath.isDirectory()) {
            if (!filePath.mkdir()) {
                result.put("message", "文件夹创建失败！");
            }
        }
        if (file != null) {
            // 取得当前上传文件的文件名称
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String myFileName = sdf.format(new Date())+file.getOriginalFilename();
            result.put("fileName",file.getOriginalFilename());
            result.put("newFileName",myFileName);
            // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
            if (!StringUtils.isEmpty(myFileName.trim())) {
                // 定义上传路径
                File localFile = new File(path+"/"+myFileName);
                // 检查文件夹是否存在不存在则新建
                if (!localFile.exists()) {
                    if (!localFile.mkdirs()) {
                        result.put("message", "文件夹创建失败！");
                    }
                }
                file.transferTo(localFile);
                result.put("message", "上传成功");
                result.put("success", true);
            }
        }
        return result;
    }
}
