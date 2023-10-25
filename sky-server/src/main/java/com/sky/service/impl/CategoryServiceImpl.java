package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @auther CL
 * @date 2023/10/24 0024  13:49
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        //分页插件
       PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        //select * form category limit 1,10
        Category category=new Category();
        BeanUtils.copyProperties(categoryPageQueryDTO,category);
        Page<Category> page=categoryMapper.pageQuery(category);
        long total = page.getTotal();
        List<Category> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 新增菜品分类
     * @param categoryDTO
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //分类状态默认为禁用状态0
        category.setStatus(StatusConstant.DISABLE);

        //设置创建时间、修改时间、创建人、修改人
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * 修改分类
     * @param categoryDTO
     */

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.update(category);
    }

    /**
     * 启用、禁用分类
     * @param status
     */
    @Override
    public void startOrStop(Integer status,Long id) {
//        categoryMapper.startOrStop(status,id);
        Category category=new Category();
        category.setStatus(status);
        category.setId(id);
        categoryMapper.update(category);
    }

    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void getByIdDelete(Long id) {
        categoryMapper.delete(id);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> list(Integer type) {
        List<Category> categoryList=categoryMapper.select(type);
        return categoryList;
    }
}
