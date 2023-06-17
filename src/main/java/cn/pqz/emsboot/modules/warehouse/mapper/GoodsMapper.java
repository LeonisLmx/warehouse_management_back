package cn.pqz.emsboot.modules.warehouse.mapper;

import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    public List<Goods> enterList();
}
