package com.lapuka.crm.service;

import com.lapuka.crm.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lapuka.crm.model.User;
import com.lapuka.crm.dto.UserRegistrationDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User findByUsername(String username);

    User findByEmail(String email);

    void deleteById(long id);

    User save(UserRegistrationDto registration);

    Page<User> findPaginated(String keyword, int pageNo, int pageSize, String sortField, String sortDirection);
}