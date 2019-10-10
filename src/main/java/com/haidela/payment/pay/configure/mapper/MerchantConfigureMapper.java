package com.haidela.payment.pay.configure.mapper;


import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 个体商户配置mapper
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Repository
public interface MerchantConfigureMapper {

    MerchantConfigure Sel(int id);

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantConfigure record);

    int insertSelective(MerchantConfigure record);

    MerchantConfigure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantConfigure record);

    int updateByPrimaryKey(MerchantConfigure record);

    /**
     * 查询所有状态为1的个体商户信息
     *
     * @return
     */
    List<MerchantConfigure> findByStstus();

    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    int updateMerchantId(String merchantId);
}
