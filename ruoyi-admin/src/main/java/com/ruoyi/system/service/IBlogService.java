package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Blog;

/**
 * 博客管理Service接口
 * 
 * @author ruoyi
 * @date 2023-03-23
 */
public interface IBlogService 
{
    /**
     * 查询博客管理
     * 
     * @param id 博客管理主键
     * @return 博客管理
     */
    public Blog selectBlogById(Long id);

    /**
     * 查询博客管理列表
     * 
     * @param blog 博客管理
     * @return 博客管理集合
     */
    public List<Blog> selectBlogList(Blog blog);

    /**
     * 新增博客管理
     * 
     * @param blog 博客管理
     * @return 结果
     */
    public int insertBlog(Blog blog);

    /**
     * 修改博客管理
     * 
     * @param blog 博客管理
     * @return 结果
     */
    public int updateBlog(Blog blog);

    /**
     * 批量删除博客管理
     * 
     * @param ids 需要删除的博客管理主键集合
     * @return 结果
     */
    public int deleteBlogByIds(String ids);

    /**
     * 删除博客管理信息
     * 
     * @param id 博客管理主键
     * @return 结果
     */
    public int deleteBlogById(Long id);
}
