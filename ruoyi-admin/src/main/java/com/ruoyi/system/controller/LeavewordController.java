package com.ruoyi.system.controller;

import java.util.List;

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
import com.ruoyi.system.domain.Leaveword;
import com.ruoyi.system.service.ILeavewordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 留言管理Controller
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Controller
@RequestMapping("/system/leaveword")
public class LeavewordController extends BaseController
{
    private String prefix = "system/leaveword";

    @Autowired
    private ILeavewordService leavewordService;

    @RequiresPermissions("system:leaveword:view")
    @GetMapping()
    public String leaveword()
    {
        return prefix + "/leaveword";
    }

    /**
     * 查询留言管理列表
     */
    @RequiresPermissions("system:leaveword:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Leaveword leaveword)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                leaveword.setUserId(sysUser.getUserId());
            }
        }
        startPage();
        List<Leaveword> list = leavewordService.selectLeavewordList(leaveword);
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @RequiresPermissions("system:leaveword:export")
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Leaveword leaveword)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                leaveword.setUserId(sysUser.getUserId());
            }
        }
        List<Leaveword> list = leavewordService.selectLeavewordList(leaveword);
        ExcelUtil<Leaveword> util = new ExcelUtil<Leaveword>(Leaveword.class);
        return util.exportExcel(list, "留言管理数据");
    }

    /**
     * 新增留言管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存留言管理
     */
    @RequiresPermissions("system:leaveword:add")
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Leaveword leaveword)
    {
        return toAjax(leavewordService.insertLeaveword(leaveword));
    }

    /**
     * 修改留言管理
     */
    @RequiresPermissions("system:leaveword:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Leaveword leaveword = leavewordService.selectLeavewordById(id);
        mmap.put("leaveword", leaveword);
        return prefix + "/edit";
    }

    /**
     * 修改保存留言管理
     */
    @RequiresPermissions("system:leaveword:edit")
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Leaveword leaveword)
    {
        return toAjax(leavewordService.updateLeaveword(leaveword));
    }

    /**
     * 删除留言管理
     */
    @RequiresPermissions("system:leaveword:remove")
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(leavewordService.deleteLeavewordByIds(ids));
    }
}
