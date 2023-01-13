package com.Makeupsale.orderservice.servicesImpl;

import com.Makeupsale.orderservice.data_transfer_objects.InventoryResponse;
import com.Makeupsale.orderservice.data_transfer_objects.OrderItemsDto;
import com.Makeupsale.orderservice.data_transfer_objects.OrderRequest;
import com.Makeupsale.orderservice.event.OrderPlacedEvent;
import com.Makeupsale.orderservice.models.Order;
import com.Makeupsale.orderservice.models.OrderItems;
import com.Makeupsale.orderservice.repositories.OrderRepository;
import com.Makeupsale.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepo;
    private final WebClient webClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    @Override
    public String palceOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderItemsList(orderItems);

        List<String> skuCodes = order.getOrderItemsList().stream().map(OrderItems::getSkuCode).toList();
        //appel de Inventory service et placcement de l'order si le produit est en stock
      InventoryResponse[] InventoryResponseArray = webClient.get()
          .uri( "http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
           .retrieve()
          .bodyToMono(InventoryResponse[].class)
        .block();

      boolean allProductsInStock = Arrays.stream(InventoryResponseArray).allMatch(InventoryResponse::isInStock);
      if(allProductsInStock) {
        orderRepo.save(order);
        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
        return "order place successfully";
      }else{
        throw new IllegalArgumentException("product is not in stock please try again later");
      }

    }
    private OrderItems mapToDto(OrderItemsDto orderItemsDto){
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;
    }
}
