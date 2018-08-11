# springBootUpload
使用SpringBoot实现单文件或者多文件上传

#集成Swagger生成RESTful规范API文档
Swagger是为了描述一套标准的而且是和语言无关的REST API的规范。对于外部调用者来说，只需通过Swagger文档即可清楚Server端提供的服务，而不需去阅读源码或接口文档说明。

##### 官方网站：http://swagger.io
##### 中文网站：http://www.sosoapi.com
<!--more-->
## 背景
**前后端分离**

1. 前后端仅仅通过异步接口(AJAX/JSON)来编程
2. 前后端都各自有自己的开发流程流程，构建工具，测试集合。
3. 关注点分离,前后端变得相对独立并且松耦合

![](http://pa5d8eb62.bkt.clouddn.com/%E5%89%8D%E5%90%8E%E7%AB%AF%E5%88%86%E7%A6%BB.png)



1. 后台编写和维护接口文档，在 API 变化时更新接口文档
2. 后台根据接口文档进行接口开发
3. 前端根据接口文档进行开发
4. 开发完成后联调和提交测试
![](http://pa5d8eb62.bkt.clouddn.com/liantiao.png)

##### Spring Boot整合Swagger2
###### **1、修改pom.xml,添加Swagger2依赖**

```java   
<!-- Swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>

        <!--接口API生成html文档-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>

```

###### **2、编写Swagger2配置类**

```java
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
```
1. 通过@Configuration注解，让Spring来加载该类配置
2. 通过@EnableSwagger2注解来启用Swagger2。

**再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore注解的API）。**

###### **3、编写Controller**

这里开始编写自己的RESTful Controller，跟正常开发没什么区别。主要是接口方法上面新增了几个注解：

* 通过@ApiOperation注解来给API增加说明
* 通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明
* 通过@ApiIgnore来忽略那些不想让生成RESTful API文档的接口

```java
package com.raven.upload.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Raven
 * @create 2018/7/24 0024
 * @since 1.0.0
 */
@RestController
@RequestMapping("/file")
@Validated
@Api(value = "文件上传UploadController",tags = {"文件上传"})
public class UploadController {
    /**
     * 初始化上传文件界面,跳转到index.jsp
     *
     * @return
     */
    @ApiOperation(value = "初始化上传界面",notes = "上传界面")
    //@GetMapping(value = "/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 上传文方法
     * @param file 前台上传的文件对象
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    //@PostMapping(value = "/upload")
    @ApiOperation(value = "上传单张图片接口",notes = "上传单张图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "string",name = "token",value = "访问凭证",required = true),
    })
    public @ResponseBody
    String upload(HttpServletRequest request, MultipartFile file) {

        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            /**
            //文件后缀名
            String suttix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            //上传文件名
            String filename = UUID.randomUUID() + suttix;
            //String filename = file.getOriginalFilename();
            //服务器端保存的文件对象
            File serverFile = new File(uploadDir + filename);
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);*/
            executeUpload(uploadDir,file);
        } catch (Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";

    }

    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传目录
     * @param file 上传对象
     * @throws Exception
     */
    private void executeUpload(String uploadDir,MultipartFile file) throws Exception {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        //上传文件名
        String filename = UUID.randomUUID()+suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir+filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
    }

    //@PostMapping(value = "/uploads")
    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    @ApiOperation(value = "上传多张图片接口",notes = "上传多张图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "string",name = "token",value = "访问凭证",required = true),
    })
    public @ResponseBody String uploads(HttpServletRequest request,MultipartFile[] file){
        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            //遍历数组执行上传
            for (int i = 0; i <file.length ; i++) {
                if(file[i]!=null){
                    //调用上传方法
                    executeUpload(uploadDir,file[i]);
                }
            }
        } catch (Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}

```
完成上述代码后，打包Spring Boot程序并启动，打开浏览器访问：http://localhost:8080/swagger-ui.html，就能看到前文所展示的RESTful API的页面。 

![](http://pa5d8eb62.bkt.clouddn.com/1533691593.jpg)
