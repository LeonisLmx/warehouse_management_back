package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/27 7:44 下午
 */
@Service
public class UtilService {

    @Resource
    private OrderListMapper orderListMapper;

    public List<String> getAllExpressNames(){
        return orderListMapper.getAllExpressName();
    }
}
