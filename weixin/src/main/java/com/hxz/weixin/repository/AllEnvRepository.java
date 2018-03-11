package com.hxz.weixin.repository;

import com.hxz.weixin.domain.AllEnv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Think on 2018/3/7.
 */
public interface AllEnvRepository  extends JpaRepository<AllEnv, String>, JpaSpecificationExecutor<AllEnv> {
    AllEnv findByUserNameAndKey(String userName, String key);
}
