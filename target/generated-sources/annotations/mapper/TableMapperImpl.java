package mapper;

import dto.TableDTO;
import javax.annotation.Generated;
import model.Table;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-22T11:02:44+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
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

        if ( tableDTO.getTableId() != null ) {
            table.setTableId( tableDTO.getTableId() );
        }
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

        tableDTO.setTableId( table.getTableId() );
        tableDTO.setNumber( table.getNumber() );

        return tableDTO;
    }
}