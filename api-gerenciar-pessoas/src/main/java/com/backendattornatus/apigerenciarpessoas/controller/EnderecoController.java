package com.backendattornatus.apigerenciarpessoas.controller;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.EnderecoRepository;
import com.backendattornatus.apigerenciarpessoas.services.EnderecoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EnderecoController {
    @Autowired

    private EnderecoServices enderecoServices;

    @GetMapping("/endereco")
    private ResponseEntity<Object> getAllEndereco() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoServices.getAllEndereco());
    }

    @PostMapping("/endereco/{id}")
    private  ResponseEntity<Object> criarEndereco(@PathVariable(name = "id") Long id, @RequestBody EnderecoModels enderecoModels) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoServices.criarEndereco(id, enderecoModels));
    }
}
