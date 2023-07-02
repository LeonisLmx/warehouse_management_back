package cn.pqz.emsboot.modules.output.http;

import cn.pqz.emsboot.modules.output.entity.OrderList;
import lombok.Data;

@Data
public class OrderListResponse extends OrderList {

    private String name;
}
