package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.service.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WallBracketController {

    @Autowired
    private WallBracketService wallBracketService;


}
