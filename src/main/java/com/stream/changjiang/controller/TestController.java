package com.stream.changjiang.controller;

import com.stream.changjiang.dao.entity.Account;
import com.stream.changjiang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.UUID;

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

    @RequestMapping("/firstFreemarker")
    public ModelAndView firstFreemarker(){
        String name = "I am a first Freemarker proceture !";
        System.out.print(name);
        ModelAndView mav = new ModelAndView();
        mav.addObject("name",name);
        mav.setViewName("/firstFreemarker");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    @RequestMapping("/addAccount")
    @ResponseBody
    public String addAccount(Account account){

        System.out.print("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

        account.setId(100002l);
        account.setCreateTime(new Date());
        account.setPhone("15000000000");
        accountServiceImpl.saveAccount(account);
        String result = "success";
        return result;
    }
}
