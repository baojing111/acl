package com.stream.changjiang.controller;

import com.stream.changjiang.dao.entity.Account;
import com.stream.changjiang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    @Autowired
    private AccountService accountServiceImpl;
    @RequestMapping("/test")
    public Account getTest(){
        Account account = new Account();
        account.setId(111111L);
        account.setCreateTime(new Date());
        account.setPassword("123456");
        account.setPhone("15000000000");
        account.setUsername("test001");
        System.out.print(account);
        accountServiceImpl.saveAccount(account);
        Account account1 = accountServiceImpl.queryAccount(1L);
        return account1;
    }
}
