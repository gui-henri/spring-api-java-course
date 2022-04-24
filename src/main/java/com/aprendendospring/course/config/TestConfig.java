package com.aprendendospring.course.config;

import com.aprendendospring.course.entities.*;
import com.aprendendospring.course.entities.enums.OrderStatus;
import com.aprendendospring.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(
                null,
                "Sarah",
                "sarah@gmail.com",
                "69696969",
                "24242424"
        );

        User u2 = new User(null,
                "Guilherme",
                "genderfluid@gmail.com",
                "24242424",
                "69696969"
        );

        Order o1 = new Order(
                null,
                Instant.parse("2022-03-26T12:00:10Z"),
                OrderStatus.PAID,
                u1
        );

        Order o2 = new Order(
                null,
                Instant.parse("2022-01-26T12:00:10Z"),
                OrderStatus.WAITING_PAYMENT,
                u2
        );

        Order o3 = new Order(
                null,
                Instant.parse("2022-02-26T12:00:10Z"),
                OrderStatus.WAITING_PAYMENT,
                u1
        );

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "One of the best films ever made", 80.0, "cool URL");
        Product p2 = new Product(null, "MacBook Pro", "A cool computer", 99999.0, "cool URL");
        Product p3 = new Product(null, "Love", "For sale!", 2.35, "cool URL");
        Product p4 = new Product(null, "Viva la Vida", "The Jerusalem bells are still ringing", 99.0, "cool URL");
        Product p5 = new Product(null, "Portal Gun", "This wasn't even a lie", 640000.0, "cool URL");

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(c2);
        p2.getCategories().add(c1);
        p2.getCategories().add(c3);
        p3.getCategories().add(c2);
        p4.getCategories().add(c2);
        p5.getCategories().add(c1);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o3, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o1, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2022-03-26T14:00:10Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
