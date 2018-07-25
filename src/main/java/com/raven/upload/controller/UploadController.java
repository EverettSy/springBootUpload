/**
 * Copyright (C), 2015-2018, 知点学派教育科技有限公司
 * FileName: UploadController
 * Author:   Administrator
 * Date:     2018/7/24 0024 14:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Raven
 * @create 2018/7/24 0024
 * @since 1.0.0
 */
@Controller
public class UploadController {
    /**
     * 初始化上传文件界面,跳转到index.jsp
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 上传文方法
     * @param file 前台上传的文件对象
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String upload(HttpServletRequest request, MultipartFile file) {

        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            /**
            //文件后缀名
            String suttix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            //上传文件名
            String filename = UUID.randomUUID() + suttix;
            //String filename = file.getOriginalFilename();
            //服务器端保存的文件对象
            File serverFile = new File(uploadDir + filename);
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);*/
            executeUpload(uploadDir,file);
        } catch (Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";

    }

    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传目录
     * @param file 上传对象
     * @throws Exception
     */
    private void executeUpload(String uploadDir,MultipartFile file) throws Exception {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        //上传文件名
        String filename = UUID.randomUUID()+suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir+filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public @ResponseBody String uploads(HttpServletRequest request,MultipartFile[] file){
        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            //遍历数组执行上传
            for (int i = 0; i <file.length ; i++) {
                if(file[i]!=null){
                    //调用上传方法
                    executeUpload(uploadDir,file[i]);
                }
            }
        } catch (Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
