/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Application
 * Author:   Administrator
 * Date:     2018/7/24 0024 14:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 〈一句话功能简述〉<br> 
 * 〈启动类〉
 * @SpringBootApplication注解标示启动类
 * @author Raven
 * @create 2018/7/24 0024
 * @since 1.0.0
 */
@SpringBootApplication
@ServletComponentScan // 注意要加上@ServletComponentScan注解，否则Servlet无法生效
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}