package com.eazykart.orders.service;

import com.eazykart.orders.dto.OrdersDto;

public interface OrderService {

	public void createOrder(OrdersDto ordersDto);
	
	public OrdersDto getOrder(Long orderId);
	
	public boolean updateOrder(OrdersDto ordersDto);
	
	public boolean deleteOrders(Long orderId);
	
}
