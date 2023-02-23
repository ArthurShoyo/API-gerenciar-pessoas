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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaServicesTest {

    @Autowired
    private PessoaServices pessoaServices;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoServices enderecoServices;

    @Autowired
    private EnderecoRepository enderecoRepository;





    @Test
    public void deveraSalvarPessoa() {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoaModels = new PessoaModels("Arthur", LocalDate.parse("2003-12-07"), endereco);
        this.pessoaServices.criarPessoa(pessoaModels);

        PessoaModels pessoaModels1 = this.pessoaServices.consultarPessoa(pessoaModels.getId());

        int size = this.pessoaRepository.findAll().size();
        int sizePessoas = this.pessoaServices.mostrarPessoas().size();
        Long id = pessoaModels1.getId();
        String name = pessoaModels1.getNome();
        LocalDate dataNasc = pessoaModels1.getDataNasc();



        EnderecoModels enderecoModels = this.enderecoRepository.findById(1L).get();


        Long idEndereco = enderecoModels.getId();
        String logradouro = enderecoModels.getLogradouro();
        String cep = enderecoModels.getCep();
        String numero = enderecoModels.getNumero();
        String cidade = enderecoModels.getCidade();
        boolean principal = enderecoModels.isPrincipal();
        Long pessoaId = enderecoModels.getPessoa().getId();


        assertEquals(sizePessoas, size);
        assertEquals("Arthur", name);
        assertEquals(LocalDate.parse("2003-12-07"), dataNasc);

        assertEquals(1, idEndereco);
        assertEquals("Rua A", logradouro);
        assertEquals("138838", cep);
        assertEquals("200", numero);
        assertEquals("Campinas", cidade);
        assertEquals(true, principal);
        assertEquals(id, pessoaId);


    }



    @Test
    public void deveraLancaUmaExceptionQuandoEditarEnderecoInexistente() {
        PessoaModels pessoaModels = new PessoaModels();
        Assertions.assertThrows(ResponseStatusException.class, () -> this.pessoaServices.editarPessoa(27L, pessoaModels));
    }

    @Test
    public void deveraEditarPessoa() {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoaModels = new PessoaModels("Arthur", LocalDate.parse("2003-12-07"), endereco);
        this.pessoaServices.criarPessoa(pessoaModels);

        //informação que vao ser editadas
        PessoaModels pessoaEditar = new PessoaModels("Arthur Araujo", LocalDate.parse("2004-12-12"), null);
        PessoaModels pessoaModels1 = pessoaServices.consultarPessoa(pessoaModels.getId());
        this.pessoaServices.editarPessoa(pessoaModels1.getId(), pessoaEditar);

        PessoaModels pessoaAtualizada = this.pessoaServices.consultarPessoa(pessoaModels.getId());


        String nome = pessoaAtualizada.getNome();
        LocalDate dataNasc = pessoaAtualizada.getDataNasc();

        assertEquals("Arthur Araujo", nome);
        assertEquals(LocalDate.parse("2004-12-12"), dataNasc);


    }

    @Test
    public void deveraLancarUmaExceptionQuandoBuscarPessoaInexistente() {
        assertThrows(ResponseStatusException.class, () -> this.pessoaServices.consultarPessoa(133L));
    }

    @Test
    public void deveMostrarUmaPessoa () {
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoaModels = new PessoaModels("Arthur", LocalDate.parse("2003-12-07"), endereco);
        this.pessoaServices.criarPessoa(pessoaModels);

        Long pessoa =  this.pessoaServices.consultarPessoa(pessoaModels.getId()).getId();

        assertEquals(pessoa, pessoa);
    }

    @Test
    public void deveraExcluirPessoaExistenteERetornarOk() {
        // Criando algumas pessoas para testar
        List<EnderecoModels> endereco = new ArrayList<>();
        endereco.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        List<EnderecoModels> endereco2 = new ArrayList<>();
        endereco2.add(new EnderecoModels("Rua A", "138838", "200", "Campinas", true));
        PessoaModels pessoa1 = pessoaServices.criarPessoa(new PessoaModels("João", LocalDate.parse("2003-12-07")));
        PessoaModels pessoa2 = pessoaServices.criarPessoa(new PessoaModels("Maria", LocalDate.parse("2003-12-07")));

        // Removendo a primeira pessoa
        pessoaServices.removerPessoa(pessoa1.getId());


        // Verificando se a lista de pessoas foi atualizada corretamente
        List<PessoaModels> pessoas = pessoaServices.mostrarPessoas();
        assertEquals(1, pessoas.size());
        assertEquals(pessoa2.getNome(), pessoas.get(0).getNome());
        assertEquals(pessoa2.getDataNasc(), pessoas.get(0).getDataNasc());
    }

    @Test
    public void deveLancarUmaExceptionQuandoRemoverUmaPessoaInexistente() {

        assertThrows(ResponseStatusException.class, () -> this.pessoaServices.removerPessoa(341L));
    }
}
