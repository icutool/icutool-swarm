package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.entity.BlogTags;
import cn.icutool.service.BlogTagsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 存储标签(BlogTags)表控制层
 *
 * @author icutool
 * @since 2024-12-29 22:14:19
 */
@RestController
@RequestMapping("/blogTags")
public class BlogTagsController {
    /**
     * 服务对象
     */
    private final BlogTagsService blogTagsService;

    public BlogTagsController(BlogTagsService blogTagsService) {
        this.blogTagsService = blogTagsService;
    }

    /**
     * 分页查询
     *
     * @param blogTags 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult queryByPage(BlogTags blogTags, PageRequest pageRequest) {
        return AjaxResult.success(this.blogTagsService.queryByPage(blogTags, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public AjaxResult queryById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.blogTagsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param blogTags 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(BlogTags blogTags) {
        return AjaxResult.success(this.blogTagsService.insert(blogTags));
    }

    /**
     * 编辑数据
     *
     * @param blogTags 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(BlogTags blogTags) {
        return AjaxResult.success(this.blogTagsService.update(blogTags));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/delete")
    public AjaxResult deleteById(Long id) {
        return AjaxResult.success(this.blogTagsService.deleteById(id));
    }

}

