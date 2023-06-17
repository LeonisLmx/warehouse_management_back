package cn.pqz.emsboot.modules.sys.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DruidSqlController {
    @GetMapping("/system/druidSql")
    public String druidSql(){
        return "http://localhost:8181/druid";
    }
}
