package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.Substation;
import cn.pqz.emsboot.modules.business.mapper.SubstationMapper;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/26 4:59 下午
 */
@Service
public class SubstationService {

    @Resource
    private SubstationMapper substationMapper;

    @Resource
    private OrderListMapper orderListMapper;
    
    public List<Map<String, Object>> listCount(Long id){
        QueryWrapper<Substation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", 0L);
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        List<Substation> substations = substationMapper.selectList(queryWrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Substation substation : substations) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", substation.getId());
            map.put("name", substation.getName());
            map.put("count", this.listByParentId(substation.getId()).size());
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> listByParentId(Long parentId){
        QueryWrapper<Substation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId);
        List<Substation> substations = substationMapper.selectList(queryWrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Substation substation : substations) {
            Map<String, Object> map = new HashMap<>();
            QueryWrapper<OrderList> orderQuery = new QueryWrapper<>();
            orderQuery.eq("substationId", substation.getId());
            map.put("id", substation.getId());
            map.put("name", substation.getName());
            map.put("count", orderListMapper.selectList(orderQuery).size());
            list.add(map);
        }
        return list;
    }

    public List<Map<String,Object>> listAll(Long parentId){
        QueryWrapper<Substation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId);
        List<Substation> substations = substationMapper.selectList(queryWrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Substation substation : substations) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", substation.getId());
            map.put("label", substation.getName());
            List<Map<String, Object>> childrenList = this.listAll(substation.getId());
            if (!childrenList.isEmpty()){
                map.put("children", childrenList);
            }
            list.add(map);
        }
        return list;
    }

    public Substation getById(Long id){
        return substationMapper.selectById(id);
    }

    public void insertRecord(String name, Long parentId){
        Substation substation = new Substation();
        substation.setName(name);
        if (parentId == null){
            parentId = 0L;
        }
        substation.setParentId(parentId);
        substationMapper.insert(substation);
    }

    public StringBuilder getFullSubstationInfo(Long substationId, StringBuilder sb){
        if (substationId == 0){
            return sb;
        }
        Substation substation = substationMapper.selectById(substationId);
        sb.insert(0,substation.getName()).insert(0,"/");
        return getFullSubstationInfo(substation.getParentId(), sb);
    }

    public Substation randomSubstation(){
        List<Substation> substations = substationMapper.selectList(new QueryWrapper<>());
        return substations.get(new Random().nextInt(substations.size()));
    }

    public List<Substation> listData(){
        return substationMapper.selectList(new QueryWrapper<>());
    }
}
