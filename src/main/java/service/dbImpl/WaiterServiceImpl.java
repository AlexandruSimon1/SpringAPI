package service.dbImpl;

import dto.OrderDTO;
import dto.WaiterDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.NotificatorMappingContext;
import mapper.OrderMapper;
import mapper.WaiterMapper;
import model.Order;
import model.Waiter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;
import repository.WaiterRepository;
import service.WaiterService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WaiterServiceImpl implements WaiterService {
    private final WaiterRepository waiterRepository;
    //private final OrderRepository orderRepository;

    @Override
    public List<WaiterDTO> getAllWaiters() {
        return waiterRepository.findAll().stream().
                map(waiter -> WaiterMapper.INSTANCE.
                        toWaiterDto(waiter, new NotificatorMappingContext())).collect(Collectors.toList());
    }

    @Override
    public WaiterDTO getWaiterById(int waiterId) {
        final Waiter getWaiter = waiterRepository.findById(waiterId).
                orElseThrow(() -> new ApplicationException(ExceptionType.WAITER_NOT_FOUND));
        return WaiterMapper.INSTANCE.toWaiterDto(getWaiter, new NotificatorMappingContext());
    }

    @Override
    public WaiterDTO deleteWaiterById(int waiterId) {
        final Waiter deleteWaiter = waiterRepository.findById(waiterId).
                orElseThrow(() -> new ApplicationException(ExceptionType.WAITER_NOT_FOUND));
        return WaiterMapper.INSTANCE.toWaiterDto(deleteWaiter, new NotificatorMappingContext());
    }

    @Override
    public WaiterDTO createWaiter(WaiterDTO waiterDTO) {
        final Waiter createWaiter = WaiterMapper.INSTANCE.fromWaiterDto(waiterDTO, new NotificatorMappingContext());
        final Waiter saveWaiter = waiterRepository.save(createWaiter);
        return WaiterMapper.INSTANCE.toWaiterDto(saveWaiter, new NotificatorMappingContext());
    }

    @Override
    public WaiterDTO update(WaiterDTO waiterDTO, int waiterId) {
        final Waiter updateWaiter = waiterRepository.findById(waiterId).
                orElseThrow(() -> new ApplicationException(ExceptionType.WAITER_NOT_FOUND));
        updateWaiter.setFirstName(waiterDTO.getFirstName());
        updateWaiter.setLastName(waiterDTO.getLastName());
        updateWaiter.setDateOfBirth(waiterDTO.getDateOfBirth());
        updateWaiter.setAddress(waiterDTO.getAddress());
        updateWaiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        updateWaiter.setEmail(waiterDTO.getEmail());
        waiterRepository.save(updateWaiter);
        return WaiterMapper.INSTANCE.toWaiterDto(updateWaiter, new NotificatorMappingContext());
    }

//    @Override check how you can implement that waiter can check all his orders
//    public Set<OrderDTO> findAllOrders(int waiterId) {
//        Waiter waiter =waiterRepository.findById(waiterId).
//                orElseThrow(()->new ApplicationException(ExceptionType.ORDER_NOT_FOUND));
//        return waiter.getMenus().stream().filter(Objects::isNull).
//                map(OrderMapper.INSTANCE.toOrderDto(waiter,new NotificatorMappingContext())).collect(Collectors.toSet());
//    }
}
