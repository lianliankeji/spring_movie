package com.lxg.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxg.springboot.mapper.UserMapper;
import com.lxg.springboot.model.Msg;
import com.lxg.springboot.model.Result;
import com.lxg.springboot.model.ResultUtil;
import com.lxg.springboot.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;

/**
 * Created by zhenghong
 * on 2017/4/25.
 */
@RestController
@RequestMapping("video/user/")
public class UserController extends BaseController {
	
	@Resource
    private UserMapper userMapper;

    @RequestMapping("save")
    public Msg save(User user) {
    
    	userMapper.save(user);
    	return ResultUtil.success();
    }    
    
    @RequestMapping("update")
    public Msg update(User user) {
    	
    	// 用户数据存储
    	userMapper.update(user);
    	return ResultUtil.success();
    }
    
    @RequestMapping("vip")
    public Msg vip(User user) throws ParseException {
    	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	// 用户数据存储
    	int i = userMapper.count(user);
    	Calendar c = Calendar.getInstance();
    	if(i==0){   		
    		c.setTime(new Date());
            c.add(Calendar.DATE,user.getTime()); 
            Date d = c.getTime();
    		user.setViptime(format.format(d));
    		userMapper.save(user);   		
    	}
    	else{
    		User temp = userMapper.query(user);
    		java.util.Date temptime=format.parse(temp.getViptime());  
    		c.setTime(temptime);
            c.add(Calendar.DATE,user.getTime()); 
            Date d = c.getTime();
    		user.setViptime(format.format(d));
    		userMapper.update(user); 
    	}
    		
    	return ResultUtil.success();
    }
    
    @RequestMapping("query")
    public Msg query(User user) {
    	
    	User userf = userMapper.query(user);
    	return ResultUtil.success(userf);
    }  
    
    @RequestMapping("saveboss")
    public Result saveboss(User user) {
    
    	userMapper.saveboss(user);
    	return new Result();
    }    
    
    @RequestMapping("updateboss")
    public Result updateboss(User user) {
    	
    	// 用户数据存储
    	userMapper.updateboss(user);
    	return new Result();
    }
    
    @RequestMapping("queryboss")
    public User queryboss(User user) {
    	
    	User userf = userMapper.queryboss(user);
    	return userf;  	
    }  
    
    @RequestMapping("login")
    public String login(User user) {    	
    	if(userMapper.countboss(user)>=1){
    		List<User> userf ;
    		userf = userMapper.querybynoboss(user);
    		JSONObject json = new JSONObject();
    		json.put("returncode", "00");
    		json.put("returnmsg","登陆成功");
    		json.put("storeid",userf);
    		return json.toJSONString();
    	}
    	else{
    		JSONObject jsonA = new JSONObject();
    		jsonA.put("returncode", "01");
    		jsonA.put("returnmsg","登陆失败");
    		return jsonA.toJSONString();
    	}
    }    
}
