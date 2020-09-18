package service.dbImpl;

import dto.MenuDTO;
import dto.OrderDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.MenuMapper;
import mapper.NotificatorMappingContext;
import mapper.OrderMapper;
import mapper.TableMapper;
import model.Menu;
import model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MenuRepository;
import repository.OrderRepository;
import service.OrderService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().filter(Objects::isNull).
                map(order -> OrderMapper.INSTANCE.toOrderDto(order, new NotificatorMappingContext())).
                collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int orderNumber) {
        final Order getOrder = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        return OrderMapper.INSTANCE.toOrderDto(getOrder, new NotificatorMappingContext());
    }

    @Override
    public OrderDTO deleteOrderById(int orderNumber) {
        final Order deleteOrder = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        return OrderMapper.INSTANCE.toOrderDto(deleteOrder, new NotificatorMappingContext());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        final Order createOrder = OrderMapper.INSTANCE.fromOrderDto(orderDTO, new NotificatorMappingContext());
        final Order saveOrder = orderRepository.save(createOrder);
        return OrderMapper.INSTANCE.toOrderDto(saveOrder, new NotificatorMappingContext());
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, int orderNumber) {
        final Order updateOrder = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        updateOrder.setQuantity(orderDTO.getQuantity());
        updateOrder.addMenu(MenuMapper.INSTANCE.fromMenuDto(orderDTO.getMenuDTO(), new NotificatorMappingContext()));
        updateOrder.setTable(TableMapper.INSTANCE.fromTableDto(orderDTO.getTableDTO(), new NotificatorMappingContext()));
        orderRepository.save(updateOrder);
        return OrderMapper.INSTANCE.toOrderDto(updateOrder, new NotificatorMappingContext());
    }

    @Override
    public Set<MenuDTO> findAllProductByOrderId(int orderNumber) {
        Order order = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        return order.getMenus().stream().filter(Objects::isNull).
                map(menu -> MenuMapper.INSTANCE.toMenuDto(menu, new NotificatorMappingContext())).collect(Collectors.toSet());
    }

    @Override
    public MenuDTO updateProductByOrderId(int orderNumber, int removed_productId, MenuDTO newMenuDTO) {
        Order order = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        Optional<Menu> deleteProduct = order.getMenus().stream().
                filter(menu -> Objects.equals(menu.getMenuId(), removed_productId)).findFirst();
        if (!deleteProduct.isPresent()) {
            throw new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND);
        }
        Menu updateProduct = deleteProduct.get();
        updateProduct.setCategoryType(newMenuDTO.getCategoryType());
        updateProduct.setName(newMenuDTO.getName());
        updateProduct.setDescription(newMenuDTO.getDescription());
        updateProduct.setPrice(newMenuDTO.getPrice());
        Menu saveProduct = menuRepository.save(updateProduct);
        return MenuMapper.INSTANCE.toMenuDto(saveProduct, new NotificatorMappingContext());
    }

    @Override
    public MenuDTO deleteProductByOrderId(int orderNumber, int removed_productId) {
        final Order order = orderRepository.findById(orderNumber).
                orElseThrow(() -> new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
        final Menu deleteProduct = menuRepository.findById(removed_productId).
                orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
        menuRepository.deleteById(removed_productId);
        order.getMenus().remove(deleteProduct);
        return MenuMapper.INSTANCE.toMenuDto(deleteProduct, new NotificatorMappingContext());
    }
}
