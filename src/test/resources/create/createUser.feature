# encoding: utf-8
# language: pt

Funcionalidade: Criar um usuario

  Cria um usuario para obter um token de acesso

  Esquema do Cenário: Criar um usuario com sucesso

    Dado que eu quero criar um novo usuario
    E insiro o nome "<name>"
    E insiro o job "<job>"
    Quando eu envio minhas infomacoes
    Entao eu recebo uma resposta
    E valido o status code "<statusCode>"
    E recebo o nome "<name>"
    E recebo o job "<job>"
    E recebo um id
    E recebo uma data de criacao


    Exemplos:
      | name            | job               | statusCode |
      | Leandro Martins | Analista QA       | 201        |
      | Juscelino       | Analista Negocios | 201        |


  Esquema do Cenário: Criar um usuario sem nome e sem job

    Dado que eu quero criar um novo usuario
    E nao insiro o nome
    E nao insiro o job
    Quando eu envio minhas infomacoes
    Entao eu recebo uma resposta
    E valido o status code "<statusCode>"
    E recebo o nome nulo
    E recebo o job nulo
    E recebo um id
    E recebo uma data de criacao


    Exemplos:
      | statusCode |
      | 201        |