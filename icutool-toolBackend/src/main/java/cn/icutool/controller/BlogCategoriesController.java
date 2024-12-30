package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.domain.entity.BlogCategories;
import cn.icutool.service.BlogCategoriesService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 存储文章分类(BlogCategories)表控制层
 *
 * @author icutool
 * @since 2024-12-29 22:13:58
 */
@RestController
@RequestMapping("/blogCategories")
public class BlogCategoriesController {
    /**
     * 服务对象
     */
    private final BlogCategoriesService blogCategoriesService;

    public BlogCategoriesController(BlogCategoriesService blogCategoriesService) {
        this.blogCategoriesService = blogCategoriesService;
    }

    /**
     * 分页查询
     *
     * @param blogCategories 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult queryByPage(BlogCategories blogCategories, PageRequest pageRequest) {
        return AjaxResult.success(this.blogCategoriesService.queryByPage(blogCategories, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public AjaxResult queryById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.blogCategoriesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param blogCategories 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(BlogCategories blogCategories) {
        return AjaxResult.success(this.blogCategoriesService.insert(blogCategories));
    }

    /**
     * 编辑数据
     *
     * @param blogCategories 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(BlogCategories blogCategories) {
        return AjaxResult.success(this.blogCategoriesService.update(blogCategories));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/delete")
    public AjaxResult deleteById(Long id) {
        return AjaxResult.success(this.blogCategoriesService.deleteById(id));
    }

}

