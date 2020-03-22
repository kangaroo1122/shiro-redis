package com.kangaroohy.shiroredis.domain.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc 性别枚举，用于数据库数据自动序列化为枚举的描述
 * @since 2020/3/9
 */
@Getter
public enum GenderEnum implements IEnum<Integer> {
    //男
    MALE(0, "男"),
    //女
    FEMALE(1, "女");

    /**
     * code
     */
    private int code;

    /**
     * desc
     */
    private String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.name() + ":" + desc;
    }
}