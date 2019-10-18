package com.haidela.payment.util;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhanglize
 * @create 2019/10/18
 */
@Component
public class ScheduledTasks {

    private MerchantConfigureService configureService;

    @Autowired
    public void setConfigureService(MerchantConfigureService configureService) {
        this.configureService = configureService;
    }
    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    /**
     * 轮询池定时任务,每天晚上00:00对于轮询池中的状态,以及单日的交易金额总量进行清0
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void task() {
        logger.info("轮询池定时任务 :" + DateUtils.stringToDate());
        //查询所有状态为完成状态的1所对应的个体商户配置信息
        List<MerchantConfigure> configureList = configureService.findByStstus("1");
        configureList.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            configureService.update(merchantConfigure.getId(), merchantConfigure.getTotalOneAmount(), merchantConfigure.getStatus());
        });
        //查询所有状态为未完成状态0所对应的个体工商户信息
        List<MerchantConfigure> list = configureService.findByStstus("1");
        list.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            configureService.update(merchantConfigure.getId(), merchantConfigure.getTotalOneAmount(), merchantConfigure.getStatus());
        });
    }


}
