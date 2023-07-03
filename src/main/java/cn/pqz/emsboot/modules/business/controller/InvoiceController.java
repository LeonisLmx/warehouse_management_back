package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.service.InvoiceService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description
 * @date 2023/7/3 4:12 下午
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    @GetMapping("/list")
    public RespBean list(Integer status){
        return RespBean.ok("", invoiceService.search(status));
    }

    @PostMapping("/update")
    public RespBean update(@RequestBody JSONObject jsonObject){
        return RespBean.ok("修改发票状态成功", invoiceService.updateStatus(
                jsonObject.getInteger("status"),
                jsonObject.getLong("id")));
    }
}
