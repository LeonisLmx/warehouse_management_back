package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.entity.Supplier;
import cn.pqz.emsboot.modules.business.service.SupplierService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private SupplierService service;

    @GetMapping("/search")
    public RespBean search(@RequestParam(value = "name",required = false)String name){
        return RespBean.ok("", service.search(name));
    }

    @PostMapping("/new")
    public RespBean newRecord(@RequestBody Supplier supplier){
        return RespBean.ok("新增供应商成功", service.add(supplier));
    }

    @PostMapping("/update")
    public RespBean update(@RequestBody Supplier supplier){
        return RespBean.ok("修改供应商信息成功", service.update(supplier));
    }

    @PostMapping("/delete")
    public RespBean delete(@RequestParam("id")Long id){
        return RespBean.ok("删除成功", service.delete(id));
    }
}
