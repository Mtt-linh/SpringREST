package com.example.SpringREST.repository;

import com.example.SpringREST.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRespository extends JpaRepository<Role,Long> {

}
