package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.service.UtilService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/27 7:44 下午
 */
@RestController
@RequestMapping("/util")
public class UtilController {

    @Resource
    private UtilService utilService;

    @GetMapping("/allExpressName")
    public RespBean getAllExpressName(){
        return RespBean.ok("", utilService.getAllExpressNames());
    }
}
