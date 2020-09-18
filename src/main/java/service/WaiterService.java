package service;

import dto.OrderDTO;
import dto.WaiterDTO;

import java.util.List;
import java.util.Set;

public interface WaiterService {
    List<WaiterDTO> getAllWaiters();

    WaiterDTO getWaiterById(int waiterId);

    WaiterDTO deleteWaiterById(int waiterId);

    WaiterDTO createWaiter(WaiterDTO waiterDTO);

    WaiterDTO update(WaiterDTO waiterDTO, int waiterId);

//    Set<OrderDTO> findAllOrders(int waiterId);
}
