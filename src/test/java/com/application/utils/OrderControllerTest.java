package com.application.utils;

import com.application.controller.OrderController;
import com.application.dto.MenuDTO;
import com.application.dto.OrderDTO;
import com.application.model.enums.CategoryType;
import com.application.service.dbImpl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OrderController.class)
class OrderControllerTest {
    private static final int ID_VALUE = 1;
    @Autowired
    private OrderController controller;
    private MockMvc mockMvc;
    @MockBean
    private OrderServiceImpl orderService;
    private OrderDTO orderDTO;
    private List<MenuDTO> menuDTO;
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
        orderDTO.setMenus(menuDTO);

        menuDTO = new ArrayList<>();
        testMenuDTO = new MenuDTO();
        testMenuDTO.setId(ID_VALUE);
        testMenuDTO.setName("Pizza");
        testMenuDTO.setCategoryType(CategoryType.PIZZA);
        testMenuDTO.setPrice(85);
        menuDTO.add(testMenuDTO);
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
        when(orderService.findAllProductByOrderId(ID_VALUE)).thenReturn(menuDTO);
        this.mockMvc.perform(get("/api/v1/orders/{orderId}/menus", ID_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void updateProductByOrderId() throws Exception {
        Mockito.when(orderService.updateProductByOrderId(ID_VALUE, ID_VALUE, testMenuDTO)).thenReturn(testMenuDTO);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/orders/1/menus/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(testMenuDTO));
        mockMvc.perform(builder).andExpect(status().isAccepted()).andExpect(jsonPath("$.id", is(ID_VALUE)))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(testMenuDTO)));
    }
//    @Test
//    void deleteProductByOrderId()throws Exception{
//        Mockito.when(orderService.deleteProductByOrderId(ID_VALUE,ID_VALUE)).thenReturn(testMenuDTO);
//        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.delete("/api/v1/orders/{orderId}",orderDTO.getId())
//                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//                .content(this.mapper.writeValueAsBytes(testMenuDTO));
//        mockMvc.perform(builder).andExpect(status().is2xxSuccessful()).andExpect(jsonPath(".id",is(ID_VALUE)))
//                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(testMenuDTO)));
////        this.mockMvc.perform(delete("/api/v1/orders/{orderId}",orderDTO.getId(),"/menus/{productId}",testMenuDTO.getId()))
////                .andExpect(status().is2xxSuccessful());
////        verify(orderService,times(1)).deleteProductByOrderId(ID_VALUE,ID_VALUE);
//    }
}
