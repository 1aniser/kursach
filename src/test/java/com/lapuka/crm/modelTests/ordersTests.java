package com.lapuka.crm.modelTests;

import com.lapuka.crm.model.Orders;
import com.lapuka.crm.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ordersTests {
    private Orders order;
    private User user;

    @BeforeEach
    void setUp()throws Exception{
        user = new User(1L, "пользователь", "почта@gmail.com", "пароль", "фио", "1234567");
        order = new Orders(1L, "тема", "описание", user, LocalDateTime.now(), LocalDateTime.now(), "Новая", 123);
    }

    @Test
    void testApplication()throws IllegalArgumentException{
        if(!order.getId().equals(1L)) throw new IllegalArgumentException();
        if(!order.getSubject().equals("тема")) throw new IllegalArgumentException();
        if(!order.getDescription().equals("описание")) throw new IllegalArgumentException();
        if(!order.getStatus().equals("Новая")) throw new IllegalArgumentException();
        if(!order.getPrice().equals(123)) throw new IllegalArgumentException();
    }
}
