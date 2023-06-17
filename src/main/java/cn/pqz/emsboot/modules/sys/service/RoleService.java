package cn.pqz.emsboot.modules.sys.service;

import cn.pqz.emsboot.modules.sys.entity.Role;
import cn.pqz.emsboot.modules.sys.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class RoleService extends ServiceImpl<RoleMapper,Role> {
    @Autowired
    private RoleMapper roleMapper;
    @CacheEvict(allEntries = true)
    public int addRole(Role role){
        String name="ROLE_"+role.getName();
        role.setName(name);
        int i=roleMapper.insert(role);
        return i;
    }
    @CacheEvict(allEntries = true)
    public int editRole(Role role){
        int i=roleMapper.updateById(role);
        return i;
    }
    public List<Role> getRoleList(){
        return roleMapper.getRoleList();
    }
}
