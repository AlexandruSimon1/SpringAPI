package mapper;

import dto.AdminDTO;
import javax.annotation.Generated;
import model.Administrator;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-22T11:02:44+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
public class AdministratorMapperImpl implements AdministratorMapper {

    @Override
    public Administrator fromAdministratorDto(AdminDTO adminDTO, NotificatorMappingContext context) {
        Administrator target = context.getMappedInstance( adminDTO, Administrator.class );
        if ( target != null ) {
            return target;
        }

        if ( adminDTO == null ) {
            return null;
        }

        Administrator administrator = new Administrator();

        context.storedMappedInstance( adminDTO, administrator );

        if ( adminDTO.getAdminId() != null ) {
            administrator.setAdminId( adminDTO.getAdminId() );
        }
        administrator.setFirstName( adminDTO.getFirstName() );
        administrator.setLastName( adminDTO.getLastName() );
        administrator.setDateOfBirth( adminDTO.getDateOfBirth() );
        administrator.setAddress( adminDTO.getAddress() );
        administrator.setPhoneNumber( adminDTO.getPhoneNumber() );
        administrator.setEmail( adminDTO.getEmail() );

        return administrator;
    }

    @Override
    public AdminDTO toAdministratorDto(Administrator administrator, NotificatorMappingContext context) {
        AdminDTO target = context.getMappedInstance( administrator, AdminDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( administrator == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        context.storedMappedInstance( administrator, adminDTO );

        adminDTO.setAdminId( administrator.getAdminId() );
        adminDTO.setFirstName( administrator.getFirstName() );
        adminDTO.setLastName( administrator.getLastName() );
        adminDTO.setDateOfBirth( administrator.getDateOfBirth() );
        adminDTO.setAddress( administrator.getAddress() );
        adminDTO.setPhoneNumber( administrator.getPhoneNumber() );
        adminDTO.setEmail( administrator.getEmail() );

        return adminDTO;
    }
}