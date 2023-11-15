package christmas.repository;

import christmas.model.Order;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {

    private static final Map<Long, Order> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    private static final OrderRepository instance = new OrderRepository();

    public static OrderRepository getInstance() {
        return instance;
    }


    public Order save(Order order) {
        order.setId(++sequence);
        store.put(order.getId(), order);
        return order;
    }

    public Order findById(Long id) {
        return store.get(id);
    }

    public void clear() {
        store.clear();
    }

}
