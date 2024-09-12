package com.ruoyi.web.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.JudgeUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.domain.Leaveword;
import com.ruoyi.system.domain.Message;
import com.ruoyi.system.domain.Test;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sett
 * @date 2023年03月23日 13:58
 * @title
 */
@Controller
public class FrontController {

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ITestRecordService iTestRecordService;

    @Autowired
    private ILeavewordService iLeavewordService;


    @Autowired
    private IMessageService iMessageService;






    public static Map<Long,List<String>> answer = new HashMap<>();
    static {
        answer.put(1l,Arrays.asList("从无或偶尔有","有时有","经常有","总是如此"));
        answer.put(2l,Arrays.asList("从无或偶尔有","有时有","经常有","总是如此"));
        answer.put(3l,Arrays.asList("没有或很少时间","小部分时间","相当多时间","绝大部分或全部时间"));
    }




    @Autowired
    private ITestService testService;

    private String prefix = "front/";

    @GetMapping("/indexfront")
    public String getIndex(ModelMap mm){
        List<Blog> blogs = iBlogService.selectBlogList(new Blog());
        mm.put("blogs",blogs);
        return prefix+"index";
    }


    @GetMapping("/blog")
    public String getBlog(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "6",value = "pageSize") Integer pageSize
                                 ,ModelMap mm){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = iBlogService.selectBlogList(new Blog());
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        mm.put("blogs",blogPageInfo);
        return prefix+"blog";
    }




    @GetMapping("/blogdetail/{id}")
    public String getblogDetail(ModelMap mm,@PathVariable(value = "id",required = false)Long id){
        id = id == null?2l:id;
        Blog blog = iBlogService.selectBlogById(id);
        mm.put("blog",blog);
        return prefix+"blog-single";
    }
    @GetMapping("/contact")
    public String getCOntact(ModelMap mm){
        List<Leaveword> leavewords = iLeavewordService.selectLeavewordList(new Leaveword());
        mm.put("leavewords",leavewords);
        return prefix+"contact";
    }

    @GetMapping("/test/{id}")
    public String getTest(ModelMap map,@PathVariable("id")Long id){
        id = id ==null?1l:id;
        Test test = testService.selectTestById(id);
        map.put("questionlist",test.getQuestionList());
        map.put("answer",answer.get(id));
        map.put("testid",id);
        return prefix+"test";
    }


    @GetMapping("/message")
    public String getMessage(ModelMap mm){
        Long userId = ShiroUtils.getUserId();
        Message message = new Message();
        message.setUserId(userId);
        List<Message> messages = iMessageService.selectMessageList(message);
        mm.put("messages",messages);
        mm.put("userid",userId);
        return prefix+"message";
    }


    @GetMapping("/sendleave")
    @ResponseBody
    public AjaxResult getLeaveWord(@RequestParam("leaveword")String leaveword){
        Long userId = ShiroUtils.getUserId();
        Leaveword leaveword1 = new Leaveword();
        leaveword1.setUserId(userId);
        leaveword1.setStatus("1");
        leaveword1.setContent(leaveword);
        int i = iLeavewordService.insertLeaveword(leaveword1);
        if (i==1){
            return AjaxResult.success();

        }else{
            return AjaxResult.error();
        }
    }


    //获取前端所有的答案
    @PostMapping("/test-form")
    public String getResult(@RequestParam Map<String,Object> map,ModelMap mm){
        Long userId = ShiroUtils.getUserId();
        map.remove("subtest");
        List<String> res = null ;
        if("1".equals(map.get("submit"))){
           res = JudgeUtils.Judge1(map);
        }else if("2".equals(map.get("submit"))){
            res = JudgeUtils.Judge2(map);
        }else{
            res = JudgeUtils.Judge3(map);
        }
        res.add(userId.toString());//0 type 1 score 2 result 3 suggestion 4 userid 5 count 6 rank
        iTestRecordService.insertTestRecord(res);
        mm.put("res",res);
        return prefix+"result";
    }



}
