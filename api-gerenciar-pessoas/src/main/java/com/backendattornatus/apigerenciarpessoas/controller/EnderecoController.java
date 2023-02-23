package com.backendattornatus.apigerenciarpessoas.controller;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.services.EnderecoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class EnderecoController {
    @Autowired

    private EnderecoServices enderecoServices;

    @GetMapping("/endereco")
    private ResponseEntity<Object> pegarTodosEndereco() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoServices.mostrarEnderecos());
    }

    @GetMapping("/endereco/{id}")
    private ResponseEntity<Object> pegarEnderecosDaPessoa(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoServices.mostrarEnderecosDaPessoa(id));
    }

    @PostMapping("/endereco/{id}")
    private  ResponseEntity<Object> criarEndereco(@PathVariable(name = "id") Long id, @RequestBody EnderecoModels enderecoModels) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoServices.criarEndereco(id, enderecoModels));
    }

    @PutMapping("/{id}/endereco/{idEndereco}")
    private ResponseEntity<Object> atualizarEndereco(@PathVariable(value = "id") Long id, @PathVariable(value = "idEndereco") Long idEndereco, @Valid @RequestBody EnderecoModels enderecoModels) {
        EnderecoModels enderecoAtualizado = enderecoServices.atualizarEndereco(id, idEndereco, enderecoModels);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoAtualizado);
    }

    @DeleteMapping("/{id}/endereco/{idEndereco}")
    private ResponseEntity<Object> deletarEndereco(@PathVariable (value = "id") Long id, @PathVariable(value = "idEndereco") Long idEndereco) {
        enderecoServices.deletarEndereco(id, idEndereco);
        return ResponseEntity.status(HttpStatus.OK).body("Endereco deletado");
    }
}
