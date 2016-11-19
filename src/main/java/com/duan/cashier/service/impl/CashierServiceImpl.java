package com.duan.cashier.service.impl;

import org.springframework.stereotype.Service;

import com.duan.cashier.service.CashierService;

@Service("cashierService")
public class CashierServiceImpl implements CashierService {

	@Override
	public void testJunit() {
		System.out.println("111111111111111111111");
	}

}
