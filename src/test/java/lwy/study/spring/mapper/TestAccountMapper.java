package lwy.study.spring.mapper;
import lwy.study.spring.vo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestAccountMapper {
    private AccountMapper accountMapper;
    @Before
    public void init() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        accountMapper = ctx.getBean(AccountMapper.class);
    }
    @Test
    public void testSelectById() {
        Account account = accountMapper.selectById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdateMoneyById() {
        //改完再改回去，保证测试可重复
        accountMapper.updateMoneyById(1, 888);
        System.out.println("改后：" + accountMapper.selectById(1));
        accountMapper.updateMoneyById(1, 1000);
        System.out.println("复原后：" + accountMapper.selectById(1));
    }
}
