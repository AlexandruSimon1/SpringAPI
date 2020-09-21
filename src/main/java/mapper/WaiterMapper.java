package mapper;

import dto.WaiterDTO;
import model.Waiter;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WaiterMapper {
    WaiterMapper INSTANCE = Mappers.getMapper(WaiterMapper.class);

    Waiter fromWaiterDto(WaiterDTO waiterDTO, @Context NotificatorMappingContext context);

    @InheritInverseConfiguration
    WaiterDTO toWaiterDto(Waiter waiter, @Context NotificatorMappingContext context);
}
