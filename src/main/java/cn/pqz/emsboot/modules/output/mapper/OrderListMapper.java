package cn.pqz.emsboot.modules.output.mapper;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderListMapper extends BaseMapper<OrderList> {
    List<OrderList> getOrderByCid(Integer id);

    List<Map<String,Object>> aggregateData(@Param("startTime") Date startTime,
                                           @Param("endTime")Date endTime,
                                           @Param("operateId")Long operateId);

    List<String> getAllExpressName();

    List<Map<String, Object>> staticsOrders();

    List<Map<String, Object>> querySupplierSettlement(@RequestParam("supplierId")Long supplierId);

    List<Map<String, Object>> querySubstationSettlement(@RequestParam("startDate")Date startDate,
                                                        @RequestParam("endDate")Date endDate,
                                                        @RequestParam("substationId")Long substationId);
}
