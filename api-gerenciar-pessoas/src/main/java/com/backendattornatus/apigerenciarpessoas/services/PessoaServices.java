package com.backendattornatus.apigerenciarpessoas.services;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.EnderecoRepository;
import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServices {
    @Autowired
    private PessoaRepository pessoaRepository;



    public List<PessoaModels> mostrarPessoas() {
        return pessoaRepository.findAll();
    }

    public PessoaModels consultarPessoa(Long id) {
        Optional<PessoaModels> pessoaExiste= pessoaRepository.findById(id);
        if (pessoaExiste.isPresent()) {
            return pessoaExiste.get();
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public PessoaModels criarPessoa(PessoaModels pessoaModels) {
        return pessoaRepository.save(pessoaModels);
    }

    public PessoaModels editarPessoa(Long id, PessoaModels pessoaModels) {
        Optional<PessoaModels> optionalPessoaModels = pessoaRepository.findById(id);
        if (optionalPessoaModels.isPresent()) {
            PessoaModels pessoaExiste = optionalPessoaModels.get();
            pessoaExiste.setNome(pessoaModels.getNome());
            pessoaExiste.setDataNasc(pessoaModels.getDataNasc());
            pessoaExiste.setEnderecos(pessoaModels.getEnderecos());
            return pessoaRepository.save(pessoaExiste);
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
