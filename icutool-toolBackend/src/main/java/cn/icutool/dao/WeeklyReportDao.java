package cn.icutool.dao;

import cn.icutool.domain.entity.WeeklyReport;
import cn.icutool.mapper.WeeklyReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author <a href="https://icutool.cn">旧年</a>
 * @since 2024-05-21
 */
@Service
public class WeeklyReportDao extends ServiceImpl<WeeklyReportMapper, WeeklyReport> {

    public List<WeeklyReport> queryPage(Integer pageNum, Integer pageSize, String keyword, Long userId){
        return baseMapper.queryPage(pageNum, pageSize, keyword, userId);
    }

    public int count(String keyword, Long userId){
        LambdaQueryWrapper<WeeklyReport> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(WeeklyReport::getUserId, userId);
        // 判断 keyword 是否为空字符串或 null
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like(WeeklyReport::getSubject, keyword)
                    .or()
                    .like(WeeklyReport::getContent, keyword);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    public int update(WeeklyReport weeklyReport){
        return baseMapper.update(weeklyReport);
    }
    public List<WeeklyReport> list(LocalDate time, Long userId){
        return lambdaQuery()
                .ge(WeeklyReport::getCreateTime, time)
                .eq(WeeklyReport::getUserId, userId)
                .orderByAsc(WeeklyReport::getCreateTime)
                .list();
    }
}
