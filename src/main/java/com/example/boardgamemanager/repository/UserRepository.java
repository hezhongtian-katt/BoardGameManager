package com.example.boardgamemanager.repository;

import com.example.boardgamemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
}