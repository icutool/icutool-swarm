package cn.icutool.dao;

import cn.icutool.domain.entity.UserFeedback;
import cn.icutool.mapper.UserFeedbackMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackDao  extends ServiceImpl<UserFeedbackMapper, UserFeedback> {
    public int add(UserFeedback userFeedback) {
        return baseMapper.insert(userFeedback);
    }
}
