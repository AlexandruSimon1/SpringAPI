package com.application.utils;

import com.application.dto.AdminDTO;
import com.application.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/administrators")
@Api(value = "administrator", description = "CRUD Operations for Administrators", tags = "ADMINISTRATOR")
public class AdministratorController {
    private final AdministratorService administratorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ALL ADMINISTRATORS", notes = "\n" + "This operation gets all administrators")
    public List<AdminDTO> getAllAdministrators() {
        return administratorService.getAllAdministrators();
    }

    @GetMapping("/{administratorId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ADMINISTRATOR BY ID", notes = "\n" + "This operation get an administrator by id")
    public AdminDTO getAdministratorById(@PathVariable int adminId) {
        return administratorService.getAdministratorById(adminId);
    }

    @DeleteMapping("/{administratorID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "DELETE ADMINISTRATOR BY ID", notes = "\n" + "This operation deletes an administrator by id")
    public AdminDTO deleteAdministratorById(@PathVariable int adminId) {
        return administratorService.deleteAdministratorById(adminId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "CREATE ADMINISTRATOR", notes = "\n" + "This operation creates an administrator")
    public AdminDTO createAdmin(@RequestBody AdminDTO adminDTO) {
        return administratorService.createAdministrator(adminDTO);
    }

    @PutMapping("/{administratorId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "UPDATE ADMINISTRATOR BY ID", notes = "\n" + "This operation update an administrator by id")
    public AdminDTO updateAdministratorById(@PathVariable int adminId, @RequestBody AdminDTO adminDTO) {
        return administratorService.update(adminDTO, adminId);
    }


}
