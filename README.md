# Projeto exams manager

## Sobre esse projeto

Esta é uma Applicação Web + Webservice REST simples que tem o objetivo de gerenciar a realização de exames pelos funcionários.

São utilizadas as tecnologias:

- Java 20
- Maven 3.9.4
- Spring Boot
- Spring Web
- Thymeleaf
- Bootstrap 5.1.3
- Bean Validation
- Spring Data JPA
- Flyway, 
- H2, o BD relacional em memória

Abra o projeto na IDE da sua preferência.

Execute os testes automatizados.

Suba a aplicação e explore a API com uma ferramenta como [cURL](https://curl.se/), [Insomnia](https://insomnia.rest/), [Postman](https://www.postman.com/).

Alguns exemplos de chamadas usando cURL estão em [exemplos-curl.md](exemplos-curl.md).

### Interface gráfica?

Os seguintes endpoints estão implementados

- `GET /exames` obtém a lista de exames
- `GET /employees` obtém a lista de employees
- `GET /exames-employees` obtém a lista de exames realizados pelos funcionários

Nas respectivas páginas, é possivel fazer outras requisições através dos botões

### O que está implementado?

1. Criar / Concluir o CRUD de exames implementando a alteração e
   deleção de registros, atualmente ele contém somente a inclusão e a
   consulta (caso você use o nosso projeto uma parte do CRUD já estará
   feita)
   a. campos obrigatórios: código e nome
2. Criar o CRUD de funcionários
   a. campos obrigatórios: código e nome
3. Criar o CRUD de exames realizados do funcionário
   a. campos obrigatórios: código do funcionário, código do exam,
   data da realização do exam
4. Implementar melhoria ao deletar um funcionário, deletar também os
   seus exames realizados
5. Implementar melhoria para não permitir deletar um exam se ele tiver
   sido realizado por um ou mais funcionários
6. Implementar melhoria para não permitir cadastrar exames realizados
   duplicados, ou seja, o mesmo exam para o mesmo funcionário na
   mesma data
7. Criar relatório de exames realizados dentro de um período
   a. campos obrigatórios na tela: data inicial e final
   b. campos obrigatórios no relatório: código e nome do funcionário,
   código e nome do exam, data da realização do exam
   c. o relatório pode ser um arquivo xls, um doc ou até mesmo em
   html na própria tela
   Atividades opcionais
   Atividades opcionais
1. Criar / Concluir as funcionalidades do WebService de Exames
   implementando a inclusão, alteração e deleção, atualmente ele contém
   somente a consulta (caso você use o nosso projeto um WebService em
   SOAP já estará feito, você precisa apenas concluir o resto, caso use o
   Spring aceitamos o WebService em REST)
2. Criar o WebService de funcionários
3. Criar o WebService de exames realizados
4. Criar tela de indicadores com os 5 exames mais realizados no período
   a. campos obrigatórios: data inicial e final
5. Criar Login
