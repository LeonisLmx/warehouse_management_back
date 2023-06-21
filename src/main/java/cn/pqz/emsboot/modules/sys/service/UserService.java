package cn.pqz.emsboot.modules.sys.service;

import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper,User> implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param username
     * @return 用户信息及其拥有的权限
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    /**
     * 分页查询
     *
     * @param pageNum 当前页数
     * @param size 当前页有多少条数据
     * @return
     */
    public List<User> userList(Integer pageNum, Integer size, String query) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", query);
        IPage<User> page = userMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        List<User> users = page.getRecords();
        return users;
    }

    /**
     * 添加用户
     * @param user 用户主体
     * @return 操作多少条数据
     */
    public Integer addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pawd = encoder.encode(user.getPassword());
        user.setPassword(pawd);
        int i = userMapper.insert(user);
        return i;
    }
}
