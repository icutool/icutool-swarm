package cn.icutool.dao;

import cn.icutool.domain.entity.Menu;
import cn.icutool.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xietao
 */
@Service
public class MenuDao extends ServiceImpl<MenuMapper, Menu> {
    public List<String> selectPermsByUserId(Long id) {
        return super.baseMapper.selectPermsByUserId(id);
    }
}
