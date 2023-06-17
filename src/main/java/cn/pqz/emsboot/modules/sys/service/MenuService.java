package cn.pqz.emsboot.modules.sys.service;

import cn.pqz.emsboot.modules.sys.entity.Menu;
import cn.pqz.emsboot.modules.sys.mapper.MenuMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService extends ServiceImpl<MenuMapper,Menu> {
    private Logger logger=Logger.getLogger(getClass());
    @Autowired
    private MenuMapper menuMapper;
    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenus(){
        logger.info("权限列表已经存入缓存");
        return menuMapper.getAllMenus();
    }

    @CacheEvict(allEntries = true)
    public boolean updateById(Menu entity) {
        int b=menuMapper.updateById(entity);
        if (b==0){
            return false;
        }else {
            logger.info("权限列表已经从缓存中删除删除");
            return true;
        }
    }
    @CacheEvict(allEntries = true)
    public boolean save(Menu entity) {
        int b=menuMapper.insert(entity);
        if (b==0){
            return false;
        }else {
            return true;
        }
    }

   @CacheEvict(allEntries = true)
    public boolean removeById(Serializable id) {
        int i=menuMapper.deleteById(id);
        if (i==0){
            return false;
        }else {
            return true;
        }
    }


    public List<Menu> menuList(){return menuMapper.menuList();}
    public List<Menu> allMenuList(String name){return menuMapper.allMenuList(name);}
}
