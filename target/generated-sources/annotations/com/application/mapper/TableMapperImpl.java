package com.application.mapper;

import com.application.dto.TableDTO;
import com.application.model.Table;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-25T09:16:21+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
public class TableMapperImpl implements TableMapper {

    @Override
    public Table fromTableDto(TableDTO tableDTO, NotificatorMappingContext context) {
        Table target = context.getMappedInstance( tableDTO, Table.class );
        if ( target != null ) {
            return target;
        }

        if ( tableDTO == null ) {
            return null;
        }

        Table table = new Table();

        context.storedMappedInstance( tableDTO, table );

        table.setId( tableDTO.getId() );
        table.setNumber( tableDTO.getNumber() );

        return table;
    }

    @Override
    public TableDTO toTableDto(Table table, NotificatorMappingContext context) {
        TableDTO target = context.getMappedInstance( table, TableDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( table == null ) {
            return null;
        }

        TableDTO tableDTO = new TableDTO();

        context.storedMappedInstance( table, tableDTO );

        tableDTO.setId( table.getId() );
        tableDTO.setNumber( table.getNumber() );

        return tableDTO;
    }
}
