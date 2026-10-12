package lwy.study.spring.service;

import lwy.study.spring.vo.Account;

public interface IAccountService {

    //转账：fromId 转出账号，toId 转入账号，amount 转账金额
    void transfer(int fromId, int toId, float amount);

    Account findById(int id);
}
