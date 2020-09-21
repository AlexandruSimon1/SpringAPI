package mapper;

import dto.AdminDTO;
import model.Administrator;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdministratorMapper {
    AdministratorMapper INSTANCE = Mappers.getMapper(AdministratorMapper.class);

    Administrator fromAdministratorDto(AdminDTO adminDTO, @Context NotificatorMappingContext context);

    @InheritInverseConfiguration
    AdminDTO toAdministratorDto(Administrator administrator, @Context NotificatorMappingContext context);
}
