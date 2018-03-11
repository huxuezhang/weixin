package com.hxz.weixin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Think on 2018/3/7.
 */
@Data
@Entity
@Table(name = "ALL_ENV")
public class AllEnv {
    @Id
    private String id;
    private String key;
    private String userName;
    private String value;
    private String type;
}
