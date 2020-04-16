# sessao-votacao

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=coverage)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=code_smells)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=bugs)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=bugs)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)

- Neste projeto foi utilizado o framework Spring Boot e o desenvolvimento e desenvolvido em Java, foi utilizado Spring Data para acesso ao banco.
- Para os testes estáticos foi utilizado o banco de dados H2, por ser fácil de utilizar no serviço. Para o banco de dados remoto foi utilizado [PostgreSQL](https://www.postgresql.org/), que possui suporte free em plataformas de serviço (Paas), como o [Heroku](https://www.heroku.com/).

## Arquitetura do projeto
Para este projeto uma divisão em feature, contemplando assim cada feature sua regra de negócio, a seguinte estrutura e módulos foi definida. 
- **contract:** Expôem os endpoint para a camada REST, ou seja, camada responsável pelo contrato.
- **exception:** Camada responsável pelo tratamento das exceções, possui uma classe gérica (GenerecException) devolve exceções de forma amigável.
- **domain:** Camada responsável por conter as regras de neçócio, persistência dos dados, mapeamento de objetos.
- **integration:** integração com serviços externos.
- **message:** Camada responsável pela mensageria utilizando o com [Kafka](https://kafka.apache.org/).

## Documentação da Api
O [Swagger](https://swagger.io) foi utilizado para documentar o contrato da aplicação. Para visualizar acesse através do endereço:
[No servidor](https://sessao-votacao.herokuapp.com/sessao-votacao/swagger-ui.html) ou 
localmente.  
```
http://localhost:9001/sessao-votacao/swagger-ui.html
```

## Execução do projeto
Para gerenciamento das dependências e build utiliza-se o Gradle e o projeto faz uso do java 11.
Foi utilizada a biblioteca do [Lombok](https://projectlombok.org/), para aumento da produtividade e redução de boilerplate. Dependendo da IDE utilizada é necessário ir até o marketplace instalar o plugin e depois em preferências aplilita-lo, caso isso não seja feito os métodos getter, setter, equals, builder podem acusar erro.
Na pasta raiz do projeto executar o comando abaixo para baixar as depências necessárias.
```
./gradlew clean build --refresh-dependencies
```

## Testes e cobertura de testes
O projeto utiliza [JUnit5](https://junit.org/junit5/) como framework de teste. Para os testes unitários foi utilizado o Framework [Mockito](https://site.mockito.org/) com o objetivo de facilitar o processo de mockar métodos ou classes dependentes no teste.
Para verificar a cobertura de teste basta executar o comando abaixo, e o relatório de cobertura esta disponível no seguinte path do projeto: ```build/reports/jacoco/index.html```.
```
./gradlew build codeCoverageReport
```

## Build do codigo e suas métricas
O build do projeto, execução de testes, geração de cobertura e deploy da aplicação pode ser visualizadas no remoto utilizando o [Travis CI](https://travis-ci.com/github/RonaldoGuastalli/sessao-votacao), sendo o build gerado através de integração continua com os comitis do GIT. Para deploy da aplicação foi utilizado o Heroku e é possivel realizar os commits sem a necessidade do deploy basta setar em application propertie como apresentado abaixo.
```
spring.profiles.active=prod
``` 
Desta forma as configurações para deploy acionadas. No [Heroku](https://www.heroku.com/) utilizou como recurso o banco de dados Heroku Postgres, um banco baseado no [PostgreSQL](https://www.postgresql.org/) executado pelo Heroku através da utilização de [add-ons](https://elements.heroku.com/addons). Utilizou-se a opção do “robby-dev” que é um dos níveis gratuitos do Heroku. As configurações do banco dados foram setadas no Travis (Environment Variables) de forma a garantir a segurança da aplicação