package lwy.study.spring.service;

import lwy.study.spring.mapper.AccountMapper;
import lwy.study.spring.vo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAccountService {

    private IAccountService accountService;
    private AccountMapper accountMapper;

    @Before
    public void init() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        accountService = ctx.getBean(IAccountService.class);
        accountMapper = ctx.getBean(AccountMapper.class);
        //每次测试前把两个账户余额复原，保证测试可重复
        accountMapper.updateMoneyById(1, 8000);
        accountMapper.updateMoneyById(2, 5000);
    }

    @Test
    public void testTransferOk() {
        System.out.println("===== 正常转账：张三(id=1) 给 李四(id=2) 转 500 元 =====");
        System.out.println("转账前：" + accountService.findById(1));
        System.out.println("转账前：" + accountService.findById(2));
        accountService.transfer(1, 2, 500);
        System.out.println("转账后：" + accountService.findById(1));
        System.out.println("转账后：" + accountService.findById(2));
    }

    @Test
    public void testTransferFail() {
        System.out.println("===== 余额不足转账：张三(id=1) 给 李四(id=2) 转 99999 元 =====");
        try {
            accountService.transfer(1, 2, 99999);
        } catch (RuntimeException e) {
            System.out.println("测试类捕获到异常：" + e.getMessage());
        }
    }
}
