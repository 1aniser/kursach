package com.lapuka.crm.service;

import com.lapuka.crm.model.Application;
import com.lapuka.crm.model.Orders;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();

    Page<Orders> findPaginated(String keyword, int pageNo, int pageSize, String sortField, String sortDirection);

    ArrayList<Orders> findUserOrders(Long id);
}
