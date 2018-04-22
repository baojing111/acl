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
        account.setId(1L);
        account.setCreateTime(new Date());
        account.setPassword("123456");
        account.setPhone("15000500050");
        account.setUsername("baojing");
        accountServiceImpl.saveAccount(account);
        Account account1 = accountServiceImpl.queryAccount(1L);
        return account1;
    }
}
