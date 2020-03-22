package com.kangaroohy.shiroredis.domain.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.kangaroohy.shiroredis.domain.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 5550808422003281513L;

    /**
     * ID
     */
    @JSONField(ordinal = 1)
    private Integer id;

    /**
     * 用户名
     */
    @JSONField(ordinal = 2)
    private String username;

    /**
     * 密码
     */
    @JSONField(ordinal = 3)
    private String password;

    /**
     * 性别（0：男，1：女）
     */
    @JSONField(ordinal = 4)
    private GenderEnum gender;

    /**
     * 添加时间
     */
    @JSONField(ordinal = 5)
    private Date updateTime;

    /**
     * 角色集合
     */
    @JSONField(ordinal = 6)
    private List<RoleVO> roleList;
}
