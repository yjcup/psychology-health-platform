package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Leaveword;

/**
 * 留言管理Service接口
 * 
 * @author ruoyi
 * @date 2023-04-06
 */
public interface ILeavewordService 
{
    /**
     * 查询留言管理
     * 
     * @param id 留言管理主键
     * @return 留言管理
     */
    public Leaveword selectLeavewordById(Long id);

    /**
     * 查询留言管理列表
     * 
     * @param leaveword 留言管理
     * @return 留言管理集合
     */
    public List<Leaveword> selectLeavewordList(Leaveword leaveword);

    /**
     * 新增留言管理
     * 
     * @param leaveword 留言管理
     * @return 结果
     */
    public int insertLeaveword(Leaveword leaveword);

    /**
     * 修改留言管理
     * 
     * @param leaveword 留言管理
     * @return 结果
     */
    public int updateLeaveword(Leaveword leaveword);

    /**
     * 批量删除留言管理
     * 
     * @param ids 需要删除的留言管理主键集合
     * @return 结果
     */
    public int deleteLeavewordByIds(String ids);

    /**
     * 删除留言管理信息
     * 
     * @param id 留言管理主键
     * @return 结果
     */
    public int deleteLeavewordById(Long id);
}
