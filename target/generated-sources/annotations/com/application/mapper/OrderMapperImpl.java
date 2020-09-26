package com.application.mapper;

import com.application.dto.MenuDTO;
import com.application.dto.OrderDTO;
import com.application.model.Menu;
import com.application.model.Order;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-26T09:25:21+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order, NotificatorMappingContext context) {
        OrderDTO target = context.getMappedInstance( order, OrderDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        context.storedMappedInstance( order, orderDTO );

        orderDTO.setId( order.getId() );
        orderDTO.setOrderNumber( order.getOrderNumber() );
        orderDTO.setMenus( menuListToMenuDTOSet( order.getMenus(), context ) );

        return orderDTO;
    }

    @Override
    public Order fromDTO(OrderDTO orderDTO, NotificatorMappingContext context) {
        Order target = context.getMappedInstance( orderDTO, Order.class );
        if ( target != null ) {
            return target;
        }

        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        context.storedMappedInstance( orderDTO, order );

        order.setId( orderDTO.getId() );
        order.setOrderNumber( orderDTO.getOrderNumber() );
        order.setMenus( menuDTOSetToMenuList( orderDTO.getMenus(), context ) );

        return order;
    }

    protected MenuDTO menuToMenuDTO(Menu menu, NotificatorMappingContext context) {
        MenuDTO target = context.getMappedInstance( menu, MenuDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( menu == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        context.storedMappedInstance( menu, menuDTO );

        menuDTO.setId( menu.getId() );
        menuDTO.setCategoryType( menu.getCategoryType() );
        menuDTO.setName( menu.getName() );
        menuDTO.setDescription( menu.getDescription() );
        menuDTO.setPrice( menu.getPrice() );

        return menuDTO;
    }

    protected Set<MenuDTO> menuListToMenuDTOSet(List<Menu> list, NotificatorMappingContext context) {
        Set<MenuDTO> target = context.getMappedInstance( list, Set.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        Set<MenuDTO> set = new HashSet<MenuDTO>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        context.storedMappedInstance( list, set );

        for ( Menu menu : list ) {
            set.add( menuToMenuDTO( menu, context ) );
        }

        return set;
    }

    protected Menu menuDTOToMenu(MenuDTO menuDTO, NotificatorMappingContext context) {
        Menu target = context.getMappedInstance( menuDTO, Menu.class );
        if ( target != null ) {
            return target;
        }

        if ( menuDTO == null ) {
            return null;
        }

        Menu menu = new Menu();

        context.storedMappedInstance( menuDTO, menu );

        menu.setId( menuDTO.getId() );
        menu.setCategoryType( menuDTO.getCategoryType() );
        menu.setName( menuDTO.getName() );
        menu.setDescription( menuDTO.getDescription() );
        menu.setPrice( menuDTO.getPrice() );

        return menu;
    }

    protected List<Menu> menuDTOSetToMenuList(Set<MenuDTO> set, NotificatorMappingContext context) {
        List<Menu> target = context.getMappedInstance( set, List.class );
        if ( target != null ) {
            return target;
        }

        if ( set == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( set.size() );
        context.storedMappedInstance( set, list );

        for ( MenuDTO menuDTO : set ) {
            list.add( menuDTOToMenu( menuDTO, context ) );
        }

        return list;
    }
}
