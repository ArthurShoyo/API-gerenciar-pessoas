package com.backendattornatus.apigerenciarpessoas.repository;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModels, Long> {
    List<EnderecoModels> findAllAddressesByPessoaId(Long id);
}
