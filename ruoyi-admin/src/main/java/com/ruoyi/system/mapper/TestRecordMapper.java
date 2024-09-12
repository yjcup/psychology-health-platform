package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TestRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 测试记录Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-06
 */
public interface TestRecordMapper
{
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
     * 删除测试记录
     *
     * @param id 测试记录主键
     * @return 结果
     */
    public int deleteTestRecordById(Long id);

    /**
     * 批量删除测试记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestRecordByIds(String[] ids);

    public Integer selectCount();

    public Integer selectRank(@Param("score")Integer score,@Param("type")Integer type);

}
