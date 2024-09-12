package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Message;

import java.util.List;

/**
 * 消息记录Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-06
 */
public interface MessageMapper
{
    /**
     * 查询消息记录
     *
     * @param id 消息记录主键
     * @return 消息记录
     */
    public Message selectMessageById(Long id);

    /**
     * 查询消息记录列表
     *
     * @param message 消息记录
     * @return 消息记录集合
     */
    public List<Message> selectMessageList(Message message);

    /**
     * 新增消息记录
     *
     * @param message 消息记录
     * @return 结果
     */
    public int insertMessage(Message message);

    /**
     * 修改消息记录
     *
     * @param message 消息记录
     * @return 结果
     */
    public int updateMessage(Message message);

    /**
     * 删除消息记录
     *
     * @param id 消息记录主键
     * @return 结果
     */
    public int deleteMessageById(Long id);

    /**
     * 批量删除消息记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMessageByIds(String[] ids);
}
