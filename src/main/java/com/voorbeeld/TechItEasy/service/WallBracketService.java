package com.voorbeeld.TechItEasy.service;

import com.voorbeeld.TechItEasy.repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    @Autowired
    WallBracketRepository wallBracketRepository;
}
