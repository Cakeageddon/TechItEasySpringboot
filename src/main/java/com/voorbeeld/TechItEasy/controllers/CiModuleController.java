package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.dtos.CiModuleDto;
import com.voorbeeld.TechItEasy.service.CiModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CiModuleController {

    @Autowired
    private CiModuleService ciModuleService;

    public CiModuleController(CiModuleService ciModuleService){this.ciModuleService = ciModuleService;}

    @GetMapping("/ci_modules")
    public ResponseEntity<List<CiModuleDto>> getallCiModules() {
        return ResponseEntity.ok(ciModuleService.getAllCiModules());
    }

    @GetMapping("/ci_modules/{id}")
    public ResponseEntity<CiModuleDto> getCiModule(@PathVariable Long id) {
        CiModuleDto ciModuleDto = ciModuleService.getOneCiModule(id);
        return ResponseEntity.ok(ciModuleDto);
    }
}
