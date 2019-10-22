package com.haidela.payment.pay.user.service;

import com.haidela.payment.pay.user.domain.User;
import com.haidela.payment.pay.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 登录页面信息
     *
     * @param model
     * @param compId   公司id
     * @param password  密码
     * @return
     */
    public String login(Model model, String compId, String password) {
        return "登陆成功";
    }
}
