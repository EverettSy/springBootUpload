/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Swagger2Config
 * Author:   Administrator
 * Date:     2018/8/6 0006 16:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通过@Configuration注解 让Spring来加载该类配置〉
 * 〈通过@EnableSwagger2注解启用Swagger2〉
 * @author Raven
 * @create 2018/8/6 1125
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.raven.upload.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private  ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                //页面标题
                .title("springBoot文件上传平台--基数数据API说明文档")
                //创建人
                .contact(new Contact("Raven","http://www.syraven.top","sy759770423@163.com"))
                //描述
                .description("2018.8.6上线版本")
                //.termsOfServiceUrl("http://syraven.top")
                //版本号
                .version("1.0")
                .build();
    }
}