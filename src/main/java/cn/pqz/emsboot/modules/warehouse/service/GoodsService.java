package cn.pqz.emsboot.modules.warehouse.service;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.Warehouse;
import cn.pqz.emsboot.modules.warehouse.entity.WarehouseGoods;
import cn.pqz.emsboot.modules.warehouse.mapper.GoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseGoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private WarehouseGoodsMapper warehouseGoodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    private final Logger logger=Logger.getLogger(GoodsService.class);


    public List<Goods> enterList(){
        return goodsMapper.enterList();
    }
    public String[] goodsPosition(Integer gid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("gid",gid);
        List<WarehouseGoods> wts=warehouseGoodsMapper.selectList(queryWrapper);
        String num[]=new String[wts.size()];
        int i=0;
        for (WarehouseGoods wt:wts){
            num[i]="";
            Integer lid=wt.getWid();
            Warehouse w=warehouseMapper.selectById(lid);
            do{
                num[i]=w.getNum()+num[i];
                w=warehouseMapper.selectById(w.getParentId());
            }while (w.getParentId()!=0);
            i++;
        }
        return num;
    }

    /**
     * 出库
     * @param id
     * @param expCode
     * @param expNo
     */
    public Boolean out(Integer id,String expCode,String expNo){
        Boolean b=true;
        Goods good=goodsMapper.selectById(id);
        try{
            //清理库存
            Wzero(id);
            //更新订单状态
            if (!good.getOrderNum().equals("")){
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("orderNum",good.getOrderNum());
                OrderList order=orderMapper.selectOne(queryWrapper);
                order.setTransport(true);
                order.setOrderState(7);
                orderMapper.updateById(order);
            }
            //更新货物表
            good.setExpCode(expCode);
            good.setExpNo(expNo);
            goodsMapper.updateById(good);
            b=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("出库失败");
            b=false;
        }
        return b;
    }

    /**
     * 根据货物的id来清理待检库的库存
     *
     * @param gid 在warehgoods表中货物的id
     */
    public void Wzero(Integer gid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("gid", gid);
        List<WarehouseGoods> wgs = warehouseGoodsMapper.selectList(qw);
        for (WarehouseGoods wg : wgs) {
            int wid = wg.getWid();
            int id=wg.getId();
            Warehouse warehouse = warehouseMapper.selectById(wid);
            do {
                warehouse.setUsed(warehouse.getUsed() - wg.getUsed());
                warehouse.setPercentage(warehouse.getUsed() / warehouse.getCapacity() * 100);
                warehouseMapper.updateById(warehouse);
                warehouse = warehouseMapper.selectById(warehouse.getParentId());
            } while (warehouse.getParentId() != 0);
            warehouseGoodsMapper.deleteById(id);
        }
    }
}
