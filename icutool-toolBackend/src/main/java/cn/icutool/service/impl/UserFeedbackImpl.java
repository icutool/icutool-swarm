package cn.icutool.service.impl;

import cn.icutool.dao.UserFeedbackDao;
import cn.icutool.domain.entity.UserFeedback;
import cn.icutool.service.UserFeedbackService;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackImpl implements UserFeedbackService {
    private final UserFeedbackDao userFeedbackDao;

    public UserFeedbackImpl(UserFeedbackDao userFeedbackDao) {
        this.userFeedbackDao = userFeedbackDao;
    }

    @Override
    public Boolean add(UserFeedback userFeedback) {
        userFeedbackDao.add(userFeedback);
        return Boolean.TRUE;
    }
}
