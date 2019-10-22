package com.haidela.payment.pay.user.mapper;

import com.haidela.payment.pay.user.domain.User;
import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);


    User selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据公司ID查询对应的人员信息
     *
     * @param compId
     * @return
     */
    User findByCompId(String compId);
}