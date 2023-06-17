package cn.pqz.emsboot.modules.sys.mapper;

import cn.pqz.emsboot.modules.sys.entity.Role;
import cn.pqz.emsboot.modules.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);

}