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

    private String TRAN_FLOW = "DF_";

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

    /**
     * 根据交易流水号查询对应的代付交易对象的信息
     *
     * @param tranFlow  交易流水号
     * @return
     */
    public RepayCustomer findByTranFlow(String tranFlow) {
        return mapper.findByTranFlow(tranFlow);
    }

    /**
     *
     * 根据交易流水号修改代付交易的状态
     *
     * @param tranFlow  交易流水号
     * @param status    交易状态
     * @return
     */
    public int updateByStatus(String tranFlow, String status) {
        return mapper.updateByStatus(tranFlow,status);
    }
}
