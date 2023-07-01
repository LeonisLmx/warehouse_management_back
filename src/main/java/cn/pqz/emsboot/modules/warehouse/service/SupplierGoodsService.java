package cn.pqz.emsboot.modules.warehouse.service;

import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.SupplierGoods;
import cn.pqz.emsboot.modules.warehouse.mapper.GoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.SupplierGoodsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierGoodsService {

    @Resource
    private SupplierGoodsMapper supplierGoodsMapper;

    @Resource
    private WarehouseService warehouseService;

    public List<SupplierGoods> list(){
        return supplierGoodsMapper.selectList(new QueryWrapper<>());
    }

    public void insertGoods(SupplierGoods supplierGoods){
        supplierGoodsMapper.insert(supplierGoods);
    }

    /**
     * 将供应商货物入库，对应goodsId
     * @param goodsId
     * @param id
     */
    public void goodsEnterWarehouse(Long substationId, Long id){
        SupplierGoods supplierGoods = supplierGoodsMapper.selectById(id);
        Goods goods = new Goods();
        warehouseService.enter();
        SupplierGoods res = new SupplierGoods();
        // todo 需要供应商货物的主键
        res.setGoodsId(goods.getId().longValue());
        supplierGoodsMapper.update(res, new QueryWrapper<SupplierGoods>().eq("id",id));
    }
}
