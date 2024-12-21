package com.eazykart.orders.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazykart.orders.dto.OrdersDto;
import com.eazykart.orders.entity.Orders;
import com.eazykart.orders.exception.OrderAlreadyExistsException;
import com.eazykart.orders.exception.OrderNotFoundException;
import com.eazykart.orders.mapper.OrderMapper;
import com.eazykart.orders.repository.OrderRepository;
import com.eazykart.orders.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Override
	public void createOrder(OrdersDto ordersDto) {
		Orders order = OrderMapper.mapToOrders(ordersDto, new Orders());
		Optional<Orders> existingOrder = orderRepository.findById(ordersDto.getOrderId());
		if(existingOrder.isPresent()) {
			throw new OrderAlreadyExistsException("Order already exists with Id: "+existingOrder.get().getOrderId());
		}
		Orders newOrder = orderRepository.save(order);
		log.info("Order created with Id: {}",newOrder.getOrderId());
		
	}

	@Override
	public OrdersDto getOrder(Long orderId) {
		Orders order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found with Id: "+orderId));
		log.info("Order found with Id: {}",order.getOrderId());
		OrdersDto ordersDto = OrderMapper.mapToOrdersDto(order, new OrdersDto());
		return ordersDto;
	}

	@Override
	public boolean updateOrder(OrdersDto ordersDto) {
		//Orders order = OrderMapper.mapToOrders(ordersDto, new Orders());
		boolean isUpdated = false;
		Orders existingOrder = orderRepository.findById(ordersDto.getOrderId()).orElseThrow(()->new OrderNotFoundException("Order not found with Id: "+ordersDto.getOrderId()));
		Orders order = OrderMapper.mapToOrders(ordersDto, existingOrder);
		Orders updatedOrder = orderRepository.save(order);
		log.info("Order has been updated: {}", updatedOrder);
		isUpdated = true;
		return isUpdated;
	}

	@Override
	public boolean deleteOrders(Long orderId) {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found with Id: "+orderId));
		orderRepository.delete(order);
		log.info("Order has been deleted with Id: {}", orderId);
		return true;
	}
	
	

}
