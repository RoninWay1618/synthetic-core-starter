package com.wy.synthetic.controller;

import com.wy.synthetic.dto.CommandDto;
import com.wy.synthetic.service.CommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commands")
public class CommandController {

    @Autowired
    private CommandService service;

    @PostMapping
    public void receive(@Valid @RequestBody CommandDto cmd) {
        service.submit(cmd);
    }
}
