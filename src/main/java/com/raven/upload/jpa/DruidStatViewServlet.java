/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DruidStatViewServlet
 * Author:   Administrator
 * Date:     2018/8/2 0002 14:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.jpa;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Raven
 * @create 2018/8/2 0002
 * @since 1.0.0
 */
@WebServlet(urlPatterns = "/druid/*",
initParams = {
        @WebInitParam(name = "allow",value = "127.0.0.1"),//IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name = "deny",value = "192.168.0.0"),//IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name = "loginUsername",value = "admin"),//druid监控页面登陆用户名
        @WebInitParam(name = "loginPassword",value = "admin"),//druid监控页面登陆密码
        @WebInitParam(name = "resetEnable",value = "false")//禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 1L;
}