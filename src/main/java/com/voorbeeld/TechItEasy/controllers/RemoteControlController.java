package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.service.RemoteControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteControlController {

    @Autowired
    private RemoteControlService remoteControlService;

}
