package cn.pqz.emsboot.modules.warehouse.mapper;

import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select({
            "SELECT codeName,sum(count) as totalCount,sum(remainCount) as remainCount from goods GROUP BY codeName"
    })
    List<Map<String,Object>> list();
}
