package com.example.SpringStart.tables.logindata.repository;

import com.example.SpringStart.tables.logindata.model.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {
}
