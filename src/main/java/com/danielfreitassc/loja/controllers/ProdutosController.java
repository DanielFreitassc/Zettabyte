package com.danielfreitassc.loja.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.loja.dtos.ProdutosDTO;
import com.danielfreitassc.loja.models.ProdutosEntity;
import com.danielfreitassc.loja.repositories.ProdutosRepository;

import jakarta.validation.Valid;

@RestController
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;

    @PostMapping("/produtos")
    public ResponseEntity<ProdutosEntity> salvarProdutosEntity(@RequestBody @Valid ProdutosDTO produtosDTO){
        var produtosEntity = new ProdutosEntity();
        BeanUtils.copyProperties(produtosDTO, produtosEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtosEntity));
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutosEntity>> buscarProdutosEntity(){
        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.findAll());
    }
    
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> buscarUmProduto(@PathVariable(value = "id") UUID id){
        Optional<ProdutosEntity> produtoUm = produtosRepository.findById(id);
        if (produtoUm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoUm.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoUm.get());
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> atualizarProdutos(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutosDTO produtosDTO){
        Optional<ProdutosEntity> produtoUm = produtosRepository.findById(id);
        if(produtoUm.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var produtosEntity = produtoUm.get();
        BeanUtils.copyProperties(produtosDTO, produtosEntity);
        return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtosEntity));
    }
    
}
