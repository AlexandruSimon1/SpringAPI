package service;

import dto.MenuDTO;
import dto.OrderDTO;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(int orderNumber);

    OrderDTO deleteOrderById(int orderNumber);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO, int orderNumber);

    Set<MenuDTO> findAllProductByOrderId(int orderNumber);

    MenuDTO updateProductByOrderId(int orderNumber, int removed_productId, MenuDTO newMenuDTO);

    MenuDTO deleteProductByOrderId(int orderNumber, int removed_productId);
}
