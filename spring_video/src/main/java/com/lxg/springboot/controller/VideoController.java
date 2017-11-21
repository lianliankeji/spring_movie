package com.lxg.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxg.springboot.mapper.VideoMapper;
import com.lxg.springboot.mapper.FieldMapper;
import com.lxg.springboot.mapper.PurchaseMapper;
import com.lxg.springboot.mapper.UserMapper;
import com.lxg.springboot.model.Video;
import com.lxg.springboot.model.Videopage;
import com.lxg.springboot.model.Field;
import com.lxg.springboot.model.Msg;
import com.lxg.springboot.model.Order;
import com.lxg.springboot.model.Result;
import com.lxg.springboot.model.ResultUtil;
import com.lxg.springboot.model.Shop;
import com.lxg.springboot.model.Token;
import com.lxg.springboot.model.User;
import com.lxg.springboot.model.Purchase;
import com.lxg.springboot.service.HttpAPIService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("video/")
public class VideoController extends BaseController {
	
	@Resource
    private VideoMapper videoMapper;
	@Resource
    private FieldMapper fieldMapper;
	@Resource
    private PurchaseMapper purchaseMapper;
	@Resource
    private UserMapper userMapper;
	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.appSecret}")
	private String appSecret;
	@Resource
	private HttpAPIService httpAPIService;

    @RequestMapping("insert")
    public Msg save(Video video) { 
    	int i = videoMapper.querymax();
    	video.setId(i+1);   	
    	videoMapper.save(video);
    	i=i+1;
    	return ResultUtil.success(i);
    } 
   
    @RequestMapping("queryvideo")
    public Msg querybypage(Video video) {
    	List<Video> videoA;  
    	int temp = 0;
    	temp = video.getPage();
    	video.setPage(temp*video.getPagenum());
    	if (video.getUploaduser()==null || video.getUploaduser().isEmpty()){
    	videoA = videoMapper.query(video);
    	temp = videoMapper.querypage(video);}
    	else {
    	videoA = videoMapper.querybyad(video);
    	temp = videoMapper.querypagead(video);
    	}   
    	Videopage videopage = new Videopage();
    	videopage.setVideo(videoA);
    	videopage.setTotalpage(temp/video.getPagenum()+1);    	
    	return ResultUtil.success(videopage); 
    } 
    
    @RequestMapping("queryone")
    public Msg queryone(Video video) {
    	Video videotemp = new Video();
    	videotemp = videoMapper.queryone(video);	
    	return ResultUtil.success(videotemp); 
    } 
    
    @RequestMapping("buy")
    public Msg buy(Purchase purchase) {
    	purchaseMapper.save(purchase); 	
    	return ResultUtil.success();
    } 
    
    @RequestMapping("validate")
    public Msg validate(Purchase purchase) {
    	int i = purchaseMapper.querybuy(purchase);
    	if (i!=0){
    	return ResultUtil.success(1);
    	}
    	else{
    	return ResultUtil.success(0);	
    	}
    }
    
}
