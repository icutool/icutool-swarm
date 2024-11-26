package cn.icutool.service;

import cn.icutool.domain.entity.User;

/**
 * 用户服务接口
 * @author xietao
 */
public interface UserService {
    User getUserInfoByApiKey(String apiKey);
}
