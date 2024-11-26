package cn.icutool.dao;

import cn.icutool.domain.entity.User;
import cn.icutool.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDao extends ServiceImpl<UserMapper, User> {
    /**
     * 通过手机号查询用户
     */
    public User selectOneByMobile(String mobile){
        return lambdaQuery()
                .eq(User::getMobile,mobile)
                .one();
    }

    /**
     * 通过apiKey查询用户
     */
    public User selectOneByApiKey(String apiKey){
        return lambdaQuery()
                .eq(User::getApiKey,apiKey)
                .one();
    }

    /**
     *  通过用户名查询用户
     */
    public User selectOneByUsername(String username){
        return lambdaQuery()
                .eq(User::getUsername,username)
                .one();
    }

    public User selectOneByOpenId(String openId){
        return lambdaQuery()
                .eq(User::getOpenId,openId)
                .one();
    }

    public User selectOneByUsernameAndMobile(String username,String mobile) {
        return lambdaQuery()
                .eq(User::getUsername,username)
                .or()
                .eq(User::getMobile,mobile)
                .one();
    }

    @Cacheable(cacheNames = "UserInfo", key = "#userId", unless = "#userId == null")
    public User selectInfo(Long userId) {
        log.info("用户id：{} 正在查询用户信息", userId);
        return lambdaQuery()
                .eq(User::getId,userId)
                .select(User::getId,User::getNickName,User::getAvatar,User::getGeo)
                .one();
    }

    public void updateUserIpInfo(Long uid, String ip, String geo) {
        lambdaUpdate()
                .eq(User::getId,uid)
                .set(User::getIp,ip)
                .set(User::getGeo,geo)
                .update();
    }
}
