package com.example.piBack.Controller;

import com.example.piBack.Service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("characteristic")
public class CharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

}