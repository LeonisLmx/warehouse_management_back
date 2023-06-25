package cn.pqz.emsboot.modules.finance.controller;

import cn.pqz.emsboot.modules.finance.service.FinanceService;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/25 4:18 下午
 */
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Resource
    private FinanceService financeService;

    @Resource
    private OrderListService orderListService;

    @GetMapping("/search")
    public RespBean search(@RequestParam("role")Integer role){
        return RespBean.ok("", financeService.search(role));
    }

    @GetMapping("/invoice")
    public RespBean invoiceList(){
        return RespBean.ok("", orderListService.queryInvoiceList());
    }
}
