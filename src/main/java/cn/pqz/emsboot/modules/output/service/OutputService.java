package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.entity.Output;
import cn.pqz.emsboot.modules.output.entity.OutputLog;
import cn.pqz.emsboot.modules.output.entity.Transition;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import cn.pqz.emsboot.modules.output.mapper.OutputLogMapper;
import cn.pqz.emsboot.modules.output.mapper.OutputMapper;
import cn.pqz.emsboot.modules.output.mapper.TransitionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OutputService extends ServiceImpl<OutputMapper,Output> {
    @Autowired
    private OutputMapper outputMapper;
    @Autowired
    private OutputLogMapper outputLogMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TransitionMapper transitionMapper;

    /**
     * 更新状态
     *
     * @param state
     * @param id
     */
    public void updateOutputState(Boolean state, Integer id) {
        Output output = new Output();
        output.setId(id);
        output.setState(state);
        outputMapper.updateById(output);
    }

    /**
     * 新建生产
     * 思路：更新output表，更新order中的state属性，向outputLog表中插入数据
     * @Transactional(roobackFor=Excaption) 遇到非运行时异常也会回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertOutput(Integer id, Integer orderId, Integer total, String orderNum, String orderName,Integer operator) {
        //更新output表
        Output output = new Output();
        output.setId(id);
        output.setOrderName(orderName);
        output.setOrderNum(orderNum);
        output.setTotal(total);
        outputMapper.updateById(output);
//      //不是导入订单的情况下
        //更新order中state的值
        if (orderNum != null && orderNum != "") {
            QueryWrapper query = new QueryWrapper();
            query.eq("orderNum", orderNum);
            OrderList order = orderMapper.selectOne(query);
            order.setOrderState(2);
            orderMapper.updateById(order);
        }
        //更新工作日志
        OutputLog outputLog = new OutputLog();
        outputLog.setOrderNum(orderNum);
        outputLog.setOrderName(orderName);
        Date startTime = new Date();
        outputLog.setStartTime(startTime);
        outputLog.setOid(id);
        outputLog.setOperator(operator);
        outputLogMapper.insert(outputLog);
    }

    /**
     * 工作进度
     *
     * @param id
     * @param outNow
     */
    public void workOutput(Integer id, Integer outNow) {
        Output output = outputMapper.selectById(id);
        Double complete = outNow + output.getComplete();
        Double percentage = complete / output.getTotal() * 100;
        output.setComplete(complete);
        output.setPercentage(percentage);
        outputMapper.updateById(output);
    }

    /**完成操作
     * @param id
     * @param orderNum
     * @param orderName
     * @param total
     * @param complete
     */
    public void achieve(Integer id, String orderNum, String orderName, Integer total, Double complete) {
        //先将数据保存在transition中
        Transition transition = new Transition();
        transition.setOrderNum(orderNum);
        transition.setName(orderName);
        transition.setPlan(total);
        transition.setComplete(complete);
        transition.setOutputId(id);
        transitionMapper.insert(transition);
        //更新output表
        Output output = outputMapper.selectById(id);
        output.setTotal(0);
        output.setComplete(0.00);
        output.setPercentage(0.00);
        output.setOrderNum("");
        output.setOrderName("");
        outputMapper.updateById(output);
        //更新日志表,思路：查询到该生产线没有结束的任务
        Date endTime = new Date();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (orderNum != null) {
            queryWrapper.eq("orderNum", orderNum);
        } else {
            queryWrapper.eq("oid", id);
            queryWrapper.eq("orderName", orderName);
        }
        //防止重名现象
        List<OutputLog> outputLogs = outputLogMapper.selectList(queryWrapper);
        for (OutputLog outputLog : outputLogs) {
            if (outputLog.getEndTime() == null) {
                outputLog.setEndTime(endTime);
                outputLogMapper.updateById(outputLog);
            }
        }
        //更新order中state的值
        if (orderNum != null && orderNum != "") {
            QueryWrapper query = new QueryWrapper();
            query.eq("orderNum", orderNum);
            OrderList order = orderMapper.selectOne(query);
            order.setOrderState(3);
            orderMapper.updateById(order);
        } else {
            return;
        }
    }
}
