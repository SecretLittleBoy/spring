package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 索尔
 */
@Controller
public class UploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile) throws IOException {
        //指明文件保存的路径
        String path = "/Users/zeleishi/Documents/code/springmvc-demo1/out/upload/"+uploadFile.getOriginalFilename();
        File file = new File(path);
        //把上传的文件保存在指定路径的文件内
        uploadFile.transferTo(file);
        return "success";
    }
}
