package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Test;

/**
 * 测试Service接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface ITestService 
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
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键集合
     * @return 结果
     */
    public int deleteTestByIds(String ids);

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    public int deleteTestById(Long id);
}
