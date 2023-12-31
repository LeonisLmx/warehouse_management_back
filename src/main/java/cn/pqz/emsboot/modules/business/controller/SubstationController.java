package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.service.SubstationService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.service.GoodsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description 分站管理
 * @date 2023/6/26 4:58 下午
 */
@RestController
@RequestMapping("/substation")
public class SubstationController {

    @Resource
    private SubstationService service;

    @Resource
    private GoodsService goodsService;

    @GetMapping("/list")
    public RespBean list(@RequestParam(value = "parentId",defaultValue = "0",required = false) Long parentId){
        return RespBean.ok("", service.listByParentId(parentId));
    }

    @GetMapping("/listCount")
    public RespBean listCount(@RequestParam(value = "id",required = false) Long id){
        return RespBean.ok("", service.listCount(id));
    }

    @GetMapping("/listAll")
    public RespBean listAdd(){
        return RespBean.ok("", service.listAll(0L));
    }

    @GetMapping("/search/{id}")
    public RespBean search(@PathVariable("id")Long id){
        return RespBean.ok("", service.getById(id));
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody JSONObject jsonObject){
        service.insertRecord(jsonObject.getString("name"), jsonObject.getLong("parentId"));
        return RespBean.ok("新增站点成功");
    }

    @GetMapping("/getFullSubstation")
    public RespBean getFullSubstation(Long goodsId){
        Long substationId = goodsService.searchById(goodsId).getSubstationId();
        StringBuilder res = service.getFullSubstationInfo(substationId, new StringBuilder());
        return RespBean.ok("", res.substring(1,res.length()));
    }


    @GetMapping("/listData")
    public RespBean listData(){
        return RespBean.ok("", service.listData());
    }
}
