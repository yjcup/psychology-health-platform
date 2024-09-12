package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Question;

/**
 * 问题表Service接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface IQuestionService 
{
    /**
     * 查询问题表
     * 
     * @param id 问题表主键
     * @return 问题表
     */
    public Question selectQuestionById(Long id);

    /**
     * 查询问题表列表
     * 
     * @param question 问题表
     * @return 问题表集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增问题表
     * 
     * @param question 问题表
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改问题表
     * 
     * @param question 问题表
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 批量删除问题表
     * 
     * @param ids 需要删除的问题表主键集合
     * @return 结果
     */
    public int deleteQuestionByIds(String ids);

    /**
     * 删除问题表信息
     * 
     * @param id 问题表主键
     * @return 结果
     */
    public int deleteQuestionById(Long id);
}
