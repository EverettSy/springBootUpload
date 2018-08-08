/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DruidStatFilter
 * Author:   Administrator
 * Date:     2018/8/2 0002 14:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.jpa;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Raven
 * @create 2018/8/2 0002
 * @since 1.0.0
 */
@WebFilter(filterName = "druidStaFilter",urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}