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

    @Resource
    private GoodsService goodsService;

    public List<SupplierGoods> list(){
        return supplierGoodsMapper.selectList(new QueryWrapper<>());
    }

    public int insertGoods(SupplierGoods supplierGoods){
        return supplierGoodsMapper.insert(supplierGoods);
    }

    /**
     * 将供应商货物入库，对应goodsId
     * @param goodsId
     * @param id
     */
    public int goodsEnterWarehouse(Long substationId, Long id){
        SupplierGoods res = new SupplierGoods();
        res.setGoodsId(goodsService.supplierGoodsEnter(supplierGoodsMapper.selectById(id), substationId).longValue());
        return supplierGoodsMapper.update(res, new QueryWrapper<SupplierGoods>().eq("id",id));
    }
}
