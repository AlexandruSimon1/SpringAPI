package com.application.utils;

import com.application.dto.MenuDTO;
import com.application.dto.OrderDTO;
import com.application.service.dbImpl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    private static final int ID_VALUE = 1;
    @Autowired
    private OrderController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderServiceImpl orderService;
    private OrderDTO orderDTO;
    private Set<MenuDTO> menuDTO;
    private MenuDTO testMenuDTO;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController()).alwaysExpect(MockMvcResultMatchers.content().
                        contentType(MediaType.APPLICATION_JSON)).build();
        orderDTO = new OrderDTO();
        orderDTO.setId(ID_VALUE);
        orderDTO.setOrderNumber(1);

        menuDTO=new HashSet<>();
        testMenuDTO=new MenuDTO();
        testMenuDTO.setName("Pizza");
        testMenuDTO.setPrice(85);
        menuDTO.add(testMenuDTO);
        orderDTO.setMenus(menuDTO);
    }

    @Test
    void getAllOrders() throws Exception {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList.add(orderDTO);
        when(orderService.getAllOrders()).thenReturn(orderDTOList);
        mockMvc.perform(get("/api/v1/orders")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getOrderById() throws Exception {
        when(orderService.getOrderById(anyInt())).thenReturn(orderDTO);
        this.mockMvc.perform(get("/api/v1/orders/{orderId}", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(ID_VALUE)));
    }

    @Test
    void deleteOrderById() throws Exception {
        when(orderService.deleteOrderById(ID_VALUE)).thenReturn(orderDTO);
        this.mockMvc.perform(delete("/api/v1/orders/{orderId}", orderDTO.getId()))
                .andExpect(status().is2xxSuccessful());
        verify(orderService, times(1)).deleteOrderById(ID_VALUE);
    }

    @Test
    void updateOrderById() throws Exception {
        OrderDTO toUpdate = new OrderDTO();
        toUpdate.setId(ID_VALUE);
        toUpdate.setOrderNumber(ID_VALUE);

        Mockito.when(orderService.update(toUpdate, ID_VALUE)).thenReturn(toUpdate);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/orders/1", toUpdate)
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(toUpdate));

        mockMvc.perform(builder).andExpect(status().isAccepted()).andExpect(jsonPath("$.id", is(ID_VALUE)))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(toUpdate)));
    }

    @Test
    void createOrder() throws Exception {
        OrderDTO toCreate = new OrderDTO();
        toCreate.setOrderNumber(ID_VALUE);
        toCreate.setId(ID_VALUE);
        when(orderService.createOrder(Mockito.any(OrderDTO.class))).thenReturn(toCreate);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/orders").
                contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(toCreate));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(ID_VALUE))).
                andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(toCreate)));
    }
    @Test
    void getAllProductByOrderId() throws Exception {
        when(orderService.findAllProductByOrderId(anyInt())).thenReturn(menuDTO);
        this.mockMvc.perform(get("/api/v1/orders/{orderId}/menus", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", is(ID_VALUE)));


    }
}