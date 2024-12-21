package com.eazykart.orders;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdersDto {

	private Long orderId;
	private String orderName;
	private String status;
}
