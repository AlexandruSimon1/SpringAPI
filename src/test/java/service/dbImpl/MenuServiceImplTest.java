package service.dbImpl;

import com.application.dto.MenuDTO;
import com.application.dto.OrderDTO;
import com.application.model.Menu;
import com.application.model.Order;
import com.application.model.enums.CategoryType;
import com.application.service.dbImpl.MenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.application.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {
    private static final int ID_VALUE = 1;
    private Menu defaultMenu;
    private MenuDTO menuDTO;
    private MenuServiceImpl menuService;

    @Mock
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuService = new MenuServiceImpl(menuRepository);

        defaultMenu = new Menu();
        defaultMenu.setProductId(ID_VALUE);
        defaultMenu.setCategoryType(CategoryType.PIZZA);
        defaultMenu.setName("Carbonara");
        defaultMenu.setDescription("Pasta with bacon and parmesan");
        defaultMenu.setPrice(85);
    }

    @Test
    void getAllProducts() {
        List<Menu> getAllProducts = new ArrayList<>();
        getAllProducts.add(defaultMenu);
        when(menuRepository.findAll()).thenReturn(getAllProducts);
        List<MenuDTO> menuDTOS = menuService.getAllMenu();
        assertEquals(menuDTOS.size(), getAllProducts.size());
        verify(menuRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        Menu menuTest=new Menu();
        menuTest.setProductId(ID_VALUE);
        when(menuRepository.findById(ID_VALUE)).thenReturn(Optional.of(menuTest));
        MenuDTO menuDTO = menuService.getProductById(ID_VALUE);

        assertEquals(menuDTO.getProductId(),menuTest.getProductId());
        verify(menuRepository, times(1)).findById(ID_VALUE);
    }

    @Test
    void deleteProductById() {
        when(menuRepository.findById(ID_VALUE)).thenReturn(Optional.of(defaultMenu));
        menuRepository.deleteById(ID_VALUE);
        verify(menuRepository, times(1)).deleteById(ID_VALUE);
        MenuDTO menuDTO = menuService.deleteProductById(ID_VALUE);
        assertEquals(menuDTO.getProductId(), defaultMenu.getProductId());
    }

    @Test
    void updateProductByID() {
        MenuDTO updateProduct = new MenuDTO();
        updateProduct.setCategoryType(CategoryType.SALAD);
        updateProduct.setName("Silicy");
        updateProduct.setDescription("Eggplant salad with tomatoes and Mozzarella");
        updateProduct.setPrice(65);

        when(menuRepository.findById(ID_VALUE)).thenReturn(Optional.of(defaultMenu));
        when(menuRepository.save(defaultMenu)).thenReturn(defaultMenu);

        MenuDTO updatedMenu = menuService.update(updateProduct, ID_VALUE);
        assertEquals(updatedMenu.getProductId(), defaultMenu.getProductId());
        assertEquals(updatedMenu.getName(), updateProduct.getName());
        assertEquals(updatedMenu.getDescription(), updateProduct.getDescription());
        assertEquals(updatedMenu.getPrice(), updateProduct.getPrice());
        assertEquals(updatedMenu.getCategoryType(), updateProduct.getCategoryType());
    }

    @Test
    void createProduct() {
        Menu createProductMenu = new Menu();
        createProductMenu.setProductId(ID_VALUE);
        createProductMenu.setName("Mr Nico");
        createProductMenu.setDescription("Ice cream with couple of chocolate");
        createProductMenu.setCategoryType(CategoryType.DESSERT);
        createProductMenu.setPrice(85);
        MenuDTO createdProduct = new MenuDTO();
        createdProduct.setProductId(ID_VALUE);
        createdProduct.setName("Mr Nico");
        createdProduct.setDescription("Ice cream with couple of chocolate");
        createdProduct.setCategoryType(CategoryType.DESSERT);
        createdProduct.setPrice(85);
        when(menuRepository.save(createProductMenu)).thenReturn(createProductMenu);

        MenuDTO finalProduct = menuService.createProduct(createdProduct);
        assertEquals(finalProduct.getProductId(), createProductMenu.getProductId());
        assertEquals(finalProduct.getName(), createProductMenu.getName());
        assertEquals(finalProduct.getDescription(), createProductMenu.getDescription());
        assertEquals(finalProduct.getCategoryType(), createProductMenu.getCategoryType());
        assertEquals(finalProduct.getPrice(), createProductMenu.getPrice());
    }
}
