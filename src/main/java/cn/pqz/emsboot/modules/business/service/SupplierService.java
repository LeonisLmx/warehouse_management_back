package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.Supplier;
import cn.pqz.emsboot.modules.business.mapper.SupplierMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    public List<Supplier> search(String name){
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
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
}
