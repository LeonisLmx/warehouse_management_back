package cn.pqz.emsboot.modules.output.http;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import lombok.Data;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/20 5:22 下午
 */
@Data
public class OrderRequest extends OrderList {

    private Long time;

    private boolean checked;

    public Long getTime() {
        return time == null?System.currentTimeMillis():time;
    }
}
