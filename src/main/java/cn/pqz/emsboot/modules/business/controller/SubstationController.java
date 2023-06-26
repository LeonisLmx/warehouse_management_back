package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.service.SubstationService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
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

    @GetMapping("/list")
    public RespBean list(){
        return RespBean.ok("", service.list());
    }

    @GetMapping("/search/{id}")
    public RespBean search(@PathVariable("id")Long id){
        return RespBean.ok("", service.getById(id));
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody JSONObject jsonObject){
        service.insertRecord(jsonObject.getString("name"));
        return RespBean.ok("新增站点成功");
    }
}
