package com.haidela.payment.pay.paycustomer.mapper;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import org.apache.ibatis.annotations.Param;
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
    int add(PayCustomer payCustomer);

    /**
     * 根据交易流水号修改该条交易的交易状态
     *
     * @param tranFlow  交易流水号
     * @param status   交易状态
     * @return
     */
    int updateStatus(@Param(value = "tranFlow") String tranFlow,@Param(value = "status") String status);
}
