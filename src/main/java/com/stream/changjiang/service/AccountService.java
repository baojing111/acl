package com.stream.changjiang.service;

import com.stream.changjiang.dao.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public void saveAccount(Account account);
    public Account queryAccount(Long id);
}
