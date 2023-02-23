# API-gerenciar-pessoas


## Rotas GET
- CONSULTAR UMA PESSOA - "/{idPessoa}"
- LISTAR PESSOAS - "/"
- LISTAR ENDEREÇOS DA PESSO - "endereco/{idPessoa}"




## Rotas POST
- CRIAR UMA PESSOA - "/"
- CRIAR ENDEREÇO PARA PESSOA - "/endereco/{idPessoa}"


## Rotas PUT
- EDITAR UMA PESSOA - "/{idPessoa}"
- EDITAR UM ENDEREÇO DA PESSOA - "{idPessoa}/endereco/{idEndereco}"

## Rotas DELETE
- DELETAR UMA PESSOA - "/"
- DELETAR UM ENDEREÇO - "{idPessoa}/endereco/{idEndereco}"


### Modelo


```
{
    "id": 1,
    "nome": "Nome Aqui",
    "dataNasc": "12/12/2000",
    "enderecos": [
        {
            "id": 1,
            "logradouro": "Rua A",
            "cep": "132213",
            "numero": "2",
            "cidade": "Campinas",
            "principal": true
        }
    ]
}
```
- Os Id são gerados automaticamente 
- Permitido apenas 1 endereço principal, o que for determinado mais recente como principal que ira carregar "principal": true, 
caso tenha outro endereco mais antigo que carregava, o mais antigo ira se tornar "principal": false
- Todas as respostas da API são em JSON


### Tecnologias usadas

- Java
- Spring Boot
- API REST
- Banco de dados H2

### Metodologias usadas

- MVC
- Código limpo
- Qualidade de código
