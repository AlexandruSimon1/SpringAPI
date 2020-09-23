package com.application.mapper;

import com.application.dto.TableDTO;
import com.application.model.Table;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    Table fromTableDto(TableDTO tableDTO, @Context NotificatorMappingContext context);

    @InheritInverseConfiguration
    TableDTO toTableDto(Table table, @Context NotificatorMappingContext context);
}
