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


### Perguntas e Respostas

Qualidade de código

1.	Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

- Certifico que a funcionalidade implementada atenda aos requisitos do usuário e do negócios, testes pata garantir a nova funcionalidade, documentação para ajudar a garantir que os usuários finais possam entender e usar a nova funcionalidade, código limpo para facilitar futuras manutenções, padrões de codificação e revisões do código. É importante seguir todos esses critérios para garantir a qualidade do software


2.	Em qual etapa da implementação você considera a qualidade de software?

- Considero em todas as etapas, desde o planejamento até a manutenção do software, para garantir um produto final de alta qualidade e satisfazer os usuários finais
