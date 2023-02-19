package com.backendattornatus.apigerenciarpessoas.controller;

import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import com.backendattornatus.apigerenciarpessoas.services.PessoaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PessoaController {
    @Autowired
    private PessoaServices pessoaServices;

    @GetMapping("/")
    private ResponseEntity<Object> listarPessoas () {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServices.mostrarPessoas());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> consultarUmaPessoa (@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServices.consultarPessoa(id));
    }

    @PostMapping("/")
    private ResponseEntity<Object> criarPessoa (@RequestBody PessoaModels pessoaModels) {
        PessoaModels novaPessoa = pessoaServices.criarPessoa(pessoaModels);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PessoaModels>  atualizarPessoa(@PathVariable(value = "id") Long id, @Valid @RequestBody PessoaModels pessoaModels) {
        PessoaModels pessoaAtualizada = pessoaServices.editarPessoa(id, pessoaModels);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada);
    }
}
