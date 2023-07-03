package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.component.util.InvoiceStatusEnum;
import cn.pqz.emsboot.modules.business.entity.InvoiceManage;
import cn.pqz.emsboot.modules.business.mapper.InvoiceManageMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/7/3 4:06 下午
 */
@Service
public class InvoiceService {

    @Resource
    private InvoiceManageMapper invoiceManageMapper;

    public int insert(String name, BigDecimal price, String description){
        InvoiceManage invoiceManage = new InvoiceManage();
        invoiceManage.setApplyTime(new Date());
        invoiceManage.setName(name);
        invoiceManage.setPrice(price);
        invoiceManage.setStatus(InvoiceStatusEnum.APPLY.getCode());
        invoiceManage.setDescription(description);
        return invoiceManageMapper.insert(invoiceManage);
    }

    public List<InvoiceManage> search(Integer status){
        QueryWrapper<InvoiceManage> queryWrapper = new QueryWrapper<>();
        if (status != null){
            queryWrapper.eq("status", status);
        }
        return invoiceManageMapper.selectList(queryWrapper);
    }

    public int updateStatus(Integer status, Long id){
        InvoiceManage invoiceManage = new InvoiceManage();
        invoiceManage.setStatus(status);
        return invoiceManageMapper.update(invoiceManage,new QueryWrapper<InvoiceManage>().eq("id",id));
    }
}
