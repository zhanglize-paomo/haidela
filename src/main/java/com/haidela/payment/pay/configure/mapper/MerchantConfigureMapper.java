package com.haidela.payment.pay.configure.mapper;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import org.springframework.stereotype.Repository;

/**
 * 个体商户配置mapper
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Repository
public interface MerchantConfigureMapper {

    MerchantConfigure Sel(int id);

}
