package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.entity.BlogArticleTags;
import cn.icutool.service.BlogArticleTagsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章与标签的多对多关系(BlogArticleTags)表控制层
 *
 * @author icutool
 * @since 2024-12-29 22:14:39
 */
@RestController
@RequestMapping("/blogArticleTags")
public class BlogArticleTagsController {
    /**
     * 服务对象
     */
    private final BlogArticleTagsService blogArticleTagsService;

    public BlogArticleTagsController(BlogArticleTagsService blogArticleTagsService) {
        this.blogArticleTagsService = blogArticleTagsService;
    }

    /**
     * 分页查询
     *
     * @param blogArticleTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult queryByPage(BlogArticleTags blogArticleTags, PageRequest pageRequest) {
        return AjaxResult.success(this.blogArticleTagsService.queryByPage(blogArticleTags, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public AjaxResult queryById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.blogArticleTagsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param blogArticleTags 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BlogArticleTags blogArticleTags) {
        return AjaxResult.success(this.blogArticleTagsService.insert(blogArticleTags));
    }

    /**
     * 编辑数据
     *
     * @param blogArticleTags 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody BlogArticleTags blogArticleTags) {
        return AjaxResult.success(this.blogArticleTagsService.update(blogArticleTags));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/delete")
    public AjaxResult deleteById(@RequestParam Long id) {
        return AjaxResult.success(this.blogArticleTagsService.deleteById(id));
    }

}

