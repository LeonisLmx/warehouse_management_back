package cn.pqz.emsboot.modules.output.contoller;

import cn.pqz.emsboot.modules.output.entity.Transition;
import cn.pqz.emsboot.modules.output.service.TransitionService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transition")
public class TransitionController {
    @Autowired
    private TransitionService transitionService;
    @GetMapping("/myTransition/")
    public RespBean myTransition(@RequestParam String name,
                                 @RequestParam Integer state){
        RespBean respBean=null;
        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            if (state!=null){
                queryWrapper.eq("state",state);
            }
            queryWrapper.like("name",name);
            List<Transition> transitions=transitionService.list(queryWrapper);
            respBean=RespBean.ok("",transitions);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取生产完成列表失败");
        }
        return respBean;
    }
    //查询待检库位置
    @GetMapping("/transitionPosition/{id}")
    public RespBean position(@PathVariable("id") Integer id){
        RespBean respBean=null;
        try {
            String[] num=transitionService.transitionPosition(id);
            respBean = RespBean.ok("",num);
        }catch (Exception e){
            e.printStackTrace();
            respBean = RespBean.error("所处待检库位置查询失败");
        }

        return respBean;
    }
    //删除生产档案中的货物
    @DeleteMapping("/deleteTransition/{id}")
    public RespBean deleteTransition(@PathVariable("id") Integer id){
        RespBean respBean=null;
        Boolean i=transitionService.removeById(id);
        if (i){
            respBean=RespBean.ok("清理成功");
        }else {
            respBean=RespBean.error("清理失败");
        }
        return respBean;
    }
}
