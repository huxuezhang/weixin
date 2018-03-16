package com.hxz.weixin.repository;

import com.hxz.weixin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String> , JpaSpecificationExecutor<User>{
}
