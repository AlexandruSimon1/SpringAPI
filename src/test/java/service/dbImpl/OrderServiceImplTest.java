package service.dbImpl;

import com.application.dto.MenuDTO;
import com.application.dto.OrderDTO;
import com.application.dto.TableDTO;
import com.application.model.Menu;
import com.application.model.Order;
import com.application.model.Table;
import com.application.model.enums.CategoryType;
import com.application.service.dbImpl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.application.repository.MenuRepository;
import com.application.repository.OrderRepository;
import com.application.repository.TableRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private static final int ID_VALUE = 1;
    private OrderServiceImpl orderService;
    private OrderDTO dto;
    private Order order;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository, menuRepository, tableRepository);

        order = new Order();
        order.setId(ID_VALUE);
        order.setOrderNumber(ID_VALUE);
        order.setQuantity(ID_VALUE);

        dto = new OrderDTO();
        dto.setId(ID_VALUE);
        dto.setOrderNumber(ID_VALUE);
        dto.setQuantity(ID_VALUE);
    }

    @Test
    void getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(orderRepository.findAll()).thenReturn(orderList);
        List<OrderDTO> orderDTOList = orderService.getAllOrders();
        assertEquals(orderDTOList.size(), orderList.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderById() {
        when(orderRepository.findById(ID_VALUE)).thenReturn(Optional.of(order));
        OrderDTO orderDTO = orderService.getOrderById(ID_VALUE);

        assertEquals(order.getId(), orderDTO.getId());
        verify(orderRepository, times(1)).findById(ID_VALUE);
    }

    @Test
    void deleteOrderById() {
        when(orderRepository.findById(ID_VALUE)).thenReturn(Optional.of(order));
        orderRepository.deleteById(ID_VALUE);

        verify(orderRepository, times(1)).deleteById(ID_VALUE);
        OrderDTO orderDTO = orderService.deleteOrderById(ID_VALUE);
        assertEquals(order.getId(), orderDTO.getId());
    }

    @Test
    void updateOrder() {
        when(orderRepository.findById(ID_VALUE)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        OrderDTO updatedOrder = orderService.update(dto, ID_VALUE);

        assertNotNull(updatedOrder);
        assertEquals(ID_VALUE, updatedOrder.getId());
        assertEquals(dto.getOrderNumber(), updatedOrder.getOrderNumber());
    }

    @Test
    void createOrder() {
//        Table table = new Table();
//        table.setTableId(ID_VALUE);
//        table.setNumber(ID_VALUE);
//        //order.setTable(table);
//        when(tableRepository.findById(ID_VALUE)).thenReturn(Optional.of(table));
//
//        Set<Order> orderSet = new HashSet<>();
//        orderSet.add(order);
//        //when(orderRepository.findAllByTableID(ID_VALUE)).thenReturn(orderSet);
//
//        Menu menu = new Menu();
//        menu.setProductId(ID_VALUE);
//        menu.setName("Pollo");
//        menu.setDescription("Pasta with chicken, mushrooms and Parmesan cheese");
//        menu.setCategoryType(CategoryType.SOUP);
//        menu.setPrice(74);
//        when(menuRepository.findById(ID_VALUE)).thenReturn(Optional.of(menu));
//
        OrderDTO orderDTOList = new OrderDTO();
//        TableDTO tableDTO = new TableDTO();
//        tableDTO.setTableId(ID_VALUE);
//        tableDTO.setNumber(ID_VALUE);
//
//        MenuDTO menuDTO = new MenuDTO();
//        menuDTO.setProductId(ID_VALUE);
//        menuDTO.setName("Pollo");
//        menuDTO.setDescription("Pasta with chicken, mushrooms and Parmesan cheese");
//        menuDTO.setCategoryType(CategoryType.SOUP);
//        menuDTO.setPrice(74);

        Order createOrder = new Order();
        createOrder.setId(1);
        createOrder.setOrderNumber(1);
        createOrder.setQuantity(1);

        orderDTOList.setId(1);
        orderDTOList.setQuantity(1);
        orderDTOList.setOrderNumber(1);

        OrderDTO existingOrder = orderService.createOrder(orderDTOList);
        assertEquals(createOrder.getId(), existingOrder.getId());
        assertEquals(createOrder.getOrderNumber(),existingOrder.getOrderNumber());
        assertEquals(createOrder.getQuantity(),existingOrder.getQuantity());
    }
}
