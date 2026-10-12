package lwy.study.spring.mapper;

import lwy.study.spring.vo.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    int updateMoneyById(@Param("id") int id, @Param("money") float money);

    Account selectById(@Param("id") int id);
}
