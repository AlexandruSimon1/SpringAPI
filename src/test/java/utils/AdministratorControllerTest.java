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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdministratorController.class)
class AdministratorControllerTest {
    private static final int ID_VALUE = 1;
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
        adminDTO.setAdminId(ID_VALUE);
        adminDTO.setFirstName("Marcus");
        adminDTO.setLastName("Polo");
        //adminDTO.setDateOfBirth("1964 - 05 - 06");
        adminDTO.setAddress("Istanbul");
        adminDTO.setPhoneNumber(65658965463L);
        adminDTO.setEmail("marcus@polo.com");
    }

    @Test
    void getAllAdministrators() throws Exception {
        List<AdminDTO> adminDTOList = new ArrayList<>();
        adminDTOList.add(adminDTO);
        when(administratorService.getAllAdministrators()).thenReturn(adminDTOList);
        mockMvc.perform(get("main/v1/administrators")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAdministratorById() throws Exception {
        when(administratorService.getAdministratorById(anyInt())).thenReturn(adminDTO);
        this.mockMvc.perform(get("main/v1/administrators/{administratorId}", ID_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(ID_VALUE)));
    }

    @Test
    void deleteAdministratorById() throws Exception {
        when(administratorService.deleteAdministratorById(ID_VALUE)).thenReturn(adminDTO);
        this.mockMvc.perform(delete("main/v1/administrators/{administratorId}", adminDTO.getAdminId()))
                .andExpect(status().is2xxSuccessful());
        verify(administratorService, times(1)).deleteAdministratorById(ID_VALUE);
    }

    @Test
    void updateAdministratorByID() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AdminDTO toUpdate = new AdminDTO();
        toUpdate.setFirstName("Alex");
        when(administratorService.update(toUpdate, anyInt())).thenReturn(toUpdate);
        this.mockMvc.perform(put("main/v1/administrators/{administratorId}", adminDTO.getAdminId().
                toString()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toUpdate)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", Matchers.is(toUpdate.getFirstName())));
    }
}
