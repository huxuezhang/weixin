package com.hxz.weixin.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER")
public class User implements Serializable{

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String nickName;
    private int sex;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private LocalDateTime subcribeTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
