package com.haidela.payment.pay.repaycustomer.mapper;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     *
     * 根据交易流水号修改代付交易的状态
     *
     * @param tranFlow  交易流水号
     * @param status    交易状态
     * @return
     */
    int updateByStatus(@Param(value = "tranFlow") String tranFlow, @Param(value = "status") String status);

    /**
     * 查询所有的代付账户信息情况
     *
     * @return
     */
    List<RepayCustomer> findByAll();

    /**
     * 根据商户号查询对应的代付信息
     *
     * @param merchantNo
     * @return
     */
    List<RepayCustomer> findByMerchantNo(String merchantNo);
}
