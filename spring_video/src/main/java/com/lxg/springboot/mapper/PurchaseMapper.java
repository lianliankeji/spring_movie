package com.lxg.springboot.mapper;

import java.util.List;

import com.lxg.springboot.model.Score;
import com.lxg.springboot.model.Purchase;

public interface PurchaseMapper {

	void save(Purchase purchase);
	
	int querybuy(Purchase purchase);

	
}