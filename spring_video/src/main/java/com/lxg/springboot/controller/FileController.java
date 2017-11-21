package com.lxg.springboot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lxg.springboot.model.Msg;
import com.lxg.springboot.model.ResultUtil;
import com.lxg.springboot.model.Video;
import com.lxg.springboot.mapper.VideoMapper;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Resource
    private VideoMapper videoMapper;
    //文件上传相关代码
    @RequestMapping(value = "video/upload")
    @ResponseBody
    public Msg upload(@RequestParam("test") MultipartFile file,String openid,String description,String director,String actor ) {
    	System.out.println("file1");
    	if (file.isEmpty()) {
        	return ResultUtil.error("999999", "文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("file2");
        logger.info("上传的后缀名为：" + suffixName);
        // 获取文件名
        Date date=new Date(); 
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss"); 
		String time=format.format(date);
        fileName = openid + time + suffixName;
        System.out.println("file3");
        logger.info("上传的文件名为：" + fileName);
        // 文件上传后的路径
        String filePath = "/usr/share/nginx/htmls/videos/";
        File dest = new File(filePath + fileName);
        
        Video video = new Video();
        int i = videoMapper.querymax();
    	video.setId(i+1);
    	video.setDescription(description);
    	video.setActor(actor);
    	video.setDirector(director);
    	video.setUploaduser(openid);
    	video.setUrl(fileName);
    	System.out.println("file4");
    	videoMapper.save(video);
    	System.out.println("file5");
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
        	System.out.println("file6");
            file.transferTo(dest);
            return ResultUtil.success(i); 
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.error("999999", "上传失败");
    }

}