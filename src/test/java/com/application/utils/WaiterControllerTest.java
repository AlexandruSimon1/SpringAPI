package com.application.utils;

import com.application.dto.WaiterDTO;
import com.application.service.dbImpl.WaiterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WaiterController.class)
class WaiterControllerTest {
    private static final Integer ID_VALUE = 1;
    @Autowired
    private WaiterController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WaiterServiceImpl waiterService;
    private WaiterDTO waiterDTO;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionController()).alwaysExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).build();
        waiterDTO = new WaiterDTO();
        waiterDTO.setId(ID_VALUE);
        waiterDTO.setFirstName("Marcus");
        waiterDTO.setLastName("Polo");
    }
    @Test
    void getAllWaiters() throws Exception {
        List<WaiterDTO> dtos = new ArrayList<>();
        dtos.add(waiterDTO);
        when(waiterService.getAllWaiters()).thenReturn(dtos);

        mockMvc.perform(get("/api/v1/waiters"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    void getWaiterById() throws Exception {
        when(waiterService.getWaiterById(anyInt())).thenReturn(waiterDTO);
        this.mockMvc.perform(get("/api/v1/waiters/{waiterId}", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(ID_VALUE)));
    }

    @Test
    void deleteWaiterById() throws Exception {
        when(waiterService.deleteWaiterById(ID_VALUE)).thenReturn(waiterDTO);
        this.mockMvc.perform(delete("/api/v1/waiters/{waiterId}", waiterDTO.getId()))
                .andExpect(status().is2xxSuccessful());
        verify(waiterService, times(1)).deleteWaiterById(ID_VALUE);
    }

    @Test
    void updateWaiterById() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        WaiterDTO toUpdate = new WaiterDTO();
        toUpdate.setPhoneNumber(9564632639l);

        when(waiterService.update(any(), anyInt())).thenReturn(toUpdate);

        this.mockMvc.perform(put("/api/v1/waiters/{waiterId}", waiterDTO.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(toUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("phoneNumber", Matchers.is(toUpdate.getPhoneNumber())));
    }
    @Test
    void createWaiter() throws Exception {
        WaiterDTO toCreate=new WaiterDTO();
        toCreate.setFirstName("Alex");
        toCreate.setLastName("Rock");
        toCreate.setAddress("Moscow");
        when(waiterService.createWaiter(Mockito.any(WaiterDTO.class))).thenReturn(toCreate);
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/api/v1/waiters").
                contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(toCreate));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.firstName",is("Alex"))).
                andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(toCreate)));
    }
}
