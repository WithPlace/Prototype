package com.example.withplaceprototype.repository;

import com.example.withplaceprototype.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

    User findByUserId(String userId);
}
