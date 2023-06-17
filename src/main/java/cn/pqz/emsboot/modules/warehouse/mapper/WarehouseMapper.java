package cn.pqz.emsboot.modules.warehouse.mapper;

import cn.pqz.emsboot.modules.warehouse.entity.Position;
import cn.pqz.emsboot.modules.warehouse.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {
    public List<Warehouse> myShelf(Integer id);
    public List<Position> position(Integer id);

}
