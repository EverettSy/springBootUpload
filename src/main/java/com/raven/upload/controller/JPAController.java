/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JPAController
 * Author:   Administrator
 * Date:     2018/7/25 0025 21:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.controller;

import com.raven.upload.dao.StudentRepository;
import com.raven.upload.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Raven
 * @create 2018/7/25 0025
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
@Api(value = "JPAController",description = "通过JPA操作数据库",tags = {"JPA操作数据库"})
public class JPAController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 保存数据
     * @param stu
     * @return
     */
    @ApiOperation(value = "创建用户",notes = "根据User对象创建用户")
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public Student save(Student stu) {
        return studentRepository.save(stu);
    }

    /**
     * 查询所有数据
     * @return
     */
    @ApiOperation(value = "查询所有用户",notes = "获取用户列表信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Student> list(){
       return studentRepository.findAll();
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @ApiOperation(value = "删除指定id用户信息",notes = "根据id删除指定用户信息")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<Student> delete(Integer id){
        studentRepository.delete(id);
        return studentRepository.findAll();
    }
}