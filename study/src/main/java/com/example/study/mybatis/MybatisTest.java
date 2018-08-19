package com.example.study.mybatis;

import com.example.study.mybatis.dao.entity.Account;
import com.example.study.mybatis.dao.mapper.AccountMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MybatisTest {
    public static void main(String[] args){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSessionFactory().openSession();
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = mapper.selectByPrimaryKey(1L);

            System.out.println(account);

        }catch (Exception e){
            System.out.println("执行失败");
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }

    }

    private static SqlSessionFactory getSqlSessionFactory() {
        //创建线程池
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.95.243.131:3306/stream?characterEncoding=utf8");
        dataSource.setUsername("fighting");
        dataSource.setPassword("albb!123");
        //构建数据库事物
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //构建数据库✅环境
        Environment evironment = new Environment("default",transactionFactory,dataSource);
        //构建Configuration对象
        Configuration configuration = new Configuration(evironment);
        //注册一个别名
        configuration.getTypeAliasRegistry().registerAlias("account", Account.class);
        //添加映射器
        configuration.addMapper(AccountMapper.class);
        /**
         * 以上是Mybatis配置信息的处理过程正常开发中主要通过xml配置的
         * 以下是创建sqlSessionFactory
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
