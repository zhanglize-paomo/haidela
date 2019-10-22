package com.haidela.payment.pay.user.mapper;

import com.haidela.payment.pay.user.domain.User;
import java.math.BigDecimal;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    User selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lyx_user
     *
     * @mbg.generated Tue Oct 22 15:46:18 CST 2019
     */
    int updateByPrimaryKey(User record);
}