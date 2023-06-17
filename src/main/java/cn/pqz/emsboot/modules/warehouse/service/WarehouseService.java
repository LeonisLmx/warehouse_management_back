package cn.pqz.emsboot.modules.warehouse.service;

import cn.pqz.emsboot.component.util.GoodsNumUtil;
import cn.pqz.emsboot.modules.output.entity.Client_order;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.entity.Transition;
import cn.pqz.emsboot.modules.output.mapper.Client_orderMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import cn.pqz.emsboot.modules.output.mapper.TransitionMapper;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.entity.*;
import cn.pqz.emsboot.modules.warehouse.mapper.GoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseGoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseTransitionMapper;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WarehouseService extends ServiceImpl<WarehouseMapper, Warehouse> {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private TransitionMapper transitionMapper;
    @Autowired
    private WarehouseTransitionMapper warehouseTransitionMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Client_orderMapper client_orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private WarehouseGoodsMapper warehouseGoodsMapper;
    private final Logger logger = Logger.getLogger(WarehouseService.class);

    public List<Warehouse> myTransitions() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type", 1);
        List<Warehouse> warehouses = warehouseMapper.selectList(queryWrapper);
        return warehouses;
    }

    /**
     * 货架
     *
     * @param id
     * @return
     */
    public List<Warehouse> myShelf(Integer id) {
        List<Warehouse> shelf = warehouseMapper.myShelf(id);
        return shelf;
    }

    /**
     * 新建货架
     */
    @Transactional(rollbackFor = Exception.class)
    public void addShelf(Warehouse warehouse) {
        //容量等于占地面积*层数，新建货架
        warehouse.setCapacity(warehouse.getArea() * warehouse.getLayer());
        warehouseMapper.insert(warehouse);
        logger.info("----------新建货架成功成功----------");

        //循环插入层数信息
        Integer parentId = warehouse.getId();//霸道获取主键
        logger.info("----------获取新建货架id为" + parentId + "----------");

        for (int i = 1; i <= warehouse.getLayer(); i++) {
            Warehouse warehouse2 = new Warehouse();
            warehouse2.setNum("L" + i);
            warehouse2.setName("层");
            warehouse2.setArea(warehouse.getArea());
            warehouse2.setCapacity(warehouse.getArea());
            warehouse2.setParentId(parentId);
            warehouse2.setType(4);
            warehouseMapper.insert(warehouse2);
            logger.info("----------" + warehouse2.getNum() + "插入成功----------");
        }
        updateCapacity(warehouse);
        logger.info("----------所有容量均更新成功----------");
    }

    /**
     * 更新容量方法
     */
    public void updateCapacity(Warehouse warehouse) {
        //更新库区容量
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", warehouse.getParentId());
        Warehouse transition1 = warehouseMapper.selectOne(queryWrapper);
//        logger.info("----------获取库区信息成功----------");
        //获取库区容量加上新的货架的容量
//        logger.info("----------更新库区容量前parentId:"+transition1.getParentId()+"----------");
        Double capatity = transition1.getCapacity() + warehouse.getArea() * warehouse.getLayer();
        transition1.setCapacity(capatity);
        warehouseMapper.updateById(transition1);
//        logger.info("----------更新库区容量后parentId:"+transition1.getParentId()+"----------");
//        logger.info("----------更新库区容量成功----------");
        //更新仓库容量
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("id", transition1.getParentId());
        Warehouse transition2 = warehouseMapper.selectOne(queryWrapper1);
//        logger.info("----------获取仓库信息成功----------");
        Double capatity1 = transition2.getCapacity() + warehouse.getArea() * warehouse.getLayer();
        transition2.setCapacity(capatity1);
        warehouseMapper.updateById(transition2);
        logger.info("----------更新仓库容量成功----------");
    }

    /**
     * 远程查询完成信息
     */
    public List<Transition> transitions(String name, Integer state) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", name);
        queryWrapper.eq("state", state);
        List<Transition> transitions = transitionMapper.selectList(queryWrapper);
        logger.info("----------远程查询货物信息成功----------");
        return transitions;
    }

    /**
     * 分配地址级联选择器
     */
    public List<Position> Position(Integer id) {
        List<Position> positions = warehouseMapper.position(id);
        for (Position position1 : positions) {
            position1.setValue(position1.getId());
            position1.setLabel(position1.getNum() + "(已使用" + position1.getPercentage() + "%)");
            for (Position position2 : position1.getChildren()) {
                position2.setValue(position2.getId());
                position2.setLabel(position2.getNum() + "(已使用" + position2.getPercentage() + "%)");
                for (Position position3 : position2.getChildren()) {
                    position3.setValue(position3.getId());
                    position3.setLabel(position3.getNum() + "(已使用" + position3.getPercentage() + "%)");
                }
            }
        }
        return positions;
    }

    /**
     * 暂存待检库
     */
    @Transactional(rollbackFor = Exception.class)
    public RespBean store(JSONObject json) {
        RespBean respBean = null;
        Boolean b = true;
//        logger.info("----------暂存待检库操作----------");
        //向warehouseTransition中插入数据
        WarehouseTransition wt = new WarehouseTransition();
        Integer tid = json.getInteger("tid");
        Double used = json.getDouble("area");
        String lids = json.getString("lid");
        String arr[] = lids.split(",");
        Integer[] lid = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lid[i] = Integer.parseInt(arr[i]);
        }
