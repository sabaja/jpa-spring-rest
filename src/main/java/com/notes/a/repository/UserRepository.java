package com.notes.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.a.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
