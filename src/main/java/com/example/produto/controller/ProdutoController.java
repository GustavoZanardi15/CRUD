package com.example.produto.controller;

import com.example.produto.dto.ProdutoDto;
import com.example.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<ProdutoDto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ProdutoDto criar(@RequestBody ProdutoDto dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public ProdutoDto atualizar(@PathVariable Long id, @RequestBody ProdutoDto dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
