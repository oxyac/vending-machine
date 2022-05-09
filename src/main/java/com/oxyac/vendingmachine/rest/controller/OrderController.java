package com.oxyac.vendingmachine.rest.controller;

import com.oxyac.vendingmachine.data.dto.OrderProductDto;
import com.oxyac.vendingmachine.data.entity.Order;
import com.oxyac.vendingmachine.data.entity.OrderProduct;
import com.oxyac.vendingmachine.data.exception.ResourceNotFoundException;
import com.oxyac.vendingmachine.service.IOrderProductService;
import com.oxyac.vendingmachine.service.OrderService;
import com.oxyac.vendingmachine.service.ProductService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@Slf4j
@RestController
public class OrderController {

    ProductService productService;
    OrderService orderService;
    IOrderProductService orderProductService;

    public OrderController(ProductService productService, OrderService orderService, IOrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull
    Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@RequestBody OrderForm form) throws ResourceNotFoundException {
        log.info(form.toString());
        List<OrderProductDto> formDtos = form.getProductOrders();
        Order order = new Order();
        order.setStatus(Order.TransactionState.IN_PROGRESS.name());
        order = this.orderService.create(order);

        validateProductsExistence(formDtos);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProductById(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setProducts(orderProducts);

        order.setStatus(Order.TransactionState.DONE.name());
        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) throws ResourceNotFoundException {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProductById(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        @Override
        public String toString() {
            return "OrderForm{" +
                    "productOrders=" + productOrders.toString() +
                    '}';
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }

}
