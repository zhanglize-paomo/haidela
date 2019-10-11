package com.haidela.payment.pay.paycustomer.service;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.mapper.PayCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户交易流水信息业务
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Service
public class PayCustomerService {

    PayCustomerMapper mapper;
    @Autowired
    public void setMapper(PayCustomerMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 新增客户交易流水信息
     *
     * @param payCustomer
     * @return
     */
    public int add(PayCustomer payCustomer) {
        return mapper.add(payCustomer);
    }

    /**
     * 根据交易流水号修改该条交易的交易状态
     *
     * @param tranFlow  交易流水号
     * @param status   交易状态
     * @return
     */
    public int updateStatus(String tranFlow, String status) {
        return mapper.updateStatus(tranFlow,status);
    }
}
