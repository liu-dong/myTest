package com.dong.spring4.resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author LD
 * @date 2020/3/17 11:08
 */
public class ResourceLoadTest {

    public static void main(String[] args) throws IOException {
//        Resource resource = new ClassPathResource("jmsSpring/common.xml");
        Resource resource = new FileSystemResource("E:\\MyProject\\myTest\\target\\classes\\jmsSpring\\common.xml");
//        Resource resource = new ServletContextResource("/jmsSpring/common.xml");
        String filename = resource.getFilename();
        boolean exists = resource.exists();
        boolean open = resource.isOpen();
        URL url = resource.getURL();
        File file = resource.getFile();
        System.out.println(filename);
        System.out.println(exists);
        System.out.println(open);
        System.out.println(url.toString());
        System.out.println(file.toString());
    }
}
