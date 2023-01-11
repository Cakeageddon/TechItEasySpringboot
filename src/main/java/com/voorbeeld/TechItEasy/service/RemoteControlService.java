package com.voorbeeld.TechItEasy.service;

import com.voorbeeld.TechItEasy.dtos.RemoteControlDto;
import com.voorbeeld.TechItEasy.dtos.RemoteControlInputDto;
import com.voorbeeld.TechItEasy.models.RemoteControl;
import com.voorbeeld.TechItEasy.repositories.RemoteControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteControlService {

    @Autowired
    RemoteControlRepository remoteControlRepository;

    public static RemoteControlDto transferToRemoteControlDto(RemoteControl remoteControl) {
        RemoteControlDto remoteControlDto = new RemoteControlDto();
        remoteControlDto.setId(remoteControl.getId());
        remoteControlDto.setCompatibleWith(remoteControl.getCompatibleWith());
        remoteControlDto.setBatteryType(remoteControl.getBatteryType());
        remoteControlDto.setName(remoteControl.getName());
        remoteControlDto.setBrand(remoteControl.getBrand());
        remoteControlDto.setPrice(remoteControl.getPrice());
        remoteControlDto.setOriginalStock(remoteControl.getOriginalStock());

        return remoteControlDto;
    }

    public RemoteControl transferToRemoteControl(RemoteControlInputDto dto) {
        RemoteControl rc = new RemoteControl();
        rc.setCompatibleWith(dto.getCompatibleWith());
        rc.setBatteryType(dto.getBatteryType());
        rc.setName(dto.getName());
        rc.setBrand(dto.getBrand());
        rc.setPrice(dto.getPrice());
        rc.setOriginalStock(dto.getOriginalStock());

        return rc;
    }
}
