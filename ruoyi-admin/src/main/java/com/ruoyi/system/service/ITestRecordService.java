package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TestRecord;

/**
 * 测试记录Service接口
 *
 * @author ruoyi
 * @date 2023-04-06
 */
public interface ITestRecordService
{


    public int insertTestRecord(List<String> res);


    /**
     * 查询测试记录
     *
     * @param id 测试记录主键
     * @return 测试记录
     */
    public TestRecord selectTestRecordById(Long id);

    /**
     * 查询测试记录列表
     *
     * @param testRecord 测试记录
     * @return 测试记录集合
     */
    public List<TestRecord> selectTestRecordList(TestRecord testRecord);

    /**
     * 新增测试记录
     *
     * @param testRecord 测试记录
     * @return 结果
     */
    public int insertTestRecord(TestRecord testRecord);

    /**
     * 修改测试记录
     *
     * @param testRecord 测试记录
     * @return 结果
     */
    public int updateTestRecord(TestRecord testRecord);

    /**
     * 批量删除测试记录
     *
     * @param ids 需要删除的测试记录主键集合
     * @return 结果
     */
    public int deleteTestRecordByIds(String ids);

    /**
     * 删除测试记录信息
     *
     * @param id 测试记录主键
     * @return 结果
     */
    public int deleteTestRecordById(Long id);
}
