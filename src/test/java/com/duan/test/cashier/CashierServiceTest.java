package com.duan.test.cashier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.duan.cashier.service.CashierService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CashierServiceTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void test() {
		CashierService cashierService = (CashierService) applicationContext.getBean("cashierService");
		cashierService.testJunit();
	}
}