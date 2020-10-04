package com.application.mapper;

import com.application.dto.MenuDTO;
import com.application.model.Menu;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    MenuDTO toMenuDto(Menu menu, @Context NotificatorMappingContext context);

    @InheritInverseConfiguration
    Menu fromMenuDto(MenuDTO menuDTO, @Context NotificatorMappingContext context);
}