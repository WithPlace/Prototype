package com.example.withplaceprototype.match.repository;

import com.example.withplaceprototype.entitiy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);
}
