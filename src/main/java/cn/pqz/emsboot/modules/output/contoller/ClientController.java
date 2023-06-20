package cn.pqz.emsboot.modules.output.contoller;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.output.entity.Client;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.service.ClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * 获取客户列表
     * @param name
     * @return
     */
    @GetMapping("/clientList/")
    public RespBean getClientList(@RequestParam(value = "clientName", required = false) String name,
                                  @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                  @RequestParam(value = "cardNumber", required = false) String cardNumber) {
        try {
           List<Client> clientList = clientService.getClientList(name, phoneNumber, cardNumber);
           return RespBean.ok("", clientList);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("获取客户列表失败");
        }
    }

    @GetMapping("/client/{id}")
    public RespBean getClientById(@PathVariable("id")Long id){
        return RespBean.ok("", clientService.getClientById(id));
    }

    /**
     * 获取用户的订单
     * @param id
     * @return
     */
    @GetMapping("/getOrderByCid/{id}")
    public RespBean getOrderById(@PathVariable("id") Integer id){
        RespBean respBean=null;
        try{
            List<OrderList> orders = clientService.getOrderByCid(id);
            respBean=RespBean.ok("",orders);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("订单查询失败");
        }
        return respBean;
    }

    @PostMapping("/addClient")
    public RespBean addClient(@RequestBody Client client) {
        boolean i = clientService.save(client);
        return i? RespBean.ok("添加成功"):RespBean.error("添加失败");

    }
    @PutMapping("/editClient")
    public RespBean editClient(@RequestBody Client client){
        RespBean respBean=null;
        Boolean i=clientService.updateById(client);
        if (i)
            respBean=RespBean.ok("修改成功");
        else
            respBean=RespBean.error("修改失败");
        return respBean;
    }

    /**
     * 加入黑名单
     * @param id
     * @return
     */
    @PutMapping("/enterBlacklist/{id}")
    public RespBean enterBlacklist(@PathVariable("id") Integer id){
        List<OrderList> orderByCid = clientService.getOrderByCid(id);
        if (CollectionUtils.isEmpty(orderByCid)){
            int i = clientService.enterBlack(id);
            if (i!=0) {
                return RespBean.ok("受理成功");
            }else {
                return RespBean.error("受理失败");
            }
        }else{
            return RespBean.error("客户存在历史订购订单，无法拉黑");
        }
    }


    /**
     * 查询黑名单
     * @return
     */
    @GetMapping("/getBlacklist")
    public RespBean getBlacklist(){
        RespBean respBean=null;
        try{QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("enabled",false);
        List<Client> clients=clientService.list(queryWrapper);
        respBean=RespBean.ok("",clients);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("查询失败");
        }
        return respBean;
    }


}
