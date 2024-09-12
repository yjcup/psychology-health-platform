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
import com.ruoyi.system.domain.TestRecord;
import com.ruoyi.system.service.ITestRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试记录Controller
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Controller
@RequestMapping("/system/record")
public class TestRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private ITestRecordService testRecordService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询测试记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TestRecord testRecord)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                testRecord.setUserId(sysUser.getUserId());
            }
        }
        startPage();
        List<TestRecord> list = testRecordService.selectTestRecordList(testRecord);
        return getDataTable(list);
    }

    /**
     * 导出测试记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "测试记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TestRecord testRecord)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        for(SysRole role:sysUser.getRoles()){
            if ("user".equals(role.getRoleKey())){
                testRecord.setUserId(sysUser.getUserId());
            }
        }
        List<TestRecord> list = testRecordService.selectTestRecordList(testRecord);
        ExcelUtil<TestRecord> util = new ExcelUtil<TestRecord>(TestRecord.class);
        return util.exportExcel(list, "测试记录数据");
    }

    /**
     * 新增测试记录
     */
    @GetMapping("/add")
    public String    add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测试记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "测试记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TestRecord testRecord)
    {
        return toAjax(testRecordService.insertTestRecord(testRecord));
    }

    /**
     * 修改测试记录
     */
    @RequiresPermissions("system:record:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TestRecord testRecord = testRecordService.selectTestRecordById(id);
        mmap.put("testRecord", testRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存测试记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "测试记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TestRecord testRecord)
    {
        return toAjax(testRecordService.updateTestRecord(testRecord));
    }

    /**
     * 删除测试记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "测试记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testRecordService.deleteTestRecordByIds(ids));
    }
}
