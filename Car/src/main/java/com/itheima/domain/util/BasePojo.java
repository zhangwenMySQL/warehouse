package com.itheima.domain.util;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePojo implements Serializable {
    /**
     * 创建时间
     */
    private Date insertTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String insertPeople;

    /**
     * 修改人
     */
    private String updatePeople;

    /**
     * 删除的标志位：1是正常，-1为删除
     */
    private Integer isDelete;
}
