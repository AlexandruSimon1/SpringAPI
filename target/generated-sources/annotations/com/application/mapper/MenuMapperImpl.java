package com.application.mapper;

import com.application.dto.MenuDTO;
import com.application.model.Menu;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-23T12:44:52+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu fromMenuDto(MenuDTO menuDTO, NotificatorMappingContext context) {
        Menu target = context.getMappedInstance( menuDTO, Menu.class );
        if ( target != null ) {
            return target;
        }

        if ( menuDTO == null ) {
            return null;
        }

        Menu menu = new Menu();

        context.storedMappedInstance( menuDTO, menu );

        menu.setProductId( menuDTO.getProductId() );
        menu.setCategoryType( menuDTO.getCategoryType() );
        menu.setName( menuDTO.getName() );
        menu.setDescription( menuDTO.getDescription() );
        menu.setPrice( menuDTO.getPrice() );

        return menu;
    }

    @Override
    public MenuDTO toMenuDto(Menu menu, NotificatorMappingContext context) {
        MenuDTO target = context.getMappedInstance( menu, MenuDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( menu == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        context.storedMappedInstance( menu, menuDTO );

        menuDTO.setProductId( menu.getProductId() );
        menuDTO.setCategoryType( menu.getCategoryType() );
        menuDTO.setName( menu.getName() );
        menuDTO.setDescription( menu.getDescription() );
        menuDTO.setPrice( menu.getPrice() );

        return menuDTO;
    }
}
