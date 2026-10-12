package lwy.study.spring.service.impl;

import lwy.study.spring.mapper.AccountMapper;
import lwy.study.spring.service.IAccountService;
import lwy.study.spring.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findById(int id) {
        return accountMapper.selectById(id);
    }

    @Override
    public void transfer(int fromId, int toId, float amount) {
        Account from = accountMapper.selectById(fromId);
        //转出账号不存在
        if (from == null) {
            throw new RuntimeException("转出账号不存在");
        }
        Account to = accountMapper.selectById(toId);
        //转入账号不存在
        if (to == null) {
            throw new RuntimeException("转入账号不存在");
        }
        //余额不足
        if (from.getMoney() < amount) {
            throw new RuntimeException("余额不足");
        }
        accountMapper.updateMoneyById(fromId, from.getMoney() - amount);
        System.out.println(from.getName() + " 转出 " + amount + " 元");
        // int i = 1 / 0;   //解开注释可演示异常通知
        accountMapper.updateMoneyById(toId, to.getMoney() + amount);
        System.out.println(to.getName() + " 收到 " + amount + " 元");
    }
}
