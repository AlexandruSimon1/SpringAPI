package service;

import dto.MenuDTO;
import dto.OrderDTO;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(int orderId);

    OrderDTO deleteOrderById(int orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO, int orderId);

    Set<MenuDTO> findAllProductByOrder(int orderId);

    MenuDTO updateProductOnOrder(int orderId,int removed_productId,MenuDTO newMenuDTO);

    MenuDTO deleteAllProductFromOrder(int orderId,int productId);
}
