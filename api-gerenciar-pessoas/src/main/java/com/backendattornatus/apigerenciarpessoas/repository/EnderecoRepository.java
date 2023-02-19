package com.backendattornatus.apigerenciarpessoas.repository;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoModels, Long> {
}
