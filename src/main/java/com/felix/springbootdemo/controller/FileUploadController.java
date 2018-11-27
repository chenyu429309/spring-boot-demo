package com.felix.springbootdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class FileUploadController {
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "请选择一个文上传";
        }
        String filename = file.getOriginalFilename();
        long fileSize = file.getSize();
        System.out.println("文件名称" + filename + "-------文件大小" + fileSize);
        String path = "F:/test";
        File dest = new File(path + "/" + filename);
        if (!dest.getParentFile().exists()) {
            //父目录不存在就创建一个
            dest.getParentFile().mkdir();
        }
        //保存文件
        try {
            file.transferTo(dest);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    /**
     * 文件下载
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileDownload")
    public String download(HttpServletResponse response) {
        String fileName = "Cloud总结.docx";
        String filepath = "F:/test";
        File file = new File(filepath + "/" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件上传成功";
    }
}
