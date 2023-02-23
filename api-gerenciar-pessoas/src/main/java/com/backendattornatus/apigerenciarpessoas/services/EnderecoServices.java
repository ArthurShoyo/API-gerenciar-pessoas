package com.backendattornatus.apigerenciarpessoas.services;


import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.EnderecoRepository;
import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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



    public List<EnderecoModels> mostrarEnderecosDaPessoa(Long id) {
        Optional<PessoaModels> pessoaModelsOptional = pessoaRepository.findByWithEnderecos(id);
        if (pessoaModelsOptional.isPresent()) {
            return enderecoRepository.findAllAddressesByPessoaId(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario nao existe");
    }


    @Transactional
    public PessoaModels criarEndereco(Long id, EnderecoModels novoEndereco) {
        Optional<PessoaModels> pessoaModelsOptional = pessoaRepository.findByWithEnderecos(id);
        if (pessoaModelsOptional.isPresent()) {
            PessoaModels pessoaExiste = pessoaModelsOptional.get();


            EnderecoModels endereco = new EnderecoModels();


            for (EnderecoModels enderecoModels: pessoaExiste.getEnderecos()) {
                if (enderecoModels.isPrincipal()) {
                    if (novoEndereco.isPrincipal()) {
                        enderecoModels.setPrincipal(false);
                        endereco.setPrincipal(novoEndereco.isPrincipal());
                    } else {
                        endereco.setPrincipal(false);
                    }


                    break;
                } else {
                    endereco.setPrincipal(novoEndereco.isPrincipal());
                }


            }

            endereco.setLogradouro(novoEndereco.getLogradouro());


            endereco.setCep(novoEndereco.getCep());


            endereco.setCidade(novoEndereco.getCidade());


            endereco.setNumero(novoEndereco.getNumero());




            pessoaExiste.getEnderecos().add(endereco);


            return pessoaRepository.save(pessoaExiste);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @Transactional
    public EnderecoModels atualizarEndereco(Long id, Long idEndereco, EnderecoModels enderecoModels) {
        Optional<EnderecoModels> optionalEnderecoModels = enderecoRepository.findById(idEndereco);
        Optional<PessoaModels> optionalPessoaModels  = pessoaRepository.findById(id);
        if (!optionalEnderecoModels.isPresent() && !optionalPessoaModels.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        EnderecoModels enderecoExiste = optionalEnderecoModels.get();
        PessoaModels pessoaExiste = optionalPessoaModels.get();
        for (EnderecoModels endereco: pessoaExiste.getEnderecos()) {
            if (enderecoModels.isPrincipal()) {
                endereco.setPrincipal(false);
                enderecoExiste.setPrincipal(enderecoModels.isPrincipal());
            } else {
                enderecoExiste.setPrincipal(enderecoModels.isPrincipal());
            }
        }
        if (enderecoModels.getLogradouro() != null) {
            enderecoExiste.setLogradouro(enderecoModels.getLogradouro());
        }
        if (enderecoModels.getCep() != null) {
            enderecoExiste.setCep(enderecoModels.getCep());
        }
        if (enderecoModels.getNumero() != null) {
            enderecoExiste.setNumero(enderecoModels.getNumero());
        }
        if (enderecoModels.getCidade() != null) {
            enderecoExiste.setCidade(enderecoModels.getCidade());
        }
        return enderecoRepository.save(enderecoExiste);

    }

    public void deletarEndereco(Long idPessoa, Long idEndereco) {
        Optional<PessoaModels> pessoaModelsOptional = pessoaRepository.findById(idPessoa);
        Optional<EnderecoModels> enderecoModelsOptional = enderecoRepository.findById(idEndereco);
        if (!pessoaModelsOptional.isPresent() && !enderecoModelsOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        enderecoRepository.deleteById(idEndereco);
    }
}
