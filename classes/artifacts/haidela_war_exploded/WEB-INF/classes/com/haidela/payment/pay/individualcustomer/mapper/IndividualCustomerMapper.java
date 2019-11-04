package com.haidela.payment.pay.individualcustomer.mapper;

import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商户进件个体商户信息
 *
 * @author zhanglize
 * @create 2019/10/15
 */
@Repository
public interface IndividualCustomerMapper {

    /**
     * 新增商户进件个体商户信息
     *
     * @param customer
     * @return
     */
    int add(IndividualCustomer customer);

    /**
     * 根据个人商户编号查询到对应的个人信息
     *
     * @param merchantNo 个人商户编号
     * @return
     */
    IndividualCustomer findMerchantNo(String merchantNo);

    /**
     * 查询商户进件个体商户信息
     *
     * @return
     */
    List<IndividualCustomer> findAllMerchant();
}
