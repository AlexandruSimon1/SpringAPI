package com.application.mapper;

import com.application.dto.OrderDTO;
import com.application.model.Order;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-23T12:44:52+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order fromOrderDto(OrderDTO orderDTO, NotificatorMappingContext context) {
        Order target = context.getMappedInstance( orderDTO, Order.class );
        if ( target != null ) {
            return target;
        }

        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        context.storedMappedInstance( orderDTO, order );

        order.setOrderId( orderDTO.getOrderId() );
        order.setOrderNumber( orderDTO.getOrderNumber() );
        order.setQuantity( orderDTO.getQuantity() );

        return order;
    }

    @Override
    public OrderDTO toOrderDto(Order order, NotificatorMappingContext context) {
        OrderDTO target = context.getMappedInstance( order, OrderDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        context.storedMappedInstance( order, orderDTO );

        orderDTO.setOrderId( order.getOrderId() );
        orderDTO.setOrderNumber( order.getOrderNumber() );
        orderDTO.setQuantity( order.getQuantity() );

        return orderDTO;
    }
}
