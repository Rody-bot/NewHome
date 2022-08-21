package com.roy.common;

import com.roy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${newhome.path}")
    private String basePath;


    @PostMapping("/upload")
    public R upload(MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String format = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+format;

        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info(file.toString());
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new R(true,fileName,"文件上传成功");
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse httpServletResponse){
        String fileName = basePath+name;
        FileInputStream fis = null;
        ServletOutputStream outputStream = null;
        try {
             fis = new FileInputStream(new File(fileName));
             outputStream = httpServletResponse.getOutputStream();
            String[] split = name.split("\\.");
            httpServletResponse.setContentType("/image/"+split[split.length-1]);

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fis.read(bytes))!=-1){
                outputStream.write(bytes);
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
