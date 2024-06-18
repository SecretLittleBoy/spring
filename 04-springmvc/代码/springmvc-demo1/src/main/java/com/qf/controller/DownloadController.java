package com.qf.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: 索尔
 */
@Controller
public class DownloadController {

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
        //下载信息的配置
        ServletContext context = request.getServletContext();
        //localhost:8080/springmvc/images/img.jpeg
        String realPath = context.getRealPath("/images/img.jpeg");
        FileInputStream fis = new FileInputStream(realPath);
        //读取文件的字节数据 存入到bytes数组中
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        //封装响应消息（头、行、体），发送给客户端
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition","attachment;filename=img.jpeg");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,headers, HttpStatus.OK);
        return responseEntity;
    }

}
