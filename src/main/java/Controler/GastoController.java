package com.gastos.controller;

import com.gastos.model.Gasto;
import com.gastos.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public Gasto criarGasto(@RequestBody Gasto gasto) {

        String descricao = gasto.getDescricao();
        String url = "http://localhost:5000/classificar?descricao=" +
                descricao.replace(" ", "%20");

        // requisição
        String categoria = restTemplate.getForObject(url, String.class);

        gasto.setCategoria(categoria);
        return repository.save(gasto);
    }

    @GetMapping
    public java.util.List<Gasto> listarGastos() {
        return repository.findAll();
    }
}
