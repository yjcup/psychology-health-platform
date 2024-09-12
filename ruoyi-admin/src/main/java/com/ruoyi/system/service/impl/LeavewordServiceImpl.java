package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.LeavewordMapper;
import com.ruoyi.system.domain.Leaveword;
import com.ruoyi.system.service.ILeavewordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 留言管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-06
 */
@Service
public class LeavewordServiceImpl implements ILeavewordService 
{
    @Autowired
    private LeavewordMapper leavewordMapper;

    /**
     * 查询留言管理
     * 
     * @param id 留言管理主键
     * @return 留言管理
     */
    @Override
    public Leaveword selectLeavewordById(Long id)
    {
        return leavewordMapper.selectLeavewordById(id);
    }

    /**
     * 查询留言管理列表
     * 
     * @param leaveword 留言管理
     * @return 留言管理
     */
    @Override
    public List<Leaveword> selectLeavewordList(Leaveword leaveword)
    {
        return leavewordMapper.selectLeavewordList(leaveword);
    }

    /**
     * 新增留言管理
     * 
     * @param leaveword 留言管理
     * @return 结果
     */
    @Override
    public int insertLeaveword(Leaveword leaveword)
    {
        leaveword.setCreateTime(DateUtils.getNowDate());
        return leavewordMapper.insertLeaveword(leaveword);
    }

    /**
     * 修改留言管理
     * 
     * @param leaveword 留言管理
     * @return 结果
     */
    @Override
    public int updateLeaveword(Leaveword leaveword)
    {
        return leavewordMapper.updateLeaveword(leaveword);
    }

    /**
     * 批量删除留言管理
     * 
     * @param ids 需要删除的留言管理主键
     * @return 结果
     */
    @Override
    public int deleteLeavewordByIds(String ids)
    {
        return leavewordMapper.deleteLeavewordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除留言管理信息
     * 
     * @param id 留言管理主键
     * @return 结果
     */
    @Override
    public int deleteLeavewordById(Long id)
    {
        return leavewordMapper.deleteLeavewordById(id);
    }
}
