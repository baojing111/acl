package com.example.study.mybatis.dao.mapper;

import com.example.study.mybatis.dao.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Select("select * from changjiang_account where id = #{id}")
    Account selectByPrimaryKey(Long id);


}