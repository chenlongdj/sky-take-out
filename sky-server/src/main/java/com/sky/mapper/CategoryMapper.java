package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     *  分类分页查询
     * @param category
     * @return
     */
    Page<Category> pageQuery(Category category);

    /**
     * 新增菜品分类
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 修改分类 || 启用、禁用分类
     * @param category
     */
    void update(Category category);
//    /**
//     * 启用、禁用分类
//     * @param status
//     * @param id
//     */
//    @Update("update category set status = #{status} where id = #{id}")
//    void startOrStop(Integer status, Integer id);
    /**
     * 根据id删除分类
     * @param id
     */

    @Delete("delete from category where id = #{id}")
    void delete(Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */

    @Select("select  * form category where type =#{type}")
    List<Category> select(Integer type);
}
