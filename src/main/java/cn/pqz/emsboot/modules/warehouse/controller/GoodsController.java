package cn.pqz.emsboot.modules.warehouse.controller;

import cn.pqz.emsboot.component.KdniaoApi.KdniaoTrackQueryAPI;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.service.GoodsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

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
    public RespBean check(){
        return RespBean.ok("", goodsService.check());
    }
}
