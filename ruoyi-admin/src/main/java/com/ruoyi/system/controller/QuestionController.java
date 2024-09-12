package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.system.domain.Question;
import com.ruoyi.system.service.IQuestionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问题表Controller
 *
 * @author ruoyi
 * @date 2023-03-24
 */
@Controller
@RequestMapping("/system/question")
public class QuestionController extends BaseController
{
    private String prefix = "system/question";

    @Autowired
    private IQuestionService questionService;

    @RequiresPermissions("system:question:view")
    @GetMapping()
    public String question()
    {
        return prefix + "/question";
    }

    /**
     * 查询问题表列表
     */
    @RequiresPermissions("system:question:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Question question)
    {

        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

    /**
     * 导出问题表列表
     */
    @RequiresPermissions("system:question:export")
    @Log(title = "问题表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Question question)
    {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        return util.exportExcel(list, "问题表数据");
    }

    /**
     * 新增问题表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存问题表
     */
    @RequiresPermissions("system:question:add")
    @Log(title = "问题表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Question question)
    {
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改问题表
     */
    @RequiresPermissions("system:question:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Question question = questionService.selectQuestionById(id);
        mmap.put("question", question);
        return prefix + "/edit";
    }

    /**
     * 修改保存问题表
     */
    @RequiresPermissions("system:question:edit")
    @Log(title = "问题表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Question question)
    {
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除问题表
     */
    @RequiresPermissions("system:question:remove")
    @Log(title = "问题表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(questionService.deleteQuestionByIds(ids));
    }
}
