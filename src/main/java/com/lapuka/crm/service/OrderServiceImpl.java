package com.lapuka.crm.service;

import com.lapuka.crm.model.Orders;
import com.lapuka.crm.model.User;
import com.lapuka.crm.repository.ApplicationRepository;
import com.lapuka.crm.repository.OrderRepository;
import com.lapuka.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Orders> getAllOrders() {
        return null;
    }

    @Override
    public Page<Orders> findPaginated(String keyword, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            if (keyword != null) {
                return orderRepository.findBySubjectContaining(keyword, pageable);
            }
            return this.orderRepository.findAll(pageable);
        }
        else {
            if (keyword != null) {
                return orderRepository.findBySubjectContainingAndUser(keyword, user, pageable);
            }
            return this.orderRepository.findByUserLike(user, pageable);
        }
    }

    @Override
    public ArrayList<Orders> findUserOrders(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return this.orderRepository.findByUserLike(user);
    }
}
