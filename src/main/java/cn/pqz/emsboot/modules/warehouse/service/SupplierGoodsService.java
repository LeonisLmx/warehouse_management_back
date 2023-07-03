package cn.pqz.emsboot.modules.warehouse.service;

import cn.pqz.emsboot.modules.business.entity.Supplier;
import cn.pqz.emsboot.modules.business.service.InvoiceService;
import cn.pqz.emsboot.modules.business.service.SupplierService;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.SupplierGoods;
import cn.pqz.emsboot.modules.warehouse.mapper.GoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.SupplierGoodsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class SupplierGoodsService {

    @Resource
    private SupplierGoodsMapper supplierGoodsMapper;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private InvoiceService invoiceService;

    @Resource
    private SupplierService supplierService;

    public List<Map<String,Object>> list(){
        return supplierGoodsMapper.searchList();
    }

    public int insertGoods(SupplierGoods supplierGoods){
        Supplier supplier = supplierService.searchById(supplierGoods.getSupplierId());
        invoiceService.insert(supplier.getName(), BigDecimal.valueOf(supplierGoods.getCount()).multiply(supplierGoods.getPrice()),
                "供应商供货：" + supplierGoods.getGoodsName() + "-" + supplierGoods.getCount() + supplierGoods.getMeasurement());
        return supplierGoodsMapper.insert(supplierGoods);
    }

    /**
     * 将供应商货物入库，对应goodsId
     * @param substationId
     * @param id
     */
    public int goodsEnterWarehouse(Long substationId, Long id){
        SupplierGoods res = new SupplierGoods();
        res.setGoodsId(goodsService.supplierGoodsEnter(supplierGoodsMapper.selectById(id), substationId).longValue());
        return supplierGoodsMapper.update(res, new QueryWrapper<SupplierGoods>().eq("id",id));
    }

    public List<Map<String, Object>> staticsOperates(){
        return supplierGoodsMapper.staticsOperates();
    }
}
