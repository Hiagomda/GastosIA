package com.gastos.Controler;

import com.gastos.service.IAGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gastos")
public class IAController {

    @Autowired
    private IAGastoService iaGastoService;

    @GetMapping("/classificar")
    public String classificar(@RequestParam String descricao) {
        return iaGastoService.classificarGasto(descricao);
    }
}