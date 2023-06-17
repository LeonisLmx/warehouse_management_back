package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.modules.output.entity.Client;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.ClientMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
    public class ClientService extends ServiceImpl<ClientMapper,Client> {
        @Autowired
        private ClientMapper clientMapper;
        @Autowired
        private OrderMapper orderMapper;

        public List<OrderList> getOrderByCid(Integer id){
            return orderMapper.getOrderByCid(id);
        }
        public List<Client> getClientList(String name){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("enabled",true);
            queryWrapper.like("name",name);
            List<Client> clientList = clientMapper.selectList(queryWrapper);
            return clientList;
        }

        /**
         * 加入黑名单
         * @param id 客户id
         * @return i 操作的数量
         */
        public Integer enterBlack(Integer id){
            Client client=clientMapper.selectById(id);
            Boolean enabled1=!client.getEnabled();
            client.setEnabled(enabled1);
            int i=clientMapper.updateById(client);
            return i;
        }

}
