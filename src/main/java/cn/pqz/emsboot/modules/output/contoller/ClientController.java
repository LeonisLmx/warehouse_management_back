package cn.pqz.emsboot.modules.output.contoller;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.output.entity.Client;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.ClientMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import cn.pqz.emsboot.modules.output.service.ClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RespBean getClientList(@RequestParam("clientName") String name) {
        RespBean respBean = null;
        try {
           List<Client> clientList = clientService.getClientList(name);
           respBean = RespBean.ok("", clientList);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("获取客户列表失败");
        }
        return respBean;
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
            List<OrderList> orders=clientService.getOrderByCid(id);
            respBean=RespBean.ok("",orders);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("订单查询失败");
        }
        return respBean;
    }

    @PostMapping("/addClient")
    public RespBean addClient(@RequestBody Client client) {
        RespBean respBean = null;
        Boolean i = clientService.save(client);
        if (i) {
            respBean = RespBean.ok("添加成功");
        } else
            respBean = RespBean.error("添加失败");
        return respBean;
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
        RespBean respBean=null;
        int i=clientService.enterBlack(id);
        if (i!=0)
            respBean=RespBean.ok("受理成功");
        else
            respBean=RespBean.error("受理失败");
        return respBean;
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
