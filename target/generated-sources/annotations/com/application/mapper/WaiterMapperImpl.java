package com.application.mapper;

import com.application.dto.WaiterDTO;
import com.application.model.Waiter;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-22T11:29:18+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
public class WaiterMapperImpl implements WaiterMapper {

    @Override
    public Waiter fromWaiterDto(WaiterDTO waiterDTO, NotificatorMappingContext context) {
        Waiter target = context.getMappedInstance( waiterDTO, Waiter.class );
        if ( target != null ) {
            return target;
        }

        if ( waiterDTO == null ) {
            return null;
        }

        Waiter waiter = new Waiter();

        context.storedMappedInstance( waiterDTO, waiter );

        if ( waiterDTO.getWaiterId() != null ) {
            waiter.setWaiterId( waiterDTO.getWaiterId() );
        }
        waiter.setFirstName( waiterDTO.getFirstName() );
        waiter.setLastName( waiterDTO.getLastName() );
        waiter.setDateOfBirth( waiterDTO.getDateOfBirth() );
        waiter.setAddress( waiterDTO.getAddress() );
        waiter.setPhoneNumber( waiterDTO.getPhoneNumber() );
        waiter.setEmail( waiterDTO.getEmail() );

        return waiter;
    }

    @Override
    public WaiterDTO toWaiterDto(Waiter waiter, NotificatorMappingContext context) {
        WaiterDTO target = context.getMappedInstance( waiter, WaiterDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( waiter == null ) {
            return null;
        }

        WaiterDTO waiterDTO = new WaiterDTO();

        context.storedMappedInstance( waiter, waiterDTO );

        waiterDTO.setWaiterId( waiter.getWaiterId() );
        waiterDTO.setFirstName( waiter.getFirstName() );
        waiterDTO.setLastName( waiter.getLastName() );
        waiterDTO.setDateOfBirth( waiter.getDateOfBirth() );
        waiterDTO.setAddress( waiter.getAddress() );
        waiterDTO.setPhoneNumber( waiter.getPhoneNumber() );
        waiterDTO.setEmail( waiter.getEmail() );

        return waiterDTO;
    }
}
