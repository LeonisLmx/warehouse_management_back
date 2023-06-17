package cn.pqz.emsboot.modules.warehouse.controller;

import cn.pqz.emsboot.component.KdniaoApi.KdniaoTrackQueryAPI;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.service.GoodsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/enterList")
    public RespBean enterList() {
        RespBean respBean = null;
        try {
            List<Goods> enterList = goodsService.enterList();
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
        RespBean respBean = null;
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        String expCode = json.getString("expCode");
        String expNo = json.getString("expNo");
        try {
            String result = api.getOrderTracesByJson(expCode, expNo);
            respBean = RespBean.ok("", result);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("物流信息查询失败");
        }
        return respBean;
    }

    @PutMapping("/out")
    public RespBean out(@RequestBody JSONObject json) {
        RespBean respBean = null;
        Integer id = json.getInteger("id");
        String expCode = json.getString("expCode");
        String expNo = json.getString("expNo");
        Boolean b = goodsService.out(id, expCode, expNo);
        if (b) {
            respBean = RespBean.ok("出库成功");
        } else {
            respBean = RespBean.error("出库失败");
        }


        return respBean;
    }
}
