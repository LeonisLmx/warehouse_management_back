package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.Substation;
import cn.pqz.emsboot.modules.business.mapper.SubstationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/26 4:59 下午
 */
@Service
public class SubstationService {

    @Resource
    private SubstationMapper substationMapper;
    
    public List<Substation> list(){
        return substationMapper.selectList(new QueryWrapper<>());
    }

    public Substation getById(Long id){
        return substationMapper.selectById(id);
    }

    public void insertRecord(String name){
        Substation substation = new Substation();
        substation.setName(name);
        substationMapper.insert(substation);
    }
}
