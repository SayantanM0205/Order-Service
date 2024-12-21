package com.eazykart.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazykart.orders.dto.OrdersDto;
import com.eazykart.orders.dto.ResponseDto;
import com.eazykart.orders.service.OrderService;

@RestController
@RequestMapping(value = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<ResponseDto> createOrder(@RequestBody OrdersDto ordersDto){
		orderService.createOrder(ordersDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Order Created"));
	}
	
	@GetMapping
	public ResponseEntity<OrdersDto> getOrder(@RequestParam Long orderId){
		OrdersDto ordersDto = orderService.getOrder(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(ordersDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateOrder(@RequestBody OrdersDto ordersDto){
		boolean isUpdated = orderService.updateOrder(ordersDto);
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Request processed successfully"));
		}else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("417", "Expectation failed. Kindly contact Dev team"));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteOrder(@RequestParam Long orderId){
		boolean isDeleted = orderService.deleteOrders(orderId);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Request processed successfully"));
		}else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("417", "Expectation failed. Kindly contact Dev team"));
		}
	}
	

}
