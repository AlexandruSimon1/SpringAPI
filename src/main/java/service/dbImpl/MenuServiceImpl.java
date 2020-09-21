package service.dbImpl;

import dto.MenuDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.MenuMapper;
import mapper.NotificatorMappingContext;
import model.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MenuRepository;
import service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public List<MenuDTO> getAllMenu() {
        return menuRepository.findAll().stream().
                map(menu -> MenuMapper.INSTANCE.toMenuDto(menu, new NotificatorMappingContext())).collect(Collectors.toList());
    }

    @Override
    public MenuDTO getProductById(int productId) {
        final Menu getProduct = menuRepository.findById(productId).
                orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
        return MenuMapper.INSTANCE.toMenuDto(getProduct, new NotificatorMappingContext());
    }

    @Override
    public MenuDTO deleteProductById(int productId) {
        final Menu deleteProduct = menuRepository.findById(productId).
                orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
        return MenuMapper.INSTANCE.toMenuDto(deleteProduct, new NotificatorMappingContext());
    }

    @Override
    public MenuDTO createProduct(MenuDTO menuDTO) {
        final Menu createProduct = MenuMapper.INSTANCE.fromMenuDto(menuDTO, new NotificatorMappingContext());
        final Menu saveProduct = menuRepository.save(createProduct);
        return MenuMapper.INSTANCE.toMenuDto(saveProduct, new NotificatorMappingContext());
    }

    @Override
    public MenuDTO update(MenuDTO menuDTO, int productId) {
        final Menu updateProduct = menuRepository.findById(productId).
                orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
        updateProduct.setCategoryType(menuDTO.getCategoryType());
        updateProduct.setName(menuDTO.getName());
        updateProduct.setDescription(menuDTO.getDescription());
        updateProduct.setPrice(menuDTO.getPrice());
        menuRepository.save(updateProduct);
        return MenuMapper.INSTANCE.toMenuDto(updateProduct, new NotificatorMappingContext());
    }
}
