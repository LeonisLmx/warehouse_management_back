package cn.pqz.emsboot.modules.sys.service;

import cn.pqz.emsboot.modules.sys.entity.User_role;
import cn.pqz.emsboot.modules.sys.mapper.User_roleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class User_roleService extends ServiceImpl<User_roleMapper, User_role> {
    @Autowired
    User_roleMapper user_roleMapper;
    public boolean save(User_role entity) {
        int i = user_roleMapper.insert(entity);
        if (i!=0){
            log.info("分配角色");
            return true;
        } else {
            return false;
        }

    }
}
