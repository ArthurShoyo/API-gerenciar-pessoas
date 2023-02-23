package com.backendattornatus.apigerenciarpessoas.services;


import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;

import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


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
            PessoaModels pessoa = pessoaExiste.get();
            return pessoa;
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
    }

    @Transactional
    public PessoaModels criarPessoa(PessoaModels pessoaModels) {

        return pessoaRepository.save(pessoaModels);
    }

    @Transactional
    public PessoaModels editarPessoa(Long id, PessoaModels pessoaModels) {
        Optional<PessoaModels> optionalPessoaModels = pessoaRepository.findById(id);
        if (optionalPessoaModels.isPresent()) {
            PessoaModels pessoaExiste = optionalPessoaModels.get();
            if (pessoaModels.getNome() != null) {
                pessoaExiste.setNome(pessoaModels.getNome());
            }
            if (pessoaModels.getDataNasc() != null) {
                pessoaExiste.setDataNasc(pessoaModels.getDataNasc());
            }



            return pessoaRepository.save(pessoaExiste);
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
    }

    public void removerPessoa (Long id) {
        PessoaModels pessoaModels = consultarPessoa(id);
        if (pessoaModels == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        pessoaRepository.deleteById(id);


    }

}
