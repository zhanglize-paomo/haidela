package com.haidela.payment.pay.createmerchant.mapper;

import com.haidela.payment.pay.createmerchant.domain.IndividualCustomer;
import org.springframework.stereotype.Repository;

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
}
