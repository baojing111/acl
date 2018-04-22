package com.stream.changjiang.service.impl;

import com.stream.changjiang.dao.entity.Account;
import com.stream.changjiang.dao.mapper.AccountMapper;
import com.stream.changjiang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void saveAccount(Account account) {
        accountMapper.insert(account);
    }

    @Override
    public Account queryAccount(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
