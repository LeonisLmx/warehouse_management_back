package cn.pqz.emsboot.modules.sys.contoller;

import cn.pqz.emsboot.modules.sys.entity.Menu;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.mapper.MenuMapper;
import cn.pqz.emsboot.modules.sys.mapper.RoleMapper;
import cn.pqz.emsboot.modules.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单列表
     * @return
     */
    @GetMapping("/menuList")
    public RespBean menuList(){
        RespBean result=null;
        try {
            List<Menu> menuList=menuService.menuList();
            result=RespBean.ok("",menuList);
        }catch (Exception e){
            e.printStackTrace();
            result=RespBean.error("获取菜单列表失败");
        }
        return result;
    }

    /**
     * 获取权限列表
     * @return
     */
    @GetMapping("/staff/powList/")
    public RespBean allMenuList(@RequestParam("query") String query){
        RespBean respBean=null;
        try{
            List<Menu> allMenuList=menuService.allMenuList(query);
            respBean=RespBean.ok("",allMenuList);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取权限列表失败");
        }
        return respBean;
    }

    /**
     * 是否在菜单栏显示
     */
    @PutMapping("/staff/changeMenuEnable/{id}/{enable}")
    public RespBean changeMenuEnable(@PathVariable("id") Integer id,
                                     @PathVariable("enable") Boolean enable){
        RespBean respBean=null;
        Menu menu=new Menu();
        menu.setId(id);
        menu.setEnable(enable);
        Boolean i=menuService.updateById(menu);
        if (i){
            respBean=RespBean.ok("更新状态成功");
        }else{
            respBean=RespBean.error("更新状态失败");
        }
        return respBean;
    }
    /**
     * 添加权限
     */
    @PostMapping("/staff/addPow")
    public RespBean addPow(@RequestBody Menu menu){
        RespBean respBean=null;
        Boolean i=menuService.save(menu);
        if (i){
            respBean=RespBean.ok("权限添加成功");
        }
        else {
            respBean=RespBean.error("权限添加失败");
        }
        return respBean;
    }
    /**
     * 修改权限
     */
    @PutMapping("/staff/editPow")
    public RespBean editPow(@RequestBody Menu menu){
        RespBean respBean=null;
        Boolean i=menuService.updateById(menu);
        if (i){
            respBean=RespBean.ok("权限修改成功");
        }
        else {
            respBean=RespBean.error("权限修改失败");
        }
        return respBean;
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/staff/deletePow/{id}")
    public RespBean deletePow(@PathVariable("id") Integer id ){
        RespBean respBean=null;
        Boolean i=menuService.removeById(id);
        if (i){
            respBean=RespBean.ok("权限删除成功");
        }
        else {
            respBean=RespBean.error("权限删除失败");
        }
        return respBean;
    }

}
