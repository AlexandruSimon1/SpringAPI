package service.dbImpl;

import com.application.dto.CheckOutDTO;
import com.application.model.CheckOut;
import com.application.model.Order;
import com.application.service.dbImpl.CheckOutServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.application.repository.CheckOutRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckOutServiceImplTest {
    private static final int ID_VALUE = 1;
    private CheckOutServiceImpl checkOutService;
    private Order order;
    private CheckOut checkOut;

    @Mock
    private CheckOutRepository checkOutRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        checkOutService = new CheckOutServiceImpl(checkOutRepository);
        checkOut = new CheckOut();
        checkOut.setCheckOutId(ID_VALUE);
        checkOut.setPaymentType("Visa");
    }

    @Test
    void getAllCheckOut() {
        List<CheckOut> checkOuts = new ArrayList<>();
        checkOuts.add(checkOut);
        when(checkOutRepository.findAll()).thenReturn(checkOuts);
        List<CheckOutDTO> checkOutDTOS = checkOutService.getAllCheckOut();
        assertNotNull(checkOutDTOS);
        assertEquals(checkOuts.size(), checkOutDTOS.size());
        verify(checkOutRepository, times(1)).findAll();
    }

    @Test
    void getCheckOutById() {
        when(checkOutRepository.findById(ID_VALUE)).thenReturn(Optional.of(checkOut));
        CheckOutDTO checkOutDTO = checkOutService.getCheckOutById(ID_VALUE);
        assertEquals(checkOut.getCheckOutId(), checkOutDTO.getCheckOutId());
        assertEquals(checkOut.getPaymentType(), checkOutDTO.getPaymentType());
        verify(checkOutRepository, times(1)).findById(ID_VALUE);
    }

    @Test
    void deleteCheckOutByID() {
        when(checkOutRepository.findById(ID_VALUE)).thenReturn(Optional.of(checkOut));
        checkOutRepository.deleteById(ID_VALUE);
        verify(checkOutRepository, times(1)).deleteById(ID_VALUE);
        CheckOutDTO checkOutDTO = checkOutService.deleteCheckOutById(ID_VALUE);
        assertEquals(ID_VALUE, checkOutDTO.getCheckOutId());
    }

    @Test
    void updateCheckOutById() {
        CheckOutDTO checkOutDTO = new CheckOutDTO();
        checkOutDTO.setCheckOutId(ID_VALUE);
        checkOutDTO.setPaymentType("Visa");

        when(checkOutRepository.findById(ID_VALUE)).thenReturn(Optional.of(checkOut));
        when(checkOutRepository.save(checkOut)).thenReturn(checkOut);

        CheckOutDTO updatedCheckOut = checkOutService.update(checkOutDTO, ID_VALUE);
        assertEquals(updatedCheckOut.getCheckOutId(), checkOut.getCheckOutId());
        assertEquals(updatedCheckOut.getPaymentType(), checkOut.getPaymentType());
    }

    @Test
    void createCheckOut() {
        CheckOut checkOutCreate = new CheckOut();
        checkOutCreate.setCheckOutId(ID_VALUE);
        checkOutCreate.setPaymentType("MasterCard");

        CheckOutDTO checkOutDTO = new CheckOutDTO();
        checkOutDTO.setCheckOutId(ID_VALUE);
        checkOutDTO.setPaymentType("MasterCard");
        when(checkOutRepository.save(checkOutCreate)).thenReturn(checkOutCreate);

        CheckOutDTO createCheckOut = checkOutService.createCheckOut(checkOutDTO);
        assertNotNull(createCheckOut);
        assertEquals(createCheckOut.getCheckOutId(), checkOutCreate.getCheckOutId());
        assertEquals(createCheckOut.getPaymentType(), checkOutCreate.getPaymentType());
    }
}
