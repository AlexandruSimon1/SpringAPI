package service.dbImpl;

import dto.TableDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.NotificatorMappingContext;
import mapper.TableMapper;
import model.Table;
import repository.TableRepository;
import service.TableService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Override
    public List<TableDTO> getAllTable() {
        return tableRepository.findAll().stream().filter(Objects::isNull).
                map(table -> TableMapper.INSTANCE.toTableDto(table, new NotificatorMappingContext())).
                collect(Collectors.toList());
    }

    @Override
    public TableDTO getTableById(int tableId) {
        final Table getTable = tableRepository.findById(tableId).
                orElseThrow(() -> new ApplicationException(ExceptionType.TABLE_NOT_FOUND));
        return TableMapper.INSTANCE.toTableDto(getTable, new NotificatorMappingContext());
    }

    @Override
    public TableDTO deleteTableById(int tableId) {
        final Table deleteTable = tableRepository.findById(tableId).
                orElseThrow(() -> new ApplicationException(ExceptionType.TABLE_NOT_FOUND));
        return TableMapper.INSTANCE.toTableDto(deleteTable, new NotificatorMappingContext());
    }

    @Override
    public TableDTO createTable(TableDTO tableDTO) {
        final Table createTable = TableMapper.INSTANCE.fromTableDto(tableDTO, new NotificatorMappingContext());
        final Table saveTable = tableRepository.save(createTable);
        return TableMapper.INSTANCE.toTableDto(saveTable, new NotificatorMappingContext());
    }

    @Override
    public TableDTO update(TableDTO tableDTO, int tableId) {
        final Table updateTable = tableRepository.findById(tableId).
                orElseThrow(() -> new ApplicationException(ExceptionType.TABLE_NOT_FOUND));
        updateTable.setNumber(tableDTO.getNumber());
        tableRepository.save(updateTable);
        return TableMapper.INSTANCE.toTableDto(updateTable, new NotificatorMappingContext());
    }
}
