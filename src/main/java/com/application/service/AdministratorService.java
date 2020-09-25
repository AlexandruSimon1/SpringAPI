package com.application.service;

import com.application.dto.AdminDTO;
import com.application.model.Administrator;

import java.util.List;

public interface AdministratorService {
    List<AdminDTO> getAllAdministrators();

    AdminDTO getAdministratorById(Integer adminId);

    AdminDTO deleteAdministratorById(Integer adminId);

    AdminDTO createAdministrator(AdminDTO adminDTO);

    AdminDTO update(AdminDTO adminDTO, Integer adminId);

}
