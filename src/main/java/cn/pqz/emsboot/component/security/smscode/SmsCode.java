package cn.pqz.emsboot.component.security.smscode;

import java.time.LocalDateTime;

public class SmsCode {

    private String code; //短信验证码
    private LocalDateTime expireTime; //过期时间
    private String mobile;

    /**
     *
     * @param code 验证码
     * @param expireAfterSeconds 秒
     */
    public SmsCode(String code, int expireAfterSeconds, String mobile){
        this.code=code;
        //当前时间加上秒数
        this.expireTime=LocalDateTime.now().plusSeconds(expireAfterSeconds);
        this.mobile=mobile;
    }

    /**
     *
     * @return 判断时间是否在expireTime之后
     */
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public String getMobile() {
        return mobile;
    }
}
