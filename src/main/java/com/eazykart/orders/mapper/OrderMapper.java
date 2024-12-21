package com.eazykart.orders.mapper;

import com.eazykart.orders.dto.OrdersDto;
import com.eazykart.orders.entity.Orders;

public class OrderMapper {
	
	public static OrdersDto mapToOrdersDto(Orders order, OrdersDto ordersDto) {
		ordersDto.setOrderId(order.getOrderId());
		ordersDto.setOrderName(order.getOrderName());
		ordersDto.setStatus(order.getStatus());
		return ordersDto;
	}
	
	public static Orders mapToOrders(OrdersDto ordersDto, Orders order) {
		order.setOrderId(ordersDto.getOrderId());
		order.setOrderName(ordersDto.getOrderName());
		order.setStatus(ordersDto.getStatus());
		return order;
	}

}
