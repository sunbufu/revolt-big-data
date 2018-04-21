package com.sunbufu;

import com.sunbufu.common.util.NetUtils;
import com.sunbufu.dao.SystemRecordDao;
import com.sunbufu.entity.SystemRecord;
import com.sunbufu.entity.type.SystemRecordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import java.nio.charset.Charset;

/**
 * 系统入口
 *
 * @author sunbufu
 */
@SpringBootApplication
public class RevoltBigDataApplication {

    @Autowired
    private SystemRecordDao systemRecordDao;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8"))).build();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RevoltBigDataApplication.class);
        //监听系统启动成功
        app.addListeners((ApplicationListener<ApplicationReadyEvent>) event -> event.getApplicationContext().getBean(SystemRecordDao.class).saveAndFlush(new SystemRecord(SystemRecordType.SYSTEM_RECORD_START, NetUtils.hostIpStr, NetUtils.hostNameStr)));
        //监听系统启动失败
        app.addListeners((ApplicationListener<ApplicationFailedEvent>) event -> event.getApplicationContext().getBean(SystemRecordDao.class).saveAndFlush(new SystemRecord(SystemRecordType.SYSTEM_RECORD_FAIL, NetUtils.hostIpStr, NetUtils.hostNameStr)));
        app.run(args);
    }

    @PreDestroy
    public void stop(){
        //监听系统关闭
        systemRecordDao.saveAndFlush(new SystemRecord(SystemRecordType.SYSTEM_RECORD_STOP, NetUtils.hostIpStr, NetUtils.hostNameStr));
    }

}
