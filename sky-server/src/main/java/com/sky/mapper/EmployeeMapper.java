package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     * @param employee
     */
    @Insert("insert into employee(name,username, password, phone,sex,id_number, create_time,update_time,create_user,update_user) " +
            "values(#{name},#{username}, #{password},#{phone} ,#{sex},#{idNumber}, #{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);



    /**
     * 分页查询
     * @param name
     * @return
     */
    Page<Employee> pageQuery(String name);

    /**
     * 启用禁用员工账号
     * @param employee
     */
    void update(Employee employee);



    /**
     * 查询用户信息
     * @param id
     * @return
     */

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);

    /**
     * 修改员工信息
     * @param employee
     */

//    void updateById(Employee employee);



    /**
     * 修改用户密码
     * @param passwordEditDTO
     */
    @Update("update employee set password = #{newPassword} where id = #{empId}")
    void updatePassword(PasswordEditDTO passwordEditDTO);


}
