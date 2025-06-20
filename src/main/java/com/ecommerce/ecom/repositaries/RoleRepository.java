package com.ecommerce.ecom.repositaries;

import com.ecommerce.ecom.model.AppRole;
import com.ecommerce.ecom.model.Product;
import com.ecommerce.ecom.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
