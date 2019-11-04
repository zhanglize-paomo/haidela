package com.haidela.payment.pay.paycustomer.mapper;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 根据交易流水号修改该笔交易的异步消息接收的情况
     *
     * @param tranFlow     交易流水号
     * @param receiveMessages   消息
     * @return
     */
    int updateReceiveMessages(@Param(value = "tranFlow") String tranFlow,@Param(value = "receiveMessages") String receiveMessages);

    /**
     * 根据交易流水号查询该交易流水号是否存在
     *
     * @param tranFlow
     * @return
     */
    PayCustomer findByTranFlow(String tranFlow);

    /**
     * 根据id修改该条商户信息的订单信息
     *
     * @param id
     * @param paySerialNo  平台交易流水号
     * @return
     */
    int updateByPaySerialNo(@Param(value = "id") Long id, @Param(value = "paySerialNo") String paySerialNo);

    /**
     * 根据商户号查询对应的最新商户数据信息
     *
     * @param merchantNo
     * @return
     */
    List<PayCustomer> findByThree(String merchantNo);

    /**
     * 根据商户号查询到对应的支付信息
     *
     * @param merchantId
     * @return
     */
    List<PayCustomer> findByMerchantNo(String merchantId);

    /**
     * 查询某种交易状态的交易信息
     *
     * @param status
     * @return
     */
    List<PayCustomer> findByStatus(String status);
}
