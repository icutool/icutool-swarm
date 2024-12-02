package cn.icutool.service;

import cn.icutool.domain.AutoReply;
import cn.icutool.domain.vo.request.AutoReplyReq;
import cn.icutool.common.domain.vo.response.AjaxResult;

import java.util.List;

/**
 * @author xietao
 */
public interface AutoReplyService{

    int deleteByPrimaryKey(Integer id);

    int insert(AutoReply record);

    int insertSelective(AutoReply record);

    List<AutoReply> selectAll();

    AutoReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AutoReply record);

    int updateByPrimaryKey(AutoReply record);

    void initHotAutoRespData();

    AjaxResult delete(List<Integer> ids);

    AjaxResult page(Integer pageNum, Integer pageSize, String keyword);

    AjaxResult addAutoReply(AutoReplyReq autoReplyReq);

    AjaxResult edit(AutoReplyReq autoReplyReq);
}
