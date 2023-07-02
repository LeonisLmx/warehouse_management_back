package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.Supplier;
import cn.pqz.emsboot.modules.business.mapper.SupplierMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    public List<Supplier> search(String name){
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        return supplierMapper.selectList(queryWrapper);
    }

    public int update(Supplier supplier){
        return supplierMapper.updateById(supplier);
    }

    public int add(Supplier supplier) {
        return supplierMapper.insert(supplier);
    }

    public int delete(Long id){
        return supplierMapper.deleteById(id);
    }

    public Supplier searchById(Long id){
        return supplierMapper.selectById(id);
    }
}
