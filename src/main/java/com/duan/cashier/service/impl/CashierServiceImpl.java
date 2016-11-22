package com.duan.cashier.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.duan.cashier.service.CashierService;



@Service("cashierService")
public class CashierServiceImpl implements CashierService {
	
	private static Logger log = LogManager.getLogger(CashierServiceImpl.class);
	
	@Override
	public void testJunit() {
		System.out.println("111111111111111111111");
	}

}
