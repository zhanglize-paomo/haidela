package com.haidela.payment.pay.paycustomer.service;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.mapper.PayCustomerMapper;
import com.haidela.payment.util.DateUtils;
import com.haidela.payment.util.ExcelUtil;
import com.haidela.payment.util.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        payCustomer.setReceiveMessages("0");
        payCustomer.setId(new SnowflakeIdUtils().nextId());
        return mapper.add(payCustomer);
    }

    /**
     * 根据交易流水号修改该条交易的交易状态
     *
     * @param tranFlow 交易流水号
     * @param status   交易状态
     * @return
     */
    public int updateStatus(String tranFlow, String status) {
        return mapper.updateStatus(tranFlow, status);
    }

    /**
     * 根据交易流水号查询该交易流水号是否存在
     *
     * @param tranFlow
     * @return
     */
    public PayCustomer findByTranFlow(String tranFlow) {
        return mapper.findByTranFlow(tranFlow);
    }

    /**
     * 根据交易流水号修改该笔交易的异步消息接收的情况
     *
     * @param tranFlow        交易流水号
     * @param receiveMessages 消息
     * @return
     */
    public int updateReceiveMessages(String tranFlow, String receiveMessages) {
        return mapper.updateReceiveMessages(tranFlow, receiveMessages);
    }

    /**
     * 根据id修改该条商户信息的订单信息
     *
     * @param id
     * @param paySerialNo 平台交易流水号
     * @return
     */
    public int updateByPaySerialNo(Long id, String paySerialNo) {
        return mapper.updateByPaySerialNo(id, paySerialNo);
    }

    /**
     * 根据商户号查询对应的最新商户数据信息
     *
     * @param merchantNo
     * @return
     */
    public List<PayCustomer> findByThree(String merchantNo) {
        return mapper.findByThree(merchantNo);
    }

    /**
     * 根据商户号查询到对应的支付信息
     *
     * @param merchantId
     * @return
     */
    public List<PayCustomer> findByMerchantNo(String merchantId) {
        return mapper.findByMerchantNo(merchantId);
    }

    /**
     * 查询某种交易状态的交易信息
     *
     * @param status
     * @return
     */
    public List<PayCustomer> findByStatus(String status) {
        return mapper.findByStatus(status);
    }


    public List<PayCustomer> pagePayCustomerDetail(String startTime, String endTime, String compID, String customerId, String typeStr, String tranFlow) {
        if (("").equals(startTime)) {
            startTime = null;
        }
        if (("").equals(endTime)) {
            endTime = null;
        }
        if (("").equals(compID) ) {
            compID = null;
        }
        if (("").equals(customerId)) {
            customerId = null;
        }
        if (("").equals(typeStr)) {
            typeStr = null;
        }
        if (("").equals(tranFlow)) {
            tranFlow = null;
        }
        return mapper.pagePayCustomerDetail(startTime, endTime, compID, customerId, typeStr, tranFlow);
    }

    /**
     * 导出Excel表格
     *  @param startTime  开始时间
     * @param endTime    结束时间
     * @param compID     公司ID
     * @param customerId
     * @param typeStr
     * @param tranFlow
     * @param response
     */
    public void exportPayCustomerDetail(String startTime, String endTime, String compID, String customerId, String typeStr, String tranFlow, HttpServletResponse response) {
        //获取到所有导出的数据信息
        List<PayCustomer> customerList = pagePayCustomerDetail(startTime,endTime,compID,customerId,typeStr,tranFlow);
        if(customerList.size() != 0){
            String fileName = "客户交易流水-" + DateUtils.stringToDate();
            String sheetName = "客户交易流水";
            ExcelUtil.writeExcel(response,customerList,fileName,sheetName,new PayCustomer());
        }

    }
}
