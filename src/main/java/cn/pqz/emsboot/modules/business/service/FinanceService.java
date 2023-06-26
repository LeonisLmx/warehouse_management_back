package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.Finance;
import cn.pqz.emsboot.modules.business.mapper.FinanceMapper;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/25 4:20 下午
 */
@Service
public class FinanceService {

    @Resource
    private FinanceMapper financeMapper;

    public RespBean search(Integer role){
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<Finance>();
        if (role != null) {
            queryWrapper.eq("role", role);
        }
        return RespBean.ok("",financeMapper.selectList(queryWrapper));
    }

    public void addRecord(Integer role, Long amount, Integer action){
        Finance finance = new Finance();
        finance.setRole(role);
        finance.setAmount(amount);
        finance.setAction(action);
        finance.setCreateTime(new Date());
        financeMapper.insert(finance);
    }
}
