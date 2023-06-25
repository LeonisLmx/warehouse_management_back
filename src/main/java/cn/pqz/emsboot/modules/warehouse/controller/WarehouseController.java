package cn.pqz.emsboot.modules.warehouse.controller;

import cn.pqz.emsboot.modules.output.entity.Transition;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.Position;
import cn.pqz.emsboot.modules.warehouse.entity.Warehouse;
import cn.pqz.emsboot.modules.warehouse.service.WarehouseService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    private final Logger logger = Logger.getLogger(WarehouseController.class);

    /**
     * 获取仓库信息
     *
     * @return
     */
    @GetMapping("/myWarehouse")
    public RespBean myTransitions() {
        RespBean respBean = null;
        try {
            List<Warehouse> warehouses = warehouseService.myTransitions();
            respBean = RespBean.ok("", warehouses);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("仓库数据获取失败");
        }
        return respBean;
    }

    /**
     * 库区
     *
     * @param id
     * @return
     */
    @GetMapping("/myRegion/{id}")
    public RespBean myRegion(@PathVariable("id") Integer id) {
        RespBean respBean = null;
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("parentId", id);
            List<Warehouse> regions = warehouseService.list(queryWrapper);
            respBean = RespBean.ok("", regions);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("获取货区数据失败");
        }
        return respBean;
    }

    /**
     * 货架
     *
     * @param id
     * @return
     */
    @GetMapping("/myShelf/{id}")
    public RespBean myShelf(@PathVariable("id") Integer id) {
        RespBean respBean = null;
        try {
            List<Warehouse> shelf = warehouseService.myShelf(id);
            respBean = RespBean.ok("", shelf);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("获取货架数据失败");
        }
        return respBean;
    }

    /**
     * 新建货架
     */
    @PostMapping("/addShelf")
    public RespBean addShelf(@RequestBody Warehouse warehouse) {
        RespBean respBean = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("num", warehouse.getNum());
        queryWrapper.eq("parentId", warehouse.getParentId());
        if (warehouseService.getOne(queryWrapper) != null) {
            respBean = RespBean.error("编号重复，请重新设置编号");
            logger.info("----------编号" + warehouse.getNum() + "重复----------");
        } else {
            try {
                warehouseService.addShelf(warehouse);
                respBean = RespBean.ok("新建货架成功");
            } catch (Exception e) {
                e.printStackTrace();
                respBean = RespBean.error("新建货架失败");
            }
        }
        return respBean;
    }

    /**
     * 远程查询完成货物信息
     */
    @GetMapping("/transitions/")
    public RespBean transitions(@RequestParam("name") String name,
                                @RequestParam("state") Integer state) {
        RespBean respBean = null;
        try {
            List<Transition> transitions = warehouseService.transitions(name,state);
            respBean = RespBean.ok("", transitions);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("远程查询货物信息失败");
        }
        return respBean;
    }

    /**
     * 级联选择器位置内容
     */
    @GetMapping("/position/")
    public RespBean position(@RequestParam Integer id) {
        RespBean respBean = null;
        try {
            List<Position> positions = warehouseService.Position(id);
            respBean = RespBean.ok("", positions);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("级联选择器中内容获取失败");
        }
        return respBean;
    }

    /**
     * 暂存数据
     * @param json
     * @return
     */
    @PostMapping("/store")
    public RespBean store(@RequestBody JSONObject json) {
        RespBean respBean = null;
        try {
            respBean=warehouseService.store(json);
            if (respBean==null){
                respBean=RespBean.ok("存储成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("存入失败");
        }
        return respBean;
    }

    @PostMapping("/addWarehouse")
    public RespBean addWarehouse(@RequestBody Warehouse warehouse) {
        RespBean respBean = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("num", warehouse.getNum());
        queryWrapper.eq("parentId", warehouse.getParentId());
        if (warehouseService.getOne(queryWrapper) != null) {
            respBean = RespBean.error("编号重复，请重新设置编号");

        }else {
            Boolean i = warehouseService.save(warehouse);
            if (i){
                respBean = RespBean.ok("新建成功");
            }else {
                respBean=RespBean.error("新建失败");
            }
        }

        return respBean;
    }
    @PutMapping("/updateWarehouse")
    public RespBean updateWarehouse(@RequestBody Warehouse warehouse){
        RespBean respBean=null;
        Boolean i=warehouseService.updateById(warehouse);
        if (i){
            respBean=RespBean.ok("更新仓库信息成功");
        }else {
            respBean=RespBean.error("更新仓库信息失败");
        }
        return respBean;
    }
    @DeleteMapping("/deleteWarehouseById/{id}")
    public RespBean deleteWarehouseById(@PathVariable("id") Integer id){
        RespBean respBean=null;
        Boolean i=warehouseService.removeById(id);
        if (i){
            respBean=RespBean.ok("删除仓库成功");
        }else {
            respBean=RespBean.error("删除仓库失败");
        }
        return respBean;
    }

    @GetMapping("/getWarehouseId")
    public RespBean getWarehouseId(){
        RespBean respBean=null;
        try{
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("type",1);
            queryWrapper.notIn("id",2);
            List<Warehouse> warehouses=warehouseService.list(queryWrapper);
            respBean=RespBean.ok("",warehouses);
        }catch (Exception e){
            respBean=RespBean.error("获取仓库数据失败");
            e.printStackTrace();
        }
        return respBean;
    }

    /**
     * 入库
     * @return
     */
    @PostMapping("/enter")
    public RespBean enter(@RequestBody Goods goods){
        RespBean respBean = warehouseService.enter(goods);
        return respBean;
    }

}
