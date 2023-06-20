package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.modules.output.entity.Client;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.ClientMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ClientService extends ServiceImpl<ClientMapper, Client> {
    @Resource
    private ClientMapper clientMapper;
    @Resource
    private OrderListMapper orderListMapper;

    public List<OrderList> getOrderByCid(Integer id) {
        return orderListMapper.getOrderByCid(id);
    }


    public List<Client> getClientList(String name, String phoneNumber, String cardNumber) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enabled", true);
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            queryWrapper.eq("phoneNumber", phoneNumber);
        }
        if (StringUtils.isNotBlank(cardNumber)) {
            queryWrapper.eq("cardNumber", cardNumber);
        }
        return clientMapper.selectList(queryWrapper);
    }

    public Client getClientById(Long id){
        return clientMapper.selectById(id);
    }

    /**
     * 加入黑名单
     *
     * @param id 客户id
     * @return i 操作的数量
     */
    public Integer enterBlack(Integer id) {
        Client client = clientMapper.selectById(id);
        Boolean enabled1 = !client.getEnabled();
        client.setEnabled(enabled1);
        return clientMapper.updateById(client);
    }

}
