package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.entity.BlogArticles;
import cn.icutool.service.BlogArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 存储文章的主要信息(BlogArticles)表控制层
 *
 * @author icutool
 * @since 2024-12-29 22:13:35
 */
@RestController
@RequestMapping("/blogArticles")
public class BlogArticlesController {
    /**
     * 服务对象
     */
    private final BlogArticlesService blogArticlesService;

    public BlogArticlesController(BlogArticlesService blogArticlesService) {
        this.blogArticlesService = blogArticlesService;
    }

    /**
     * 分页查询
     *
     * @param blogArticles 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult queryByPage(BlogArticles blogArticles, PageRequest pageRequest) {
        return AjaxResult.success(this.blogArticlesService.queryByPage(blogArticles, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public AjaxResult queryById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.blogArticlesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param blogArticles 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BlogArticles blogArticles) {
        return AjaxResult.success(this.blogArticlesService.insert(blogArticles));
    }

    /**
     * 编辑数据
     *
     * @param blogArticles 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody BlogArticles blogArticles) {
        return AjaxResult.success(this.blogArticlesService.update(blogArticles));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/delete")
    public AjaxResult deleteById(@RequestParam Long id) {
        return AjaxResult.success(this.blogArticlesService.deleteById(id));
    }

}

