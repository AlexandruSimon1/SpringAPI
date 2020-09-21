package mapper;

import dto.OrderDTO;
import model.Order;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order fromOrderDto(OrderDTO orderDTO, @Context NotificatorMappingContext context);

    @InheritInverseConfiguration
    OrderDTO toOrderDto(Order order, @Context NotificatorMappingContext context);
}