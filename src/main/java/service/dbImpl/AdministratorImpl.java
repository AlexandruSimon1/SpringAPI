package service.dbImpl;


import dto.AdminDTO;
import exceptions.ApplicationException;
import exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import mapper.AdministratorMapper;
import mapper.NotificatorMappingContext;
import model.Administrator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AdministratorRepository;
import service.AdministratorService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdministratorImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;


    @Override
    public List<AdminDTO> getAllAdministrators() {
        return administratorRepository.findAll().stream().filter(Objects::isNull).
                map(administrator -> AdministratorMapper.INSTANCE.
                        toAdministratorDto(administrator, new NotificatorMappingContext())).collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdministratorById(int adminId) {
        final Administrator getAdministrator = administratorRepository.findById(adminId).
                orElseThrow(() -> new ApplicationException(ExceptionType.ADMINISTRATOR_NOT_FOUND));
        return AdministratorMapper.INSTANCE.toAdministratorDto(getAdministrator, new NotificatorMappingContext());
    }

    @Override
    public AdminDTO deleteAdministratorById(int adminId) {
        final Administrator deleteAdministrator = administratorRepository.findById(adminId).
                orElseThrow(() -> new ApplicationException(ExceptionType.ADMINISTRATOR_NOT_FOUND));
        return AdministratorMapper.INSTANCE.toAdministratorDto(deleteAdministrator, new NotificatorMappingContext());
    }

    @Override
    public AdminDTO createAdministrator(AdminDTO adminDTO) {
        final Administrator createAdministrator = AdministratorMapper.INSTANCE.fromAdministratorDto(adminDTO, new NotificatorMappingContext());
        final Administrator saveAdministrator = administratorRepository.save(createAdministrator);
        return AdministratorMapper.INSTANCE.toAdministratorDto(saveAdministrator, new NotificatorMappingContext());
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO, int adminId) {
        final Administrator updateAdministrator = administratorRepository.findById(adminId).
                orElseThrow(() -> new ApplicationException(ExceptionType.ADMINISTRATOR_NOT_FOUND));
        updateAdministrator.setFirstName(adminDTO.getFirstName());
        updateAdministrator.setLastName(adminDTO.getLastName());
        updateAdministrator.setDateOfBirth(adminDTO.getDateOfBirth());
        updateAdministrator.setAddress(adminDTO.getAddress());
        updateAdministrator.setPhoneNumber(adminDTO.getPhoneNumber());
        updateAdministrator.setEmail(adminDTO.getEmail());
        administratorRepository.save(updateAdministrator);
        return AdministratorMapper.INSTANCE.toAdministratorDto(updateAdministrator, new NotificatorMappingContext());
    }

}
