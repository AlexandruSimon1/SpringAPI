package com.application.mapper;

import com.application.dto.CheckOutDTO;
import com.application.model.CheckOut;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-25T09:16:21+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
public class CheckOutMapperImpl implements CheckOutMapper {

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
}
