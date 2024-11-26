package cn.icutool.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author xietao
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
 
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime currentDate = LocalDateTime.now();
        // 默认未删除
        setFieldValByName("delFlag", Boolean.FALSE,metaObject);
        // 创建时间默认当前时间
        setFieldValByName("createTime", currentDate,metaObject);
        setFieldValByName("updateTime", currentDate,metaObject);
    }
 
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime currentDate = LocalDateTime.now();
        // 修改时间
        setFieldValByName("updateTime",currentDate,metaObject);
    }
}