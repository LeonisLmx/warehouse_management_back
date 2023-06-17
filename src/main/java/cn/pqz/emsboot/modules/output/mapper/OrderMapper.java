package cn.pqz.emsboot.modules.output.mapper;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<OrderList> {
    List<OrderList> getOrderByCid(Integer id);
}
