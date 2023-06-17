package cn.pqz.emsboot.modules.communication.controller;


import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    UserService userService;

    @GetMapping("/addressBook/")
    public RespBean addressBook(@RequestParam("name") String name){
        RespBean respBean = null;
        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.like("name",name);
//            queryWrapper.like("username",name);
            List<User> userList = userService.list(queryWrapper);
            if (userList.size()==0){
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.like("username",name);
                userList=userService.list(queryWrapper1);
            }
            respBean=RespBean.ok("",userList);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("通讯录获取失败");
        }
        return respBean;
    }


}
