package com.danielfreitassc.loja.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutosDTO(@NotBlank String nome, @NotNull BigDecimal valor) {
    
}
