package com.Makeupsale.orderservice.servicesImpl;

import com.Makeupsale.orderservice.data_transfer_objects.OrderItemsDto;
import com.Makeupsale.orderservice.data_transfer_objects.OrderRequest;
import com.Makeupsale.orderservice.models.Order;
import com.Makeupsale.orderservice.models.OrderItems;
import com.Makeupsale.orderservice.repositories.OrderRepository;
import com.Makeupsale.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepo;
    @Override
    public void palceOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderItemsList(orderItems);
        orderRepo.save(order);

    }
    private OrderItems mapToDto(OrderItemsDto orderItemsDto){
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;
    }
}
