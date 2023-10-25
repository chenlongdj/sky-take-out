package com.sky.controller.admin;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @auther CL
 * @date 2023/10/24 0024  13:45
 */


@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);
        return  Result.success(pageResult);
    }

    /**
     * 新增菜品分类
     * @param categoryDTO
     * @return
     */
    @PostMapping()
    @ApiOperation("新增菜品分类")
    public Result save(@RequestBody CategoryDTO categoryDTO){
        categoryService.save(categoryDTO);
    return Result.success();
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */

    @PutMapping
    @ApiOperation("修改分类")
    public  Result  update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 启用、禁用分类
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用、禁用分类")
    public Result startOrStop(@PathVariable  Integer status,Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */

    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public  Result getByIdDelete(@RequestParam(required = true) Long id){
        categoryService.getByIdDelete(id);
        return Result.success();
    }
    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public  Result<List<Category>> queryList( Integer type){
        List<Category> categoryList=categoryService.list(type);
        return Result.success(categoryList);

    }




}
