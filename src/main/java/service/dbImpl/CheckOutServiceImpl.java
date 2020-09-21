package service.dbImpl;

import dto.CheckOutDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.CheckOutMapper;
import mapper.NotificatorMappingContext;
import mapper.OrderMapper;
import model.CheckOut;
import repository.CheckOutRepository;
import service.CheckOutService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CheckOutServiceImpl implements CheckOutService {
    private final CheckOutRepository checkOutRepository;

    @Override
    public List<CheckOutDTO> getAllCheckOut() {
        return checkOutRepository.findAll().stream().
                map(checkOut -> CheckOutMapper.INSTANCE.toCheckOutDto(checkOut, new NotificatorMappingContext())).
                collect(Collectors.toList());
    }

    @Override
    public CheckOutDTO getCheckOutById(int checkOutId) {
        final CheckOut getCheckOut = checkOutRepository.findById(checkOutId).
                orElseThrow(() -> new ApplicationException(ExceptionType.CHECKOUT_NOT_FOUND));
        return CheckOutMapper.INSTANCE.toCheckOutDto(getCheckOut, new NotificatorMappingContext());
    }

    @Override
    public CheckOutDTO deleteCheckOutById(int checkOutId) {
        final CheckOut deleteCheckOut = checkOutRepository.findById(checkOutId).
                orElseThrow(() -> new ApplicationException(ExceptionType.CHECKOUT_NOT_FOUND));
        return CheckOutMapper.INSTANCE.toCheckOutDto(deleteCheckOut, new NotificatorMappingContext());
    }

    @Override
    public CheckOutDTO createCheckOut(CheckOutDTO checkOutDTO) {
        final CheckOut createCheckOut = CheckOutMapper.INSTANCE.fromCheckOutDto(checkOutDTO, new NotificatorMappingContext());
        final CheckOut saveCheckOut = checkOutRepository.save(createCheckOut);
        return CheckOutMapper.INSTANCE.toCheckOutDto(saveCheckOut, new NotificatorMappingContext());
    }

    @Override
    public CheckOutDTO update(CheckOutDTO checkOutDTO, int checkOutId) {
        final CheckOut updateCheckOut = checkOutRepository.findById(checkOutId).
                orElseThrow(() -> new ApplicationException(ExceptionType.CHECKOUT_NOT_FOUND));
        updateCheckOut.setPaymentType(checkOutDTO.getPaymentType());
        updateCheckOut.setOrder(OrderMapper.INSTANCE.
                fromOrderDto(checkOutDTO.getOrderDTO(), new NotificatorMappingContext()));
        checkOutRepository.save(updateCheckOut);
        return CheckOutMapper.INSTANCE.toCheckOutDto(updateCheckOut, new NotificatorMappingContext());
    }
}
