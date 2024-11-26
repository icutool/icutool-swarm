package cn.icutool.service.impl;

import cn.icutool.dao.UserDao;
import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.domain.entity.User;
import cn.icutool.service.CommonUtilsService;
import cn.icutool.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CommonUtilsService commonUtilsService;
    @Override
    @Cacheable(key = "#apiKey", unless = "#result == null")
    public User getUserInfoByApiKey(String apiKey) {
        log.info("从数据库中通过apiKey获取用户信息");
        return userDao.selectOneByApiKey(apiKey);
    }
}