//        logger.info("----------已经成功获取所有数据----------");
        if (lid.length == 1) {
            Warehouse w = warehouseMapper.selectById(lid[0]);
            if (w.getCapacity() < w.getUsed() + used) {
                respBean = RespBean.error("存储已达上限,存储失败");
                b = false;
            } else {
                wt.setTid(tid);
                wt.setLid(lid[0]);
                wt.setUsed(used);
                warehouseTransitionMapper.insert(wt);
            }
        } else {
            for (int i = 0; i < lid.length - 1; i++) {
                Warehouse w = warehouseMapper.selectById(lid[i]);
                if (w.getCapacity() < w.getUsed() + 10) {
                    respBean = RespBean.error("存储已达上限,存储失败");
                    b = false;
                } else {
                    wt.setTid(tid);
                    wt.setLid(lid[i]);
                    wt.setUsed(10.00);
                    warehouseTransitionMapper.insert(wt);
                }
            }
            Warehouse w = warehouseMapper.selectById(lid[lid.length - 1]);
            if (w.getCapacity() < w.getUsed() + used % 10) {
                respBean = RespBean.error("存储已达上限,存储失败");
                b = false;
            } else {
                if (used%10==0){
                    wt.setUsed(10.00);
                }else {
                    wt.setUsed(used%10);
                }
                wt.setTid(tid);
                wt.setLid(lid[lid.length - 1]);
                warehouseTransitionMapper.insert(wt);
            }
        }
//        logger.info("----------warehouseTransition表数据插入成功----------");
        //更新warehouse表
        if (b) {
            updateWarehouse(lid, used);
            //更新transition的state值
            Transition transition = transitionMapper.selectById(tid);
            transition.setState(2);
            transitionMapper.updateById(transition);
//        logger.info("----------transition.state更新成功----------");
            //更新order中state的值
            String orderNum = transition.getOrderNum();
            if (orderNum != null && !orderNum.equals("")) {
                QueryWrapper query = new QueryWrapper();
                query.eq("orderNum", orderNum);
                OrderList order = orderMapper.selectOne(query);
                order.setOrderState(4);
                orderMapper.updateById(order);
            }
//            logger.info("----------暂存待检库操作成功----------");
        }

        return respBean;
    }

    /**
     * 仓库容量处理
     *
     * @param lid
     * @param used
     */
    @Transactional(rollbackFor = Exception.class)
    void updateWarehouse(Integer[] lid, Double used) {

        if (lid.length == 1) {
            Warehouse w = warehouseMapper.selectById(lid[0]);
            do {
                if (w.getCapacity() < w.getUsed() + used) {
                    break;
                } else {
                    w.setUsed(w.getUsed() + used);
                    w.setPercentage(w.getUsed() / w.getCapacity() * 100);
                    warehouseMapper.updateById(w);
                }
                w = warehouseMapper.selectById(w.getParentId());
            } while (w.getParentId() != 0);

        } else {
            for (int i = 0; i < lid.length - 1; i++) {
                Warehouse w = warehouseMapper.selectById(lid[i]);
                do {
                    if (w.getCapacity() < w.getUsed() + 10) {

                        break;
                    } else {
                        w.setUsed(w.getUsed() + 10);
                        w.setPercentage(w.getUsed() / w.getCapacity() * 100);
                        warehouseMapper.updateById(w);
                    }
                    w = warehouseMapper.selectById(w.getParentId());
                } while (w.getParentId() != 0);
            }
            Warehouse w = warehouseMapper.selectById(lid[lid.length - 1]);

            do {
                if (w.getCapacity() < w.getUsed() + used % 10) {

                    break;
                } else {
                    if (used % 10 == 0) {
                        w.setUsed(w.getUsed()+10.00);//出错的地方
                    } else {
                        w.setUsed(w.getUsed() + used % 10);
                    }
                    w.setPercentage(w.getUsed() / w.getCapacity() * 100);
                    warehouseMapper.updateById(w);
                }
                w = warehouseMapper.selectById(w.getParentId());
            } while (w.getParentId() != 0);
            //          logger.info("----------仓库使用量更新成功----------");
        }
//        logger.info("----------warehouse容量更新成功----------");

    }

    /**
     * 入库
     */
    @Transactional(rollbackFor = Exception.class)
    public RespBean enter(JSONObject json) {
        //获取数据
        RespBean respBean = null;
        Boolean b = true;
        Integer tid = json.getInteger("tid");
        String num = GoodsNumUtil.GetRandom();
        String orderNum = json.getString("orderNum");
        String name = json.getString("name");
        Double count = json.getDouble("countQ");
        Integer type = json.getInteger("type");
        Double used1 = json.getDouble("area1");
        Double used2 = json.getDouble("area2");
        String operator=json.getString("operator");
        String lids = json.getString("lid");
        String arr[] = lids.split(",");
        Integer[] lid = new Integer[arr.length];
        try{
            for (int i = 0; i < arr.length; i++) {
                lid[i] = Integer.parseInt(arr[i]);
            }
            Integer cid = null;
            if (orderNum.equals("自主生产")) {
                cid = 0;
                orderNum = "";
            } else {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("orderNum", orderNum);
                OrderList order = orderMapper.selectOne(queryWrapper);
                QueryWrapper qu = new QueryWrapper();
                qu.eq("oid", order.getId());
                Client_order co = client_orderMapper.selectOne(qu);
                cid = co.getCid();
                order.setOrderState(5);
                orderMapper.updateById(order);//order状态更新成功;
            }
//        logger.info("----------数据获取成功---------");
            //存入goods
            Goods goods = new Goods();
            goods.setNum(num);
            goods.setOrderNum(orderNum);
            goods.setName(name);
            goods.setCount(count);
            goods.setClientId(cid);
            goods.setType(type);
            goods.setDate(new Date());
            goods.setOperator(operator);
            int i = goodsMapper.insert(goods);
            Integer gid = goods.getId();
//        logger.info("--------存入goods成功---------");
            if (i == 1) {
                Zero(tid);
//            logger.info("---------所属仓库已清理----------");
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("tid", tid);
                int i1 = warehouseTransitionMapper.delete(queryWrapper);
//            logger.info("----------warehouseTransition数据删除成功");
                if (i1 != 0) {
//                logger.info("----------开始warehouseGoods插入");
                    //存储数据
                    WarehouseGoods wg = new WarehouseGoods();
                    if (lid.length == 1) {
                        Warehouse w = warehouseMapper.selectById(lid[0]);
                        if (w.getCapacity() < w.getUsed() + used1) {
                            respBean = RespBean.error("存储已达上限,存储失败");
                            b = false;
                        } else {
                            wg.setGid(gid);
                            wg.setWid(lid[0]);
                            wg.setUsed(used1);
                            warehouseGoodsMapper.insert(wg);
//                        logger.info("----------warehouseGoods插入成功");
                        }
                    } else {
                        for (int n = 0; n < lid.length - 1; n++) {
                            Warehouse w = warehouseMapper.selectById(lid[n]);
                            if (w.getCapacity() < w.getUsed() + 10) {
                                respBean = RespBean.error("存储已达上限,存储失败");
                                b = false;
                            } else {
                                wg.setGid(gid);
                                wg.setWid(lid[n]);
                                wg.setUsed(10.00);
                                warehouseGoodsMapper.insert(wg);
                                //                            logger.info("----------warehouseGoods插入成功");
                            }
                        }
                        Warehouse w = warehouseMapper.selectById(lid[lid.length - 1]);
                        if (w.getCapacity() < w.getUsed() + used1 % 10) {
                            respBean = RespBean.error("存储已达上限,存储失败");
                            b = false;
                        } else {
                            if (used1%10==0){
                                wg.setUsed(10.00);
                            }else {
                                wg.setUsed(used1 % 10);//分两种情况；
                            }
                            wg.setGid(gid);
                            wg.setWid(lid[lid.length - 1]);
                            warehouseGoodsMapper.insert(wg);
                            //                        logger.info("----------warehouseGoods插入成功");
                        }
                    }
                    if (b) {
                        try{
                            updateWarehouse(lid, used1);
                        }catch (Exception e){
                            e.printStackTrace();
                            respBean=RespBean.error("仓库容量更新失败");
                            b=false;
                        }

                    }
                    if (b) {
                        try{
                            Warehouse warehouse = warehouseMapper.selectById(58);//废品库
                            warehouse.setUsed(warehouse.getUsed() + used2);
                            warehouse.setPercentage(warehouse.getUsed() / warehouse.getCapacity() * 100);
                            warehouseMapper.updateById(warehouse);
                        }catch (Exception e){
                            e.printStackTrace();
                            respBean=RespBean.error("废品处理失败");
                            b=false;
                        }
                    }
                    if (b) {
                        Transition transition = transitionMapper.selectById(tid);
                        transition.setState(3);
                        transitionMapper.updateById(transition);
                        respBean=RespBean.ok("存储成功");
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("存储失败");
        }


        return respBean;
    }

    /**
     * 根据货物的id来清理待检库的库存
     *
     * @param tid
     */
    public void Zero(Integer tid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("tid", tid);
        List<WarehouseTransition> wts = warehouseTransitionMapper.selectList(qw);
        for (WarehouseTransition wt : wts) {
            int lid = wt.getLid();
            Warehouse warehouse = warehouseMapper.selectById(lid);
            do {
                warehouse.setUsed(warehouse.getUsed() - wt.getUsed());
                warehouse.setPercentage(warehouse.getUsed() / warehouse.getCapacity() * 100);
//                System.out.println("----------------;;;"+warehouse.getPercentage());
                warehouseMapper.updateById(warehouse);
                warehouse = warehouseMapper.selectById(warehouse.getParentId());
            } while (warehouse.getParentId() != 0);
        }
    }

}
