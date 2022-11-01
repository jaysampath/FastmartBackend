package com.services.fastmart.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.services.fastmart.entity.DatabaseFields;
import com.services.fastmart.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderProduct;
import org.springframework.util.CollectionUtils;

@Component
public class OrderDaoImpl implements OrderDao {

	Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
	private final MongoTemplate mongoTemplate;

	private final OrderRepository orderRepository;

	@Autowired
	public OrderDaoImpl(MongoTemplate mongoTemplate, OrderRepository orderRepository) {
		this.mongoTemplate = mongoTemplate;
		this.orderRepository = orderRepository;
	}

	@Override
	public Order saveOrder(Order order) {
		Order persistedOrder = orderRepository.save(order);
		logger.info("Persisted order - {}", persistedOrder);
		return persistedOrder;
	}

	@Override
	public List<OrderProduct> getOrderItems(String userEmail, String orderId) {
		Order order = getOrder(userEmail, orderId) ;
		return  order != null ? order.getOrderProducts(): null;
	}
	@Override
	public Order getOrder(String userEmail, String orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(new ObjectId(orderId));
		return optionalOrder.orElse(null);
	}

	@Override
	public List<Order> getAllOrders(String userEmail) {
		List<Order> allOrders = orderRepository.findAllByUserEmail(userEmail);
		return allOrders.stream().sorted((o1, o2) -> (int) (o1.getCreatedDate() - o2.getCreatedDate())).collect(Collectors.toList());
	}

	@Override
	public Order getLatestOrder(String userEmail) {
		Query query = new Query();
		query.addCriteria(Criteria.where(DatabaseFields.USER_EMAIL).is(userEmail));
		query.with(Sort.by(Direction.DESC, DatabaseFields.ORDER_TIME));
		query.limit(1);
		List<Order> latestOrders = mongoTemplate.find(query,Order.class);
		if(CollectionUtils.isEmpty(latestOrders)) {
			return null;
		}
		return latestOrders.get(0);
	}

}
