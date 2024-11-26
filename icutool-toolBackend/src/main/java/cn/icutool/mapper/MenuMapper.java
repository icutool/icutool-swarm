package cn.icutool.mapper;


import cn.icutool.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过用户id查询权限
     * @return 权限列表
     */
    List<String> selectPermsByUserId(Long userId);
}
