package com.sunbufu.service.impl;

import com.sunbufu.common.exception.ServiceException;
import com.sunbufu.service.JdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdServiceImplTest {

    @Autowired
    private JdServiceImpl jdService;

//    @Test
//    public void searchBySkuIds() throws Exception {
//        jdService.searchBySkuIds(Arrays.asList("412415", "25763364546", "17868242047"));
//    }

    @Test
    public void searchByKeyword() throws ServiceException {
        jdService.searchByKeyword("钢琴", 1);
    }

}