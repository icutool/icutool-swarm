package cn.icutool.domain.enums;

public enum MessageTypeEnum {

    /**
     * 文本
     */
    TEXT("text",0),
    /**
     * 图片
     */
    IMAGE("image",1);
    private final String desc;
    private final Integer code;
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    MessageTypeEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }
}
