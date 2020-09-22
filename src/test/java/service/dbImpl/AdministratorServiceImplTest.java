package service.dbImpl;

import com.application.model.Administrator;
import com.application.service.dbImpl.AdministratorServiceImpl;
import com.application.dto.AdminDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.application.repository.AdministratorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdministratorServiceImplTest {
    private static final int ID_VALUE = 1;
    private Administrator firstAdministrator;
    private Administrator secondAdministrator;
    @InjectMocks
    private AdministratorServiceImpl administratorService;
    @Mock
    private AdministratorRepository administratorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        administratorService = new AdministratorServiceImpl(administratorRepository);

        firstAdministrator = new Administrator();
        firstAdministrator.setAdminId(1);
        firstAdministrator.setFirstName("Max");
        firstAdministrator.setLastName("Cameron");
        firstAdministrator.setDateOfBirth(1990 - 04 - 06);
        firstAdministrator.setAddress("Douala");
        firstAdministrator.setPhoneNumber(3564789566L);
        firstAdministrator.setEmail("max@cameron.com");

        secondAdministrator = new Administrator();
        secondAdministrator.setAdminId(2);
        secondAdministrator.setFirstName("Dean");
        secondAdministrator.setLastName("Fox");
        secondAdministrator.setDateOfBirth(1985 - 04 - 06);
        secondAdministrator.setAddress("Barcelona");
        secondAdministrator.setPhoneNumber(3569875486L);
        secondAdministrator.setEmail("dean@fox.com");
    }

    @Test
    void getAllAdministrators() {
        List<Administrator> getAllAdmins = new ArrayList<>();
        getAllAdmins.add(firstAdministrator);
        getAllAdmins.add(secondAdministrator);

        when(administratorRepository.findAll()).thenReturn(getAllAdmins);

        List<AdminDTO> adminDTOS = administratorService.getAllAdministrators();
        assertEquals(getAllAdmins.size(), adminDTOS.size());
        verify(administratorRepository, times(1)).findAll();
    }

    @Test
    void getAdministratorById() {
        Administrator getAdmin = new Administrator();
        getAdmin.setAdminId(ID_VALUE);

        when(administratorRepository.findById(ID_VALUE)).thenReturn(Optional.of(getAdmin));
        AdminDTO adminDTO = administratorService.getAdministratorById(ID_VALUE);
        assertEquals(getAdmin.getAdminId(), adminDTO.getAdminId());
        verify(administratorRepository, times(1)).findById(ID_VALUE);
    }

    @Test
    void deleteAdministratorById() {
        Administrator deleteAdmin = new Administrator();
        deleteAdmin.setAdminId(ID_VALUE);

        when(administratorRepository.findById(ID_VALUE)).thenReturn(Optional.of(deleteAdmin));
        administratorRepository.deleteById(ID_VALUE);
        verify(administratorRepository, times(1)).deleteById(ID_VALUE);

        AdminDTO adminDTO = administratorService.deleteAdministratorById(ID_VALUE);
        assertEquals(deleteAdmin.getAdminId(), adminDTO.getAdminId());
    }

    @Test
    void createAdministrator() {
        Administrator createAdmin = new Administrator();
        createAdmin.setAdminId(ID_VALUE);
        createAdmin.setFirstName("Greg");
        createAdmin.setLastName("Eagle");
        createAdmin.setDateOfBirth(1990 - 11 - 11);
        createAdmin.setAddress("Istanbul");
        createAdmin.setPhoneNumber(54789654785L);
        createAdmin.setEmail("greg@eagle.com");

        AdminDTO dto = new AdminDTO();
        dto.setAdminId(ID_VALUE);
        dto.setFirstName("Greg");
        dto.setLastName("Eagle");
        dto.setDateOfBirth(1990 - 11 - 11);
        dto.setAddress("Istanbul");
        dto.setPhoneNumber(54789654785L);
        dto.setEmail("greg@eagle.com");

        when(administratorRepository.save(createAdmin)).thenReturn(createAdmin);
        AdminDTO adminDTO = administratorService.createAdministrator(dto);

        assertNotNull(adminDTO);
        assertEquals(createAdmin.getAdminId(), adminDTO.getAdminId());
        assertEquals(createAdmin.getFirstName(), adminDTO.getFirstName());
        assertEquals(createAdmin.getLastName(), adminDTO.getLastName());
        assertEquals(createAdmin.getDateOfBirth(), adminDTO.getDateOfBirth());
        assertEquals(createAdmin.getAddress(), adminDTO.getAddress());
        assertEquals(createAdmin.getPhoneNumber(), adminDTO.getPhoneNumber());
        assertEquals(createAdmin.getEmail(), adminDTO.getEmail());
    }

    @Test
    void updateAdministratorById() {
        Administrator updateAdministratorById = new Administrator();
        updateAdministratorById.setAdminId(ID_VALUE);
        updateAdministratorById.setFirstName("Dave");
        updateAdministratorById.setLastName("Foster");
        updateAdministratorById.setDateOfBirth(1977 - 10 - 10);
        updateAdministratorById.setAddress("Copenhagen");
        updateAdministratorById.setPhoneNumber(68956465963L);
        updateAdministratorById.setEmail("dave@foster.com");

        AdminDTO dto = new AdminDTO();
        dto.setAdminId(2);
        dto.setFirstName("Alex");
        dto.setLastName("Stone");
        dto.setDateOfBirth(1967 - 05 - 06);
        dto.setAddress("Larnaca");
        dto.setPhoneNumber(26548965475L);
        dto.setEmail("alex@stone.com");

        when(administratorRepository.findById(ID_VALUE)).thenReturn(Optional.of(updateAdministratorById));
        when(administratorRepository.save(updateAdministratorById)).thenReturn(updateAdministratorById);
        AdminDTO updatedAdmin = administratorService.update(dto, ID_VALUE);

        assertNotNull(updatedAdmin);
        assertEquals(updateAdministratorById.getAdminId(), updatedAdmin.getAdminId());
        assertEquals(updateAdministratorById.getFirstName(), updatedAdmin.getFirstName());
        assertEquals(updateAdministratorById.getLastName(), updatedAdmin.getLastName());
        assertEquals(updateAdministratorById.getDateOfBirth(), updatedAdmin.getDateOfBirth());
        assertEquals(updateAdministratorById.getAddress(), updatedAdmin.getAddress());
        assertEquals(updateAdministratorById.getPhoneNumber(), updatedAdmin.getPhoneNumber());
        assertEquals(updateAdministratorById.getEmail(), updatedAdmin.getEmail());

        verify(administratorRepository, times(1)).save(updateAdministratorById);
    }
}