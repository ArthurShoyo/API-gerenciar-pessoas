package com.backendattornatus.apigerenciarpessoas.services;

import com.backendattornatus.apigerenciarpessoas.dtos.EnderecoDto;
import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.EnderecoRepository;
import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServices {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<EnderecoModels> getAllEndereco() {
        return enderecoRepository.findAll();
    }


    @Transactional
    public PessoaModels criarEndereco(Long id, EnderecoModels novoEnderoco) {
        Optional<PessoaModels> pessoaModelsOptional = pessoaRepository.findByWithEnderecos(id);
        if (pessoaModelsOptional.isPresent()) {

            PessoaModels pessoa = pessoaModelsOptional.get();
            pessoa.getEnderecos().add(novoEnderoco);

            novoEnderoco.setPessoa(pessoa);

            EnderecoModels endereco = new EnderecoModels();

            endereco.setLogradouro(novoEnderoco.getLogradouro());
            endereco.setCep(novoEnderoco.getCep());
            endereco.setNumero(novoEnderoco.getNumero());
            endereco.setCidade(novoEnderoco.getCidade());
            endereco.setPessoa(novoEnderoco.getPessoa());




            enderecoRepository.save(novoEnderoco);



            return pessoaRepository.save(pessoa);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }



    public EnderecoModels atualizarEndereco(Long id,EnderecoModels enderecoModels) {
        Optional<EnderecoModels> optionalEnderecoModels = enderecoRepository.findById(id);
        if (optionalEnderecoModels.isPresent()) {
            EnderecoModels enderecoExiste = optionalEnderecoModels.get();
            enderecoExiste.setLogradouro(enderecoModels.getLogradouro());
            enderecoExiste.setCep(enderecoModels.getCep());
            enderecoExiste.setNumero(enderecoModels.getNumero());
            enderecoExiste.setCidade(enderecoModels.getCidade());
            enderecoExiste.setCidade(enderecoModels.getCidade());
            return enderecoRepository.save(enderecoExiste);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
