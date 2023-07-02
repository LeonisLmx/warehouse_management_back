package cn.pqz.emsboot.modules.warehouse.mapper;

import cn.pqz.emsboot.modules.warehouse.entity.SupplierGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SupplierGoodsMapper extends BaseMapper<SupplierGoods> {

    @Select({
            "select sg.*,s.name as supplierName  from supplier_goods sg  left join supplier s on sg.supplierId  = s.id "
    })
    List<Map<String,Object>> searchList();

    @Select({
            "select  count(0) as counts,sg.operateId,u.name  from supplier_goods sg left join `user` u on sg.operateId = u.id  group by sg.operateId order by counts desc  limit 5"
    })
    List<Map<String,Object>> staticsOperates();
}
