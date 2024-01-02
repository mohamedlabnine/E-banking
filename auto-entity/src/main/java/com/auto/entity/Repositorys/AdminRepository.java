package com.auto.entity.Repositorys;

import com.auto.entity.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByAdminName(String adminName);
}
