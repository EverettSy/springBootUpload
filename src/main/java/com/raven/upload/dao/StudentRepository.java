/**
 * Copyright (C), 2015-2018, 知点学派教育科技有限公司
 * FileName: StudentRepository
 * Author:   Administrator
 * Date:     2018/7/25 0025 20:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.raven.upload.dao;

import com.raven.upload.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Raven
 * @create 2018/7/25 0025
 * @since 1.0.0
 */
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>, Serializable {
}
