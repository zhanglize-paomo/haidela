package com.haidela.payment.pay.configure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;

/**
 * 个体商户配置mapper
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Mapper
@Repository
public interface MerchantConfigureMapper {

    NamingException Sel(int id);

}
