package com.haidela.payment.pay.configure.mapper;


import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import org.apache.ibatis.annotations.Param;
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
     * @param status
     */
    List<MerchantConfigure> findByStstus(String status);

    /**
     * 查询公司下所有可用的商户id信息
     *
     *
     * @param payType 支付类型
     * @param status  状态
     * @param compID  公司ID
     * @return
     */
    List<MerchantConfigure> findByStstusCompId(@Param(value = "status") String status, @Param(value = "compID") String compID, @Param(value = "payType") String payType);

    /**
     * 根据商户编号以及支付类型获取到对应的配置对象信息
     *
     * @param merchantNo
     * @param payType
     * @return
     */
    MerchantConfigure findByMerchantNo(@Param(value = "merchantNo") String merchantNo, @Param(value = "payType") String payType);

    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    int updateMerchantId(String merchantId);
    /**
     * 根据id修改个体工商户配置的基本信息
     *
     * @param id    主键
     * @param totalOneAmount 单日总额
     * @param status  状态
     * @return
     */
    int update(@Param(value = "id") Long id,@Param(value = "totalOneAmount") String totalOneAmount, @Param(value = "status") String status);

    /**
     * 根据id修改该条商户信息
     *
     * @param id   ID
     * @param status 状态
     * @return
     */
    int updateStatus(@Param(value = "id") Long id, @Param(value = "status") String status);

    /**
     * 根据该商户的id修改该商户的支付类型的状态
     *
     * @param merchantNo
     * @param payType
     * @param status
     * @return
     */
    int updateMerchantIdPayType(@Param(value = "merchantNo") String merchantNo,@Param(value = "payType") String payType, @Param(value = "status") String status);
}
