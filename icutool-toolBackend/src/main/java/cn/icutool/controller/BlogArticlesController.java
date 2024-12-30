package cn.icutool.controller;

import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.domain.entity.BlogArticles;
import cn.icutool.service.BlogArticlesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

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
     * 分页接口
     */
    @ApiOperation("消息分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "keyword", value = "关键词")
    })
    @GetMapping("/page")
    public AjaxResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @Valid @Max(value = 30, message = "页大小最大30") @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String keyword){
        return AjaxResult.success(this.blogArticlesService.queryByPage(keyword, pageNum, pageSize));
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

