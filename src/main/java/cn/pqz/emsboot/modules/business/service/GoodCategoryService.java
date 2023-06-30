package cn.pqz.emsboot.modules.business.service;

import cn.pqz.emsboot.modules.business.entity.GoodCategory;
import cn.pqz.emsboot.modules.business.mapper.GoodCategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/30 4:41 下午
 */
@Service
public class GoodCategoryService {

    @Resource
    private GoodCategoryMapper goodCategoryMapper;

    public int insert(Long parentId, String name){
        GoodCategory goodCategory = new GoodCategory();
        goodCategory.setParentId(0L);
        if (parentId != null){
            goodCategory.setParentId(parentId);
        }
        goodCategory.setName(name);
        return goodCategoryMapper.insert(goodCategory);
    }

    public List<GoodCategory> search(Long parentId){
        QueryWrapper<GoodCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId == null?0:parentId);
        return goodCategoryMapper.selectList(queryWrapper);
    }
}
