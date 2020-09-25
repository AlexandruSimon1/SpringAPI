package utils;


import com.application.utils.AdministratorController;
import com.application.utils.ExceptionController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.application.dto.AdminDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.application.service.dbImpl.AdministratorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdministratorController.class)
class AdministratorControllerTest {
    private static final Integer ID_VALUE = 1;
    private AdministratorController administratorController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdministratorServiceImpl administratorService;
    private AdminDTO adminDTO;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(administratorController)
                .setControllerAdvice(new ExceptionController()).alwaysExpect(MockMvcResultMatchers.content().
                        contentType(MediaType.APPLICATION_JSON)).build();
        adminDTO = new AdminDTO();
        adminDTO.setId(ID_VALUE);
        adminDTO.setFirstName("Marcus");
        adminDTO.setLastName("Polo");
        adminDTO.setAddress("Istanbul");
        adminDTO.setPhoneNumber(65658965463L);
        adminDTO.setEmail("marcus@polo.com");
    }

    @Test
    void getAllAdministrators() throws Exception {
        List<AdminDTO> adminDTOList = new ArrayList<>();
        adminDTOList.add(adminDTO);
        when(administratorService.getAllAdministrators()).thenReturn(adminDTOList);
        mockMvc.perform(get("/api/v1/administrators")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAdministratorById() throws Exception {
        when(administratorService.getAdministratorById(anyInt())).thenReturn(adminDTO);
        this.mockMvc.perform(get("/api/v1/administrators/{administratorId}", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(ID_VALUE)));
    }

    @Test
    void deleteAdministratorById() throws Exception {
        when(administratorService.deleteAdministratorById(ID_VALUE)).thenReturn(adminDTO);
        this.mockMvc.perform(delete("/api/v1/administrators/{administratorId}", adminDTO.getId()))
                .andExpect(status().is2xxSuccessful());
        verify(administratorService, times(1)).deleteAdministratorById(ID_VALUE);
    }

    @Test
    void updateAdministratorByID() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AdminDTO toUpdate = new AdminDTO();
        toUpdate.setFirstName("Alex");
        when(administratorService.update(toUpdate, anyInt())).thenReturn(toUpdate);
        this.mockMvc.perform(put("/api/v1/administrators/{administratorId}", adminDTO.getId().
                toString()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toUpdate)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", Matchers.is(toUpdate.getFirstName())));
    }

    @Test
    void createAdministrator() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        AdminDTO create=new AdminDTO();
        create.setFirstName("Alex");
        create.setLastName("Rock");
        when(administratorService.createAdministrator(create)).thenReturn(adminDTO);
        this.mockMvc.perform(post("/api/v1/administrators",create)).andExpect(status().isCreated());
    }
}
