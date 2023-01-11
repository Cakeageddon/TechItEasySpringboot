package com.voorbeeld.TechItEasy.service;

import com.voorbeeld.TechItEasy.dtos.CiModuleDto;
import com.voorbeeld.TechItEasy.dtos.CiModuleInputDto;
import com.voorbeeld.TechItEasy.exceptions.RecordNotFoundException;
import com.voorbeeld.TechItEasy.models.CiModule;
import com.voorbeeld.TechItEasy.repositories.CiModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiModuleService {
    @Autowired
    CiModuleRepository ciModuleRepository;

    public List<CiModuleDto> getAllCiModules() {
        List<CiModule> allCiModules = ciModuleRepository.findAll();
        List<CiModuleDto> allCiModuleDto = new ArrayList<>();
        for (CiModule c : allCiModules) {
            allCiModuleDto.add(transferToCiModuleDto(c));
        } return allCiModuleDto;
    }

    public CiModuleDto getOneCiModule(Long id) {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        if (optionalCiModule.isEmpty()) {
            throw new RecordNotFoundException("No Ci-Module found with id: " + id);
        } else {
            CiModule ciModule = optionalCiModule.get();
            return transferToCiModuleDto(ciModule);
        }
    }

    static CiModuleDto transferToCiModuleDto(CiModule ciModule) {
        CiModuleDto ciDto = new CiModuleDto();
        ciDto.setId(ciModule.getId());
        ciDto.setName(ciModule.getName());
        ciDto.setType(ciModule.getType());
        ciDto.setPrice(ciModule.getPrice());

        return ciDto;
    }

    public CiModule transferToCiModule(CiModuleInputDto ciDto){
        CiModule ciModule = new CiModule();
        ciModule.setId(ciDto.getId());
        ciModule.setName(ciDto.getName());
        ciModule.setType(ciDto.getType());
        ciModule.setPrice(ciDto.getPrice());

        return ciModule;
    }
}
