package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestRecordMapper;
import com.ruoyi.system.domain.TestRecord;
import com.ruoyi.system.service.ITestRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 测试记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Service
public class TestRecordServiceImpl implements ITestRecordService
{
    @Autowired
    private TestRecordMapper testRecordMapper;

    @Override
    public int insertTestRecord(List<String> res) {
        TestRecord testRecord = new TestRecord();//0 type 1 score 2 result 3 suggestion 4 userid
        testRecord.setUserId(Long.valueOf(res.get(4)));
        testRecord.setScore(Integer.valueOf(res.get(1)));
        testRecord.setResult(res.get(2));
        testRecord.setType(Integer.valueOf(res.get(0)));
        testRecord.setCreateTime(new Date());
        int result = testRecordMapper.insertTestRecord(testRecord);
        if (result==1){
            res.add(String.valueOf(testRecordMapper.selectCount()));
            res.add(String.valueOf(testRecordMapper.selectRank(testRecord.getScore(),testRecord.getType())));
        }
        return result;
    }

    /**
     * 查询测试记录
     *
     * @param id 测试记录主键
     * @return 测试记录
     */
    @Override
    public TestRecord selectTestRecordById(Long id)
    {
        return testRecordMapper.selectTestRecordById(id);
    }

    /**
     * 查询测试记录列表
     *
     * @param testRecord 测试记录
     * @return 测试记录
     */
    @Override
    public List<TestRecord> selectTestRecordList(TestRecord testRecord)
    {
        return testRecordMapper.selectTestRecordList(testRecord);
    }

    /**
     * 新增测试记录
     *
     * @param testRecord 测试记录
     * @return 结果
     */
    @Override
    public int insertTestRecord(TestRecord testRecord)
    {
        testRecord.setCreateTime(DateUtils.getNowDate());
        return testRecordMapper.insertTestRecord(testRecord);
    }

    /**
     * 修改测试记录
     *
     * @param testRecord 测试记录
     * @return 结果
     */
    @Override
    public int updateTestRecord(TestRecord testRecord)
    {
        return testRecordMapper.updateTestRecord(testRecord);
    }

    /**
     * 批量删除测试记录
     *
     * @param ids 需要删除的测试记录主键
     * @return 结果
     */
    @Override
    public int deleteTestRecordByIds(String ids)
    {
        return testRecordMapper.deleteTestRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测试记录信息
     *
     * @param id 测试记录主键
     * @return 结果
     */
    @Override
    public int deleteTestRecordById(Long id)
    {
        return testRecordMapper.deleteTestRecordById(id);
    }
}
