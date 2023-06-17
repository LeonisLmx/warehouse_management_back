package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.modules.output.entity.Transition;
import cn.pqz.emsboot.modules.output.mapper.TransitionMapper;
import cn.pqz.emsboot.modules.warehouse.entity.Warehouse;
import cn.pqz.emsboot.modules.warehouse.entity.WarehouseGoods;
import cn.pqz.emsboot.modules.warehouse.entity.WarehouseTransition;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseGoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseTransitionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransitionService extends ServiceImpl<TransitionMapper, Transition> {
    @Autowired
    WarehouseTransitionMapper warehouseTransitionMapper;
    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    WarehouseGoodsMapper warehouseGoodsMapper;
    public String[] transitionPosition(Integer tid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("tid",tid);
        List<WarehouseTransition> wts=warehouseTransitionMapper.selectList(queryWrapper);
        String num[]=new String[wts.size()];
        int i=0;
        for (WarehouseTransition wt:wts){
            num[i]="";
            Integer lid=wt.getLid();
            Warehouse w=warehouseMapper.selectById(lid);
            do{
                num[i]=w.getNum()+num[i];
                w=warehouseMapper.selectById(w.getParentId());
            }while (w.getParentId()!=0);
            i++;
        }
        return num;
    }
}
