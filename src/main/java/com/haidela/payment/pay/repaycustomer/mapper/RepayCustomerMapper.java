package com.haidela.payment.pay.repaycustomer.mapper;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import org.springframework.stereotype.Repository;

/**
 * 代付消息接收情况MAPPER
 *
 * @author zhanglize
 * @create 2019/10/15
 */
@Repository
public interface RepayCustomerMapper {

    /**
     * 新增代付消息接收情况
     *
     * @param customer
     * @return
     */
    int add(RepayCustomer customer);

    /**
     * 根据交易流水号查询对应的代付交易对象的信息
     *
     * @param tranFlow  交易流水号
     * @return
     */
    RepayCustomer findByTranFlow(String tranFlow);
}