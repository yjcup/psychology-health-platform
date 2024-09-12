package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Test;
import com.ruoyi.system.domain.Question;

/**
 * 测试Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface TestMapper 
{
    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    public Test selectTestById(Long id);

    /**
     * 查询测试列表
     * 
     * @param test 测试
     * @return 测试集合
     */
    public List<Test> selectTestList(Test test);

    /**
     * 新增测试
     * 
     * @param test 测试
     * @return 结果
     */
    public int insertTest(Test test);

    /**
     * 修改测试
     * 
     * @param test 测试
     * @return 结果
     */
    public int updateTest(Test test);

    /**
     * 删除测试
     * 
     * @param id 测试主键
     * @return 结果
     */
    public int deleteTestById(Long id);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestByIds(String[] ids);

    /**
     * 批量删除问题表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionByGroupIds(String[] ids);
    
    /**
     * 批量新增问题表
     * 
     * @param questionList 问题表列表
     * @return 结果
     */
    public int batchQuestion(List<Question> questionList);
    

    /**
     * 通过测试主键删除问题表信息
     * 
     * @param id 测试ID
     * @return 结果
     */
    public int deleteQuestionByGroupId(Long id);
}
