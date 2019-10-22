package com.haidela.payment.pay.user.service;

import com.haidela.payment.pay.user.domain.User;
import com.haidela.payment.pay.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author zhanglize
 * @create 2019/10/22
 */
@Service
public class UserService {

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据公司ID查询对应的人员信息
     *
     * @param compId
     * @return
     */
    public User findByCompId(String compId) {
        return userMapper.findByCompId(compId);
    }

    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 登录页面信息
     *
     * @param compId   公司id
     * @param password  密码
     * @return
     */
    public String login(String compId, String password) {
        /**
         * 1.判断公司id是否存在,如果不存在提示用户登录名错误
         * 2.判断密码是否正确,不正确提示用户密码不对
         * 3.否则登陆成功
         */
        User user = findByCompId(compId);
        if(user == null){
            return "用户不存在";
        }
        if(user.getPassword() != password){
            return "登陆密码不正确";
        }
        return "登陆成功";
    }
}
