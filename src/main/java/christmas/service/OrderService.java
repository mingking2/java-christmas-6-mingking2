package christmas.service;

import christmas.dto.DateDTO;
import christmas.dto.OrderMenuRequest;
import christmas.model.Order;
import christmas.model.Date;
import christmas.model.OrderMenu;
import christmas.repository.OrderRepository;


public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Date createDateOfVisit(DateDTO dateDTO) {
        return new Date(dateDTO);
    }

    public OrderMenu createOrderMenu(OrderMenuRequest orderMenuRequest) {
        return new OrderMenu(orderMenuRequest);
    }

    public Order createOrder(DateDTO dateDTO, OrderMenuRequest orderMenuRequest) {
        Date date = createDateOfVisit(dateDTO);
        OrderMenu orderMenu = createOrderMenu(orderMenuRequest);
        Order order = new Order(date, orderMenu);

        orderRepository.save(order);

        return order;
    }



}
