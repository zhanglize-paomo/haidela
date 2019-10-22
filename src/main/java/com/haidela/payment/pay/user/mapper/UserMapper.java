package com.haidela.payment.pay.user.mapper;

import com.haidela.payment.pay.user.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author zhanglize
 * @create 2019/10/22
 */
@Repository
public interface UserMapper {

    /**
     * 根据主键删除数据信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增用户对象
     *
     * @param record
     * @return
     */
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
