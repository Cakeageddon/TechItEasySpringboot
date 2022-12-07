package com.voorbeeld.TechItEasy.service;

import com.voorbeeld.TechItEasy.dtos.WallBracketDto;
import com.voorbeeld.TechItEasy.models.WallBracket;
import com.voorbeeld.TechItEasy.repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    @Autowired
    WallBracketRepository wallBracketRepository;

    public static WallBracketDto transferToWallBracketDto(WallBracket wallBracket) {
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.setId(wallBracket.getId());
        wallBracketDto.setSize(wallBracket.getSize());
        wallBracketDto.setAdjustable(wallBracket.isAdjustable());
        wallBracketDto.setName(wallBracket.getName());
        wallBracketDto.setPrice(wallBracket.getPrice());

        return wallBracketDto;
    }
}
