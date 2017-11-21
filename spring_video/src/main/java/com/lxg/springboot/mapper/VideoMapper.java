package com.lxg.springboot.mapper;

import java.util.List;

import com.lxg.springboot.model.Video;
import com.lxg.springboot.model.Field;

public interface VideoMapper {

	void save(Video video);
	
	Video queryone(Video video);
	
	List<Video> query(Video video);
	
	List<Video> querybyad(Video video);
	
	int querymax();
	
	int querypage(Video video);
	
	int querypagead(Video video);
	
}