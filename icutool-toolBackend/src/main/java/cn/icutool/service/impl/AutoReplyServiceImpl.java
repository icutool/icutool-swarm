package cn.icutool.service.impl;

import cn.icutool.config.CacheConfig;
import cn.icutool.domain.AutoReply;
import cn.icutool.domain.dto.PageDTO;
import cn.icutool.domain.vo.request.AutoReplyReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.mapper.AutoReplyMapper;
import cn.icutool.service.AutoReplyService;
import cn.icutool.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static cn.icutool.config.CacheConfig.HOT_REPLY_MAP;

@Slf4j
@Service
public class AutoReplyServiceImpl implements AutoReplyService{

    @Resource
    private AutoReplyMapper autoReplyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return autoReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AutoReply record) {
        return autoReplyMapper.insert(record);
    }

    @Override
    public int insertSelective(AutoReply record) {
        return autoReplyMapper.insertSelective(record);
    }

    @Override
    public List<AutoReply> selectAll() {
        return autoReplyMapper.selectAll();
    }

    @Override
    public AutoReply selectByPrimaryKey(Integer id) {
        return autoReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AutoReply record) {
        return autoReplyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AutoReply record) {
        return autoReplyMapper.updateByPrimaryKey(record);
    }

    @Override
    public void initHotAutoRespData() {
        log.info("初始化自动回复数据 时间:{}", LocalDateTime.now());
        HOT_REPLY_MAP.clear();
        List<AutoReply> autoReplies = this.selectAll();
        autoReplies.forEach(autoReply -> HOT_REPLY_MAP.put(autoReply.getQuestion(), autoReply.getAnswer()));
    }

    @Override
    public AjaxResult delete(List<Integer> ids) {
        autoReplyMapper.removeByIds(ids);
        synchronized (CacheConfig.HOT_REPLY_MAP) {
            initHotAutoRespData();
        }
        return AjaxResult.success("删除成功");
    }

    @Override
    public AjaxResult page(Integer pageNum, Integer pageSize, String keyword) {
        log.debug("查询自动回复列表");
        // 判断是否是管理员
        if (SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            pageNum = (pageNum -1) * pageSize;
            List<AutoReply> autoReplyList = autoReplyMapper.queryPage(pageNum, pageSize, keyword);
            int count = autoReplyMapper.count(keyword);
            PageDTO<AutoReply> resp = new PageDTO<>(autoReplyList, count);
            return AjaxResult.success("分页查询成功",resp);
        } else {
            return AjaxResult.error("非管理员 无法操作");
        }
    }

    @Override
    public AjaxResult addAutoReply(AutoReplyReq autoReplyReq) {
        AutoReply autoReply = new AutoReply();
        AutoReply autoReplyBySelect = autoReplyMapper.selectByQuestion(autoReplyReq.getQuestion());
        if (null == autoReplyBySelect){
            BeanUtils.copyProperties(autoReplyReq, autoReply);
            Date now = new Date();
            autoReply.setCreateTime(now);
            autoReply.setUpdateTime(now);
            autoReply.setDelFlag(Boolean.TRUE);
            autoReplyMapper.insert(autoReply);
            HOT_REPLY_MAP.put(autoReplyReq.getQuestion(), autoReplyReq.getAnswer());
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("问题重复");
        }
    }

    @Override
    public AjaxResult edit(AutoReplyReq autoReplyReq) {
        AutoReply autoReplyByPrimaryKey = autoReplyMapper.selectByPrimaryKey(autoReplyReq.getId());
        if (null != autoReplyByPrimaryKey) {
            AutoReply autoReply = autoReplyMapper.selectByQuestion(autoReplyReq.getQuestion());
            if (null != autoReply){
                return AjaxResult.error("要修改的问题重复,在数据库已存在");
            } else {
                AutoReply reply = new AutoReply();
                BeanUtils.copyProperties(autoReplyReq, reply);
                autoReplyMapper.updateByPrimaryKeySelective(reply);
                HOT_REPLY_MAP.remove(autoReplyByPrimaryKey.getQuestion());
                HOT_REPLY_MAP.put(autoReplyReq.getQuestion(), autoReplyReq.getAnswer());
                return AjaxResult.success("修改成功");
            }
        } else {
            return AjaxResult.error("id不存在");
        }
    }

}
