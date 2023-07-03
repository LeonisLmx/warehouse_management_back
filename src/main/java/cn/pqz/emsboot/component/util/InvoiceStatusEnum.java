package cn.pqz.emsboot.component.util;

/**
 * @author urey.liu
 * @description
 * @date 2023/7/3 4:09 下午
 */
public enum InvoiceStatusEnum {

    MISS(-1,"发票作废"),
    BACK(0,"发票退回"),
    APPLY(1,"发票申请"),
    OK(2,"开票完成");

    private int code;
    private String content;

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    InvoiceStatusEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
