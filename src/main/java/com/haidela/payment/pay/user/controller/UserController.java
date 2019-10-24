package com.haidela.payment.pay.user.controller;

import com.haidela.payment.pay.user.domain.User;
import com.haidela.payment.pay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器层
 *
 *
 * @author zhanglize
 * @create 2019/10/22
 */
@Controller("UserController")
@RequestMapping("/user")
public class UserController {

    private UserService service;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable Long id){
        return service.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public int insert(@RequestBody User user){
        return service.insert(user);
    }

    @ResponseBody
    @RequestMapping(value = "insert-selective",method = RequestMethod.POST)
    public int insertSelective(@RequestBody User user){
        return service.insertSelective(user);
    }

    @ResponseBody
    @RequestMapping(value = "select-primary-key",method = RequestMethod.POST)
    public User selectByPrimaryKey(@PathVariable Long id){
        return service.selectByPrimaryKey(id);
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody User user){
        return service.updateByPrimaryKeySelective(user);
    }

    @ResponseBody
    @RequestMapping(value = "update-primary-key",method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody User user){
        return service.updateByPrimaryKey(user);
    }

    /**
     * 登录页面信息
     *
     * @param compId   公司id
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String compId, @RequestParam String password){
        return service.login(compId,password);
    }


}
