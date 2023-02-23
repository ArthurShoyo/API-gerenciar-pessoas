package com.backendattornatus.apigerenciarpessoas.repository;

import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PessoaRepository  extends JpaRepository<PessoaModels, Long> {
        @Query("SELECT p FROM PessoaModels p JOIN FETCH p.enderecos WHERE p.id = :id")
        Optional<PessoaModels> findByWithEnderecos(@Param("id") Long id);
}
