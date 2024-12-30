package cn.icutool.service.impl;

import cn.icutool.domain.dto.BlogArticlesDTO;
import cn.icutool.domain.dto.BlogDTO;
import cn.icutool.domain.dto.BlogTagDTO;
import cn.icutool.domain.dto.PageDTO;
import cn.icutool.domain.entity.BlogArticles;
import cn.icutool.mapper.BlogArticleTagsMapper;
import cn.icutool.mapper.BlogArticlesMapper;
import cn.icutool.service.BlogArticlesService;
import cn.icutool.utils.IStringUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 存储文章的主要信息(BlogArticles)表服务实现类
 *
 * @author icutool
 * @since 2024-12-29 22:13:37
 */
@Service("blogArticlesService")
public class BlogArticlesServiceImpl implements BlogArticlesService {
    private final BlogArticlesMapper blogArticlesMapper;
    private final BlogArticleTagsMapper blogArticleTagsMapper;

    public BlogArticlesServiceImpl(BlogArticlesMapper blogArticlesMapper, BlogArticleTagsMapper blogArticleTagsMapper) {
        this.blogArticlesMapper = blogArticlesMapper;
        this.blogArticleTagsMapper = blogArticleTagsMapper;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogArticles queryById(Long id) {
        return this.queryById(id);
    }

    @Override
    public BlogArticlesDTO queryDetailById(Long id) {
        BlogDTO article = blogArticlesMapper.queryOne(id);
        // 查询上一条和下一条
        BlogArticlesDTO blogArticlesDTO = new BlogArticlesDTO();
        blogArticlesDTO.setArticle(article);
        return blogArticlesDTO;
    }

    /**
     * 分页查询
     *
     * @param keyword 筛选条件
     * @param pageNum      页码
     * @param pageSize      页大小
     * @return 查询结果
     */
    @Override
    public PageDTO<BlogDTO> queryByPage(String keyword, Integer pageNum, Integer pageSize) {
        pageNum = (pageNum -1)* pageSize;
        long total = this.blogArticlesMapper.count(keyword);
        List<BlogDTO> blogArticlesPage = this.blogArticlesMapper.queryPageByLimit(keyword, pageNum, pageSize);
        if (blogArticlesPage.isEmpty()) {
            return new PageDTO<>(blogArticlesPage, total);
        }

        List<Long> articleIds = blogArticlesPage.stream().map(BlogDTO::getId).collect(Collectors.toList());
        List<BlogTagDTO> tags = blogArticleTagsMapper.getTagsForArticles(articleIds);

        Map<Long, List<String>> tagsGroupedByArticleId = tags.stream()
                .collect(Collectors.groupingBy(
                        BlogTagDTO::getArticleId, // 按文章 ID 分组
                        Collectors.mapping(BlogTagDTO::getTagName, Collectors.toList()) // 提取 tagName 列表
                ));

        for (BlogDTO blog : blogArticlesPage) {
            String body = IStringUtil.removeHtmlTags(blog.getContent());
            blog.setExcerpt(body.length() > 100 ? body.substring(0, 100) + "..." : body);
            blog.setTags(tagsGroupedByArticleId.getOrDefault(blog.getId(), Collections.emptyList()));
            blog.setContent(null);
        }
        return new PageDTO<>(blogArticlesPage, total);
    }

    /**
     * 新增数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticles insert(BlogArticles blogArticles) {
        this.blogArticlesMapper.insert(blogArticles);
        return blogArticles;
    }

    /**
     * 修改数据
     *
     * @param blogArticles 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticles update(BlogArticles blogArticles) {
        this.blogArticlesMapper.update(blogArticles);
        return this.queryById(blogArticles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.blogArticlesMapper.deleteById(id) > 0;
    }
}
