/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 cuisongliu@qq.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.xinyuewulian.test;

import com.xinyuewulian.admin.startup.Application;
import com.xinyuewulian.entity.Param;
import com.xinyuewulian.service.ParamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.List;

/**
 * SpringBootTest
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2017-05-20 21:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@org.springframework.boot.test.context.SpringBootTest(classes = Application.class)
public class SpringBootTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ParamService paramService;

    @Test
    public void testSelectAll() {
        OrderByHelper.orderBy("id desc");
        List<Param> paramList = paramService.listAll();
        for (Param param:paramList){
            logger.debug("param:"+param.getParamName());
        }
    }

}
