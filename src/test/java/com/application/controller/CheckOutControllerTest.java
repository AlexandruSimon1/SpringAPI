package com.application.controller;

import com.application.controller.CheckOutController;
import com.application.dto.CheckOutDTO;
import com.application.service.dbImpl.CheckOutServiceImpl;
import com.application.utils.ExceptionController;
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
@ContextConfiguration(classes = CheckOutController.class)
class CheckOutControllerTest {
    private static final int ID_VALUE = 1;
    @Autowired
    private CheckOutController controller;
    private MockMvc mockMvc;
    @MockBean
    private CheckOutServiceImpl checkOutService;
    private CheckOutDTO checkOutDTO;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController()).alwaysExpect(MockMvcResultMatchers.content().
                        contentType(MediaType.APPLICATION_JSON)).build();
        checkOutDTO = new CheckOutDTO();
        checkOutDTO.setId(ID_VALUE);
        checkOutDTO.setPaymentType("Visa");
    }

    @Test
    void getAllCheckOut() throws Exception {
        List<CheckOutDTO> checkOutDTOList = new ArrayList<>();
        checkOutDTOList.add(checkOutDTO);
        when(checkOutService.getAllCheckOut()).thenReturn(checkOutDTOList);
        mockMvc.perform(get("/api/v1/checkout")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getCheckOutById() throws Exception {
        when(checkOutService.getCheckOutById(anyInt())).thenReturn(checkOutDTO);
        this.mockMvc.perform(get("/api/v1/checkout/{checkoutId}", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(ID_VALUE)));
    }

    @Test
    void deleteCheckOutById() throws Exception {
        when(checkOutService.deleteCheckOutById(ID_VALUE)).thenReturn(checkOutDTO);
        this.mockMvc.perform(delete("/api/v1/checkout/{checkoutId}", checkOutDTO.getId()))
                .andExpect(status().is2xxSuccessful());
        verify(checkOutService, times(1)).deleteCheckOutById(ID_VALUE);
    }

    @Test
    void updateCheckOutById() throws Exception {
        CheckOutDTO toUpdate = new CheckOutDTO();
        toUpdate.setId(ID_VALUE);

        Mockito.when(checkOutService.update(toUpdate,ID_VALUE)).thenReturn(toUpdate);
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.put("/api/v1/checkout/1",toUpdate)
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(toUpdate));

        mockMvc.perform(builder).andExpect(status().isAccepted()).andExpect(jsonPath("$.id", is(ID_VALUE)))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(toUpdate)));
    }
    @Test
    void createCheckOut() throws Exception {
        CheckOutDTO toCreate=new CheckOutDTO();
        toCreate.setPaymentType("Visa");
        toCreate.setId(ID_VALUE);
        when(checkOutService.createCheckOut(Mockito.any(CheckOutDTO.class))).thenReturn(toCreate);
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/api/v1/checkout").
                contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(toCreate));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.paymentType",is("Visa"))).
                andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(toCreate)));
    }
}
