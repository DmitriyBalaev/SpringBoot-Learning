package com.dmbl.springbootcacheexample.repository;

import com.dmbl.springbootcacheexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
