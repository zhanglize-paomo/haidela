package com.haidela.payment.pay.repaycustomer.service;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.mapper.RepayCustomerMapper;
import com.haidela.payment.util.DateUtils;
import com.haidela.payment.util.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanglize
 * @create 2019/10/15
 */
@Service
public class RepayCustomerService {

    private String TRAN_FLOW = "DF";

    private RepayCustomerMapper mapper;

    @Autowired
    public void setMapper(RepayCustomerMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 新增代付消息接收情况
     *
     * @param customer
     * @return
     */
    public int add(RepayCustomer customer) {
        customer.setCreateTime(DateUtils.stringToDate());
        customer.setRepayTranFlow(TRAN_FLOW + customer.getTranFlow());
        customer.setId(new SnowflakeIdUtils().nextId());
        return mapper.add(customer);
    }
}
