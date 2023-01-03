package com.voorbeeld.TechItEasy.service;

import com.voorbeeld.TechItEasy.dtos.TelevisionDto;
import com.voorbeeld.TechItEasy.dtos.TelevisionInputDto;
import com.voorbeeld.TechItEasy.dtos.WallBracketDto;
import com.voorbeeld.TechItEasy.exceptions.RecordNotFoundException;
import com.voorbeeld.TechItEasy.models.CiModule;
import com.voorbeeld.TechItEasy.models.RemoteControl;
import com.voorbeeld.TechItEasy.models.Television;
import com.voorbeeld.TechItEasy.models.WallBracket;
import com.voorbeeld.TechItEasy.repositories.CiModuleRepository;
import com.voorbeeld.TechItEasy.repositories.RemoteControlRepository;
import com.voorbeeld.TechItEasy.repositories.TelevisionRepository;
import com.voorbeeld.TechItEasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.voorbeeld.TechItEasy.service.CiModuleService.transferToCiModuleDto;
import static com.voorbeeld.TechItEasy.service.RemoteControlService.transferToRemoteControlDto;
import static com.voorbeeld.TechItEasy.service.WallBracketService.transferToWallBracketDto;

@Service
public class TelevisionService {

    TelevisionRepository televisionRepository;
    RemoteControlRepository remoteControlRepository;
    CiModuleRepository ciModuleRepository;
    WallBracketRepository wallBracketRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControlRepository remoteControlRepository, CiModuleRepository ciModuleRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControlRepository = remoteControlRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    public void assignRemoteControllerToTelevision(Long televisionId, Long remoteControlId) {

        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<RemoteControl> optionalRemoteControl = remoteControlRepository.findById(remoteControlId);
        if (optionalTelevision.isEmpty() && optionalRemoteControl.isEmpty()) {
            throw new RecordNotFoundException("No television/remote control combination found");
        } else {
            Television television = optionalTelevision.get();
            RemoteControl remoteControl = optionalRemoteControl.get();
            television.setRemoteControl(remoteControl);
            televisionRepository.save(television);
        }
    }

    public void assignCiModuleToTelevision(Long televisionId, Long ciModuleId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(ciModuleId);
        if (optionalTelevision.isEmpty() || optionalCiModule.isEmpty()) {
            throw new RecordNotFoundException("No television/ci module combination found");
        } else {
            Television television = optionalTelevision.get();
            CiModule ciModule = optionalCiModule.get();
            television.setCiModule(ciModule);
            televisionRepository.save(television);
        }
    }

    @Transactional
    public void assignWallBracketToTelevision(Long televisionId, Long wallBracketId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(wallBracketId);
        if (optionalTelevision.isEmpty() && optionalWallBracket.isEmpty()) {
            throw new RecordNotFoundException("No Television/WallBracket combination found");
        } else {
            Television television = optionalTelevision.get();
            WallBracket wallBracket = optionalWallBracket.get();

            Set<WallBracket> wallBrackets = television.getWallBrackets();
            wallBrackets.add(wallBracket);
            television.setWallBrackets(wallBrackets);
            televisionRepository.save(television);

            Set<Television> televisions = wallBracket.getTelevisions();
            televisions.add(television);
            wallBracket.setTelevisions(televisions);
            wallBracketRepository.save(wallBracket);
        }
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> allTelevisions = televisionRepository.findAll();
        List<TelevisionDto> allTelevisionDto = new ArrayList<>();
        for (Television t : allTelevisions) {
            allTelevisionDto.add(transferToTelevisionDto(t));
        }
        return allTelevisionDto;
    }

    public TelevisionDto getOneTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television television1 = optionalTelevision.get();
            return transferToTelevisionDto(television1);
        }
    }

    public TelevisionDto createTelevision(TelevisionInputDto television) {
        Television televisionSavedLocal = televisionRepository.save(transferToTelevision(television));

        return transferToTelevisionDto(televisionSavedLocal);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto t) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television updateTelevision = optionalTelevision.get();
            if (t.getId() != null) {
                updateTelevision.setId(t.getId());
            }
            if (t.getType() != null) {
                updateTelevision.setType(t.getType());
            }
            if (t.getBrand() != null) {
                updateTelevision.setBrand(t.getBrand());
            }
            if (t.getName() != null) {
                updateTelevision.setName(t.getName());
            }
            if (t.getPrice() != null) {
                updateTelevision.setPrice(t.getPrice());
            }
            if (t.getAvailableSize() != null) {
                updateTelevision.setAvailableSize(t.getAvailableSize());
            }
            if (t.getRefreshRate() != null) {
                updateTelevision.setRefreshRate(t.getRefreshRate());
            }
            if (t.getScreenType() != null) {
                updateTelevision.setScreenType(t.getScreenType());
            }
            if (t.getScreenQuality() != null) {
                updateTelevision.setScreenQuality(t.getScreenQuality());
            }
            if (t.getSmartTv() != null) {
                updateTelevision.setSmartTv(t.getSmartTv());
            }
            if (t.getWifi() != null) {
                updateTelevision.setWifi(t.getWifi());
            }
            if (t.getVoiceControl() != null) {
                updateTelevision.setVoiceControl(t.getVoiceControl());
            }
            if (t.getHdr() != null) {
                updateTelevision.setHdr(t.getHdr());
            }
            if (t.getBluetooth() != null) {
                updateTelevision.setBluetooth(t.getBluetooth());
            }
            if (t.getAmbiLight() != null) {
                updateTelevision.setAmbiLight(t.getAmbiLight());
            }
            if (t.getOriginalStock() != null) {
                updateTelevision.setOriginalStock(t.getOriginalStock());
            }
            if (t.getSold() != null) {
                updateTelevision.setSold(t.getSold());
            }
            televisionRepository.save(updateTelevision);
            return transferToTelevisionDto(updateTelevision);
        }
    }

    public void deleteTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            televisionRepository.deleteById(id);
        }
    }

    private TelevisionDto transferToTelevisionDto(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());
        if(television.getRemoteControl() != null) {dto.setRemoteControlDto(transferToRemoteControlDto(television.getRemoteControl()));}
        if(television.getCiModule() != null) {dto.setCiModuleDto(transferToCiModuleDto(television.getCiModule()));}
//        if(television.getWallBrackets() != null) {dto.setWallBracketDto(transferToWallBracketDto(television.getWallBrackets()));}
        return dto;
    }

    public Television transferToTelevision(TelevisionInputDto dto) {
        Television tv = new Television();
        tv.setType(dto.getType());
        tv.setBrand(dto.getBrand());
        tv.setName(dto.getName());
        tv.setPrice(dto.getPrice());
        tv.setAvailableSize(dto.getAvailableSize());
        tv.setRefreshRate(dto.getRefreshRate());
        tv.setScreenType(dto.getScreenType());
        tv.setScreenQuality(dto.getScreenQuality());
        tv.setSmartTv(dto.getSmartTv());
        tv.setWifi(dto.getWifi());
        tv.setVoiceControl(dto.getVoiceControl());
        tv.setHdr(dto.getHdr());
        tv.setBluetooth(dto.getBluetooth());
        tv.setAmbiLight(dto.getAmbiLight());
        tv.setOriginalStock(dto.getOriginalStock());
        tv.setSold(dto.getSold());

        return tv;
    }
}
