package com.byfood.api.service;

import com.byfood.api.dto.OrderRequest;
import com.byfood.api.dto.OrderResponse;
import com.byfood.api.exception.NotFoundException;
import com.byfood.api.mapper.OrderMapper;
import com.byfood.api.model.MenuItem;
import com.byfood.api.model.Order;
import com.byfood.api.model.OrderItem;
import com.byfood.api.model.OrderStatus;
import com.byfood.api.repository.MenuItemRepository;
import com.byfood.api.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantService restaurantService;
    private final WhatsAppLinkService whatsAppLinkService;

    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository,
                        RestaurantService restaurantService, WhatsAppLinkService whatsAppLinkService) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.restaurantService = restaurantService;
        this.whatsAppLinkService = whatsAppLinkService;
    }

    private String whatsappLink(Order order) {
        return whatsAppLinkService.buildOrderLink(order, restaurantService.getRestaurant().whatsappNumber());
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .customerName(request.customerName())
                .customerPhone(request.customerPhone())
                .customerAddress(request.customerAddress())
                .status(OrderStatus.RECEIVED)
                .total(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;
        for (var itemRequest : request.items()) {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.menuItemId())
                    .orElseThrow(() -> new NotFoundException("Menu item not found"));

            if (!menuItem.isAvailable()) {
                throw new NotFoundException("Menu item is not available");
            }

            BigDecimal lineTotal = menuItem.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            total = total.add(lineTotal);

            OrderItem item = OrderItem.builder()
                    .itemName(menuItem.getName())
                    .unitPrice(menuItem.getPrice())
                    .quantity(itemRequest.quantity())
                    .build();
            order.addItem(item);
        }

        order.setTotal(total);
        Order saved = orderRepository.save(order);
        return OrderMapper.toResponse(saved, whatsappLink(saved));
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        return orderRepository.findById(id)
                .map(order -> OrderMapper.toResponse(order, whatsappLink(order)))
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(order -> OrderMapper.toResponse(order, whatsappLink(order)))
                .toList();
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        Order saved = orderRepository.save(order);
        return OrderMapper.toResponse(saved, whatsappLink(saved));
    }
}