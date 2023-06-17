package cn.pqz.emsboot.modules.sys.contoller;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.Role;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.entity.User_role;
import cn.pqz.emsboot.modules.sys.mapper.RoleMapper;
import cn.pqz.emsboot.modules.sys.mapper.UserMapper;
import cn.pqz.emsboot.modules.sys.mapper.User_roleMapper;
import cn.pqz.emsboot.modules.sys.service.RoleService;
import cn.pqz.emsboot.modules.sys.service.UserService;
import cn.pqz.emsboot.modules.sys.service.User_roleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("/employee")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private User_roleService user_roleService;
    private static final Logger logger=Logger.getLogger(UserController.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");//定义时间格式，用作文件夹命名
    /**
     * 展示用户列表
     * @param pageNum
     * @param size
     * @param query
     * @return
     */
    @GetMapping("/userList/")
    public RespBean userList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("size") Integer size,
                             @RequestParam("query") String query){
        JSONObject obj=new JSONObject();
        obj.put("data",userService.userList(pageNum,size,query));
        obj.put("total",userService.count(null));
        RespBean resp=RespBean.ok("",obj);
//        logger.info("----------获取用户列表成功----------");
        return resp;
    }

    /**
     * 改变用户状态enable
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/updateEnabled/{userId}/{userEnabled}")
    public RespBean updateEnabled(@PathVariable("userId") Integer id,
                                  @PathVariable("userEnabled") Boolean enabled,
                                  HttpServletRequest request){
        RespBean respBean=null;
        User user=new User();
        user.setId(id);
        user.setEnabled(enabled);
//        SecurityContextImpl securityContext=(SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        Authentication authentication=securityContext.getAuthentication();
//        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user,authentication.getCredentials());
//        auth.setDetails(authentication.getDetails());
//        securityContext.setAuthentication(auth);
        Boolean i=userService.updateById(user);
        if (i){
            respBean=RespBean.ok("更新状态成功");
            logger.info("----------用户状态更新成功----------");
        }else{
            respBean=RespBean.error("更新状态失败");
        }
        return respBean;
    }
    @PostMapping("/addUser")
    public RespBean addUser(@RequestBody User user){
        RespBean respBean=null;
        String phone=user.getPhone();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("phone",phone);
        User user1=userService.getOne(queryWrapper);
        if (!Objects.isNull(user1)){
            respBean=RespBean.error(phone+"已经注册");
        }else {
            int i=userService.addUser(user);
            if (i!=0){
                respBean=RespBean.ok("添加成功");
            }else{
                respBean=RespBean.error("添加失败");
            }
        }
        return respBean;
    }
    @PutMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user){
        RespBean respBean=null;
        Boolean i=userService.updateById(user);
        if (i){
            respBean=RespBean.ok("修改成功");
        }else {
            respBean=RespBean.error("修改失败");
        }
        return respBean;
    }
    @DeleteMapping("/deleteUser/{id}")
    public RespBean deleteUser(@PathVariable("id") Integer id){
        RespBean respBean=null;
        Boolean i=userService.removeById(id);
        if (i){
            respBean=RespBean.ok("删除成功");
        }else {
            respBean=RespBean.error("删除失败");
        }
        return respBean;
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping("/allRole")
    public RespBean allRole(){
        RespBean respBean=null;
        List<Role> allRole = roleService.list();
        if (allRole!=null)
            respBean=RespBean.ok("",allRole);
        else
            respBean=RespBean.error("获取错误");
        return respBean;
    }

    /**
     * 分配角色
     * @param uid
     * @param rid
     * @return
     */
    @PutMapping("/addUr/{uid}/{rid}")
    public RespBean addUr(@PathVariable("uid") Integer uid,
                          @PathVariable("rid") Integer rid){
        RespBean respBean=null;
        QueryWrapper query=new QueryWrapper();
        query.eq("uid",uid);
        user_roleService.remove(query);
        User_role ur=new User_role();
        User user=userService.getById(uid);
        Role role=roleService.getById(rid);
        user.setRoleName(role.getNameZh());//在user表中设置名称
        userService.updateById(user);//更新user表
        ur.setUid(uid);
        ur.setRid(rid);
        Boolean i = user_roleService.save(ur);
        if (i)
            respBean=RespBean.ok("分配角色成功");
        else
            respBean=RespBean.error("分配角色失败");
        return respBean;

    }

    @PostMapping("/upload/{id}")
    public RespBean upload(@RequestParam("file") MultipartFile uploadFile,
                           @PathVariable("id") Integer id,
                           HttpServletRequest req){
        RespBean respBean=null;
//        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String realPath = "D:/uploadFile/";
        User user=userService.getById(id);
//        System.out.println(realPath);//realPath为绝对路径
        String format = sdf.format(new Date());//定义一个时间点并格式化
        File folder = new File(realPath + format);//新建文件夹文件夹命名为uploadFile/时间
        if (!folder.isDirectory()) {
            folder.mkdirs(); //创建多级目录
        }
        String oldName = uploadFile.getOriginalFilename();//getOriginalFilename得到上传时的文件名
//       System.out.println(oldName);
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),
                oldName.length());
        try {
            uploadFile.transferTo(new File(folder, newName));//将文件保存到哪，文件名
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() +
                    "/uploadFile/" + format + newName;
            user.setUserface(filePath);
            userService.updateById(user);
            respBean=RespBean.ok("上传成功",filePath);

        } catch (Exception e) {
                e.printStackTrace();
                respBean=RespBean.error("上传失败");
        }
        return respBean;
    }
}
