package cn.pqz.emsboot.modules.warehouse.mapper;

import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select({
            "SELECT a.codeName,sum(a.count) as totalCount,sum(a.remainCount) as remainCount,s.name as substationName from goods a left join substation s on a.substationId = s.id where a.substationId = #{substationId} GROUP BY codeName "
    })
    List<Map<String,Object>> list(@RequestParam("substationId")Long substationId);
}
