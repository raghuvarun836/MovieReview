package com.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviedb.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

}
