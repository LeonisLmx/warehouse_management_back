package cn.pqz.emsboot.modules.warehouse.controller;

import cn.pqz.emsboot.component.KdniaoApi.KdniaoTrackQueryAPI;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.SupplierGoods;
import cn.pqz.emsboot.modules.warehouse.service.GoodsService;
import cn.pqz.emsboot.modules.warehouse.service.SupplierGoodsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Resource
    private SupplierGoodsService supplierGoodsService;

    @GetMapping("/enterList")
    public RespBean enterList(@RequestParam(value = "startTime", required = false)Long startTime,
                              @RequestParam(value = "endTime", required = false)Long endTime) {
        RespBean respBean = null;
        try {
            List<Goods> enterList = goodsService.enterList(startTime, endTime);
            respBean = RespBean.ok("", enterList);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("货物列表获取失败");

        }
        return respBean;
    }

    /**
     * 仓库存储位置
     *
     * @param id
     * @return
     */
    @GetMapping("/goodsPosition/{id}")
    public RespBean goodsPosition(@PathVariable("id") Integer id) {
        RespBean respBean = null;
        try {
            String[] num = goodsService.goodsPosition(id);
            respBean = RespBean.ok("", num);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("所处待检库位置查询失败");
        }

        return respBean;
    }

    @PostMapping("/ship")
    public RespBean ship(@RequestBody JSONObject json) {
        String goodsId = json.getString("goodsId");
        return RespBean.ok("", goodsService.searchByOrders(goodsId));
    }

    @PutMapping("/out")
    public RespBean out(@RequestBody JSONObject json) {
        Long goodsId = json.getLong("goodsId");
        String orders = json.getString("orders");
        return goodsService.goodsOut(goodsId, orders.split("-")[0], Long.parseLong(orders.split("-")[1]));
    }

    @GetMapping("/check")
    public RespBean check(Long substationId){
        return RespBean.ok("", goodsService.check(substationId));
    }

    /**
     * 供应商的商品录入
     * @return
     */
    @PostMapping("/enter")
    public RespBean enterGoods(@RequestBody SupplierGoods supplierGoods){
        return RespBean.ok("供应商商品录入成功", supplierGoodsService.insertGoods(supplierGoods));
    }

    @GetMapping("/searchSupplierList")
    public RespBean searchSupplierList(){
        return RespBean.ok("", supplierGoodsService.list());
    }

    /**
     * 供应商商品入仓库
     */
    @PostMapping("/goodsToWarehouse")
    public RespBean goodsToWarehouse(@RequestBody JSONObject jsonObject){
        return RespBean.ok("", supplierGoodsService.goodsEnterWarehouse(jsonObject.getLong("substationId"), jsonObject.getLong("id")));
    }
}
