package cn.pqz.emsboot.modules.business.controller;

import cn.pqz.emsboot.modules.business.service.GoodCategoryService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/30 4:49 下午
 */
@RestController
@RequestMapping("/goodCategory")
public class GoodCategoryController {

    @Resource
    private GoodCategoryService goodCategoryService;

    @PostMapping("/add")
    public RespBean addNew(@RequestBody JSONObject jsonObject){
        return RespBean.ok("添加新分类成功",goodCategoryService.insert(jsonObject.getLong("parentId"),jsonObject.getString("name")));
    }

    @GetMapping("/search")
    public RespBean search(Long parentId){
        return RespBean.ok("", goodCategoryService.search(parentId));
    }
}
