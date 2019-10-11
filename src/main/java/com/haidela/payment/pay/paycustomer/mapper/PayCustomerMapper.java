package com.haidela.payment.pay.paycustomer.mapper;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import org.springframework.stereotype.Repository;

/**
 * @author zhanglize
 * @create 2019/10/10
 */
@Repository
public interface PayCustomerMapper {

    /**
     * 新增客户交易流水信息
     *
     * @param payCustomer
     * @return
     */
    PayCustomer add(PayCustomer payCustomer);
}
