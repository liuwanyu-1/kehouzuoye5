# 课后作业5：account 转账 + AspectJ 方式的 AOP

Spring 整合 MyBatis 操作 account 表，实现按 id 修改余额与转账业务，并用 AspectJ 方式（XML `aop:aspect`）为转账方法添加五种通知。

## 结构
- `Account`：实体（id/name/money，lombok）
- `AccountMapper`：`updateMoneyById`（按 id 修改 money）、`selectById`
- `IAccountService` / `AccountServiceImpl`：`transfer(fromId, toId, amount)` 转账，含账户存在与余额校验
- `MyAdvice`：五种通知（带参前置 args 绑定转账实参 / 环绕 / 后置返回 / 后置 / 异常）
- `applicationContext.xml`：整合三件套 + `aop:config` 切面配置

## 测试数据
| id | name | money |
|----|------|-------|
| 1 | 张三 | 8000 |
| 2 | 李四 | 5000 |

`TestAccountMapper`：查询/改余额（改后复原，可重复执行）
`TestAccountService`：正常转账 500 元（五段通知链完整，余额 7500/5500）；余额不足转账 99999 元（异常通知触发，测试类捕获）
