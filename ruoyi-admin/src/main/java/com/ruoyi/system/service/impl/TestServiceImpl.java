package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.Question;
import com.ruoyi.system.mapper.TestMapper;
import com.ruoyi.system.domain.Test;
import com.ruoyi.system.service.ITestService;
import com.ruoyi.common.core.text.Convert;

/**
 * 测试Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@Service
public class TestServiceImpl implements ITestService 
{
    @Autowired
    private TestMapper testMapper;

    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    @Override
    public Test selectTestById(Long id)
    {
        return testMapper.selectTestById(id);
    }

    /**
     * 查询测试列表
     * 
     * @param test 测试
     * @return 测试
     */
    @Override
    public List<Test> selectTestList(Test test)
    {
        return testMapper.selectTestList(test);
    }

    /**
     * 新增测试
     * 
     * @param test 测试
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTest(Test test)
    {
        int rows = testMapper.insertTest(test);
        insertQuestion(test);
        return rows;
    }

    /**
     * 修改测试
     * 
     * @param test 测试
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTest(Test test)
    {
        testMapper.deleteQuestionByGroupId(test.getId());
        insertQuestion(test);
        return testMapper.updateTest(test);
    }

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTestByIds(String ids)
    {
        testMapper.deleteQuestionByGroupIds(Convert.toStrArray(ids));
        return testMapper.deleteTestByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTestById(Long id)
    {
        testMapper.deleteQuestionByGroupId(id);
        return testMapper.deleteTestById(id);
    }

    /**
     * 新增问题表信息
     * 
     * @param test 测试对象
     */
    public void insertQuestion(Test test)
    {
        List<Question> questionList = test.getQuestionList();
        Long id = test.getId();
        if (StringUtils.isNotNull(questionList))
        {
            List<Question> list = new ArrayList<Question>();
            for (Question question : questionList)
            {
                question.setGroupId(id);
                list.add(question);
            }
            if (list.size() > 0)
            {
                testMapper.batchQuestion(list);
            }
        }
    }
}
