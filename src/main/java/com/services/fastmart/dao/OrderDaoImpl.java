package com.services.fastmart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderItem;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Order saveOrder(Order order) {
		
		mongoTemplate.insert(order, "customer_order");
		return order;
	}

	@Override
	public List<OrderItem> getOrderItems(String userEmail, int orderId) {
		
		Order order = mongoTemplate.findById(orderId, Order.class);
		List<OrderItem> orderItems = order.getOrderItems();		
		return orderItems;
	}

	@Override
	public Order getOrder(String userEmail, int orderId) {
		
		Order order = mongoTemplate.findById(orderId, Order.class);
		return order;
	}

	@Override
	public List<Order> getAllOrders(String userEmail) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userEmail").is(userEmail) );
		List<Order> orders = mongoTemplate.find(query, Order.class);		
		return orders;
	}

	@Override
	public Order getLatestOrder(String userEmail) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userEmail").is(userEmail));
		query.with(Sort.by(Direction.DESC, "orderTime"));
		query.limit(1);
		List<Order> latestOrders = mongoTemplate.find(query,Order.class);
		if(latestOrders.size()==0) {
			return null;
		}
		return latestOrders.get(0);
	}

}
