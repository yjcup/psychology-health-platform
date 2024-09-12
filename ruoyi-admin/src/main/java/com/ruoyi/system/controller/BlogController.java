package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.service.IBlogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客管理Controller
 *
 * @author ruoyi
 * @date 2023-03-23
 */
@Controller
@RequestMapping("/system/blog")
public class BlogController extends BaseController
{
    private String prefix = "system/blog";

    @Autowired
    private IBlogService blogService;

    @RequiresPermissions("system:blog:view")
    @GetMapping()
    public String blog()
    {
        return prefix + "/blog";
    }

    /**
     * 查询博客管理列表
     */
    @RequiresPermissions("system:blog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Blog blog)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                blog.setCreateId(sysUser.getUserId());
            }
        }
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    /**
     * 导出博客管理列表
     */
    @RequiresPermissions("system:blog:export")
    @Log(title = "博客管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Blog blog)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                blog.setCreateId(sysUser.getUserId());
            }
        }
        List<Blog> list = blogService.selectBlogList(blog);
        ExcelUtil<Blog> util = new ExcelUtil<Blog>(Blog.class);
        return util.exportExcel(list, "博客管理数据");
    }

    /**
     * 新增博客管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存博客管理
     */
    @RequiresPermissions("system:blog:add")
    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Blog blog)
    {
        blog.setCreateId(ShiroUtils.getUserId());
        return toAjax(blogService.insertBlog(blog));
    }

    /**
     * 修改博客管理
     */
    @RequiresPermissions("system:blog:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Blog blog = blogService.selectBlogById(id);
        mmap.put("blog", blog);
        return prefix + "/edit";
    }

    /**
     * 修改保存博客管理
     */
    @RequiresPermissions("system:blog:edit")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Blog blog)
    {
        return toAjax(blogService.updateBlog(blog));
    }

    /**
     * 删除博客管理
     */
    @RequiresPermissions("system:blog:remove")
    @Log(title = "博客管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(blogService.deleteBlogByIds(ids));
    }
}
