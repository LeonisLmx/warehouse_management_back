package cn.pqz.emsboot.modules.sys.contoller;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.Role;
import cn.pqz.emsboot.modules.sys.mapper.Menu_roleMapper;
import cn.pqz.emsboot.modules.sys.mapper.RoleMapper;
import cn.pqz.emsboot.modules.sys.service.Menu_roleService;
import cn.pqz.emsboot.modules.sys.service.RoleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private Menu_roleService menuRoleService;

    /**
     * 角色列表(更新处理方法)
     * @return
     */
    @GetMapping("/roleList")
    public RespBean roleList(){
        RespBean respBean=null;
        try{
            List<Role> roleList=roleService.getRoleList();
            respBean=RespBean.ok("",roleList);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取角色列表失败");
        }
        return respBean;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public RespBean addRole(@RequestBody Role role){
        RespBean respBean=null;
        int i=roleService.addRole(role);
        if (i!=0){
            respBean=RespBean.ok("添加角色成功");
        }else
            respBean=RespBean.error("添加失败");
        return respBean;
    }

    /**
     * 更新角色列表
     * @param role
     * @return
     */
    @PutMapping("/editRole")
    public RespBean editRole(@RequestBody Role role){
        RespBean respBean=null;
//        role.setId(role.getId());
//        System.out.println(role);
        int i=roleService.editRole(role);
        if (i!=0){
            respBean=RespBean.ok("修改成功");
        }else
            respBean=RespBean.error("修改失败");
        return respBean;
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/deleteRole/{id}")
    public RespBean deleteRole(@PathVariable("id") Integer id){
        RespBean respBean=null;
        Boolean i=roleService.removeById(id);
        if (i){
            respBean=RespBean.ok("删除成功");
        }else
            respBean=RespBean.error("删除失败");
        return respBean;
    }
    @DeleteMapping("/deletePow/{rid}/{mid}")
    public RespBean deletePow(@PathVariable("rid") Integer rid,
                              @PathVariable("mid") Integer mid){
        RespBean respBean=null;
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("rid",rid);
        queryWrapper.eq("mid",mid);
        Boolean i=menuRoleService.remove(queryWrapper);
        if (i){
            respBean=RespBean.ok("删除成功");
        }else
            respBean=RespBean.error("删除失败");
        return respBean;
    }

    /**
     * 为角色分配权限
     * @param json
     * @return
     */
    @PostMapping("/assPow")
    public RespBean assPow(@RequestBody JSONObject json){
        RespBean respBean=null;
        try{
            String rid=json.getString("roleId");
            String mids=json.getString("mids");
            menuRoleService.assPow(rid,mids);
            respBean=RespBean.ok("授权成功");
        }catch (Exception e){
            respBean=RespBean.error("授权失败");
            e.printStackTrace();
        }
        return respBean;
    }
}
