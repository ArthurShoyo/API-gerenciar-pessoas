package com.backendattornatus.apigerenciarpessoas;

import com.backendattornatus.apigerenciarpessoas.models.EnderecoModels;
import com.backendattornatus.apigerenciarpessoas.models.PessoaModels;
import com.backendattornatus.apigerenciarpessoas.repository.EnderecoRepository;
import com.backendattornatus.apigerenciarpessoas.repository.PessoaRepository;
import com.backendattornatus.apigerenciarpessoas.services.EnderecoServices;
import com.backendattornatus.apigerenciarpessoas.services.PessoaServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EnderecoServicesTest {

    @Autowired
    EnderecoServices enderecoServices;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaServices pessoaServices;

    @Autowired
    PessoaRepository pessoaRepository;

    @Test
    public void deveraLancarUmaExceptionQuandoBuscarEnderecoInexistente() {
        Assertions.assertThrows(ResponseStatusException.class, () -> this.enderecoServices.mostrarEnderecosDaPessoa(2L));
    }

    @Test
    public void deveMostrarEnderecosDaPessoa () {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels(1L,"Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoaModels = new PessoaModels(1L, "Arthur", LocalDate.parse("2003-12-07"), endereco);
        this.pessoaServices.criarPessoa(pessoaModels);


        int mostrar =  enderecoServices.mostrarEnderecosDaPessoa(pessoaModels.getId()).size();

        assertEquals(1, mostrar);
    }



    @Test
    public void deverarSalvarEnderecoNaPessoa() {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoaModels = new PessoaModels("Arthur", LocalDate.parse("2003-12-07"), endereco);

        this.pessoaServices.criarPessoa(pessoaModels);


        EnderecoModels enderecoModels = new EnderecoModels("Rua 123", "123456", "2", "campinas", true);

        this.enderecoServices.criarEndereco(pessoaModels.getId(), enderecoModels);

        int enderecoSalvo1 = this.enderecoRepository.findAllAddressesByPessoaId(pessoaModels.getId()).size();
        List<EnderecoModels> enderecoSalvo = this.enderecoServices.mostrarEnderecosDaPessoa(pessoaModels.getId());

        int index = enderecoSalvo1 - 1;

        String logradouro = enderecoSalvo.get(index).getLogradouro();
        String cep = enderecoSalvo.get(index).getCep();
        String numero = enderecoSalvo.get(index).getNumero();
        String cidade = enderecoSalvo.get(index).getCidade();
        boolean principal = enderecoSalvo.get(index).isPrincipal();
        Long pessoaId = enderecoSalvo.get(index).getPessoa().getId();

        Assertions.assertEquals("Rua 123", logradouro);
        Assertions.assertEquals("123456", cep);
        Assertions.assertEquals("2", numero );
        Assertions.assertEquals("campinas", cidade);
        Assertions.assertEquals(enderecoModels.isPrincipal(), principal);
        Assertions.assertEquals(pessoaModels.getId(), pessoaId);
    }


    @Test
    public void deveraLancaUmaExceptionQuandoEditarEnderecoInexistente() {
        EnderecoModels enderecoModels = new EnderecoModels();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.enderecoServices.atualizarEndereco(100L, 200L, enderecoModels));
    }

    @Test
    public void deveraEditarEndereco() {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));

        PessoaModels pessoaModels = new PessoaModels("Arthur", LocalDate.parse("2003-12-07"), endereco);

        this.pessoaServices.criarPessoa(pessoaModels);


        //informação que vao ser editadas
        EnderecoModels enderecoModels = new EnderecoModels("Rua 123", "123456", "2", "pedreira", false);


        EnderecoModels enderecoAtualizado =    this.enderecoServices.atualizarEndereco( pessoaModels.getId(), endereco.get(0).getId(), enderecoModels);

        String logradouro = enderecoAtualizado.getLogradouro();
        String cep = enderecoAtualizado.getCep();
        String numero = enderecoAtualizado.getNumero();
        String cidade = enderecoAtualizado.getCidade();
        boolean principal = enderecoAtualizado.isPrincipal();




        assertEquals("Rua 123", logradouro);
        assertEquals("123456", cep);
        assertEquals("2", numero);
        assertEquals("pedreira", cidade);
        assertEquals(false, principal);


    }

    @Test
    public void deveLancarUmaExceptionQuandoRemoverUmEnderecoInexistente() {
        Assertions.assertThrows(ResponseStatusException.class, () -> this.enderecoServices.deletarEndereco(5L, 10L));
    }

    @Test void deveExcluirEnderecoSelecionado() {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels(1L, "Rua A", "138838", "200", "Campinas", true));
        endereco.add(new EnderecoModels(2L, "Rua 123", "123456", "2", "campinas", false));
        endereco.add(new EnderecoModels(3L, "Rua 153", "15456", "3", "campinas", false));
        PessoaModels pessoaModels = new PessoaModels(1L, "Arthur", LocalDate.parse("2003-12-07"), endereco);

        this.pessoaServices.criarPessoa(pessoaModels);

        PessoaModels pessoa = this.pessoaRepository.findById(1L).get();









        this.enderecoServices.deletarEndereco(1L, 1L);


        List enderecoModels = this.enderecoRepository.findAll();


        Assertions.assertEquals(2, enderecoModels.size());
    }
}
