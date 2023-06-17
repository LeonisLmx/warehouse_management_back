package cn.pqz.emsboot.modules.output.mapper;

import cn.pqz.emsboot.modules.output.entity.OutputLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutputLogMapper extends BaseMapper<OutputLog> {
    public List<OutputLog> getOutputLogList(Integer oid);
}
