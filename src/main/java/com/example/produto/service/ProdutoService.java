package com.example.produto.service;

import com.example.produto.dto.ProdutoDto;
import com.example.produto.model.Produto;
import com.example.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDto toDTO(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        return dto;
    }

    public Produto toEntity(ProdutoDto dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        return produto;
    }

    public List<ProdutoDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDto salvar(ProdutoDto dto) {
        Produto produto = toEntity(dto);
        Produto salvo = repository.save(produto);
        return toDTO(salvo);
    }

    public ProdutoDto buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public ProdutoDto atualizar(Long id, ProdutoDto dto) {
        return repository.findById(id).map(produto -> {
            produto.setNome(dto.getNome());
            produto.setPreco(dto.getPreco());
            return toDTO(repository.save(produto));
        }).orElse(null);
    }
}
