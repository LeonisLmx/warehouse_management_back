package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.modules.output.entity.OutputLog;
import cn.pqz.emsboot.modules.output.mapper.OutputLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutputLogService extends ServiceImpl<OutputLogMapper, OutputLog> {
    @Autowired
    private OutputLogMapper outputLogMapper;

    public List<OutputLog> getOutputLogList(Integer oid){
        return outputLogMapper.getOutputLogList(oid);
    }
}
