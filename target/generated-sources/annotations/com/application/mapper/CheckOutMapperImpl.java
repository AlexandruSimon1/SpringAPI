package com.application.mapper;

import com.application.dto.CheckOutDTO;
import com.application.model.CheckOut;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-26T09:25:21+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class CheckOutMapperImpl implements CheckOutMapper {

    @Override
    public CheckOutDTO toCheckOutDto(CheckOut checkOut, NotificatorMappingContext context) {
        CheckOutDTO target = context.getMappedInstance( checkOut, CheckOutDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( checkOut == null ) {
            return null;
        }

        CheckOutDTO checkOutDTO = new CheckOutDTO();

        context.storedMappedInstance( checkOut, checkOutDTO );

        checkOutDTO.setId( checkOut.getId() );
        checkOutDTO.setPaymentType( checkOut.getPaymentType() );

        return checkOutDTO;
    }

    @Override
    public CheckOut fromCheckOutDto(CheckOutDTO checkOutDTO, NotificatorMappingContext context) {
        CheckOut target = context.getMappedInstance( checkOutDTO, CheckOut.class );
        if ( target != null ) {
            return target;
        }

        if ( checkOutDTO == null ) {
            return null;
        }

        CheckOut checkOut = new CheckOut();

        context.storedMappedInstance( checkOutDTO, checkOut );

        checkOut.setId( checkOutDTO.getId() );
        checkOut.setPaymentType( checkOutDTO.getPaymentType() );

        return checkOut;
    }
}
