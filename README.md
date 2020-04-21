# sessao-votacao

[![Build Status](https://travis-ci.com/RonaldoGuastalli/sessao-votacao.svg?branch=master)](https://travis-ci.com/RonaldoGuastalli/sessao-votacao)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=coverage)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=code_smells)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=bugs)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=RonaldoGuastalli_sessao-votacao&metric=bugs)](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao)

- Neste projeto foi utilizado o framework Spring Boot e o desenvolvimento e desenvolvido em Java, foi utilizado Spring Data para acesso ao banco.
- Para os testes estáticos foi utilizado o banco de dados H2, por ser fácil de utilizar no serviço. Para o banco de dados remoto foi utilizado [PostgreSQL](https://www.postgresql.org/), que possui suporte free em plataformas de serviço (Paas), como o [Heroku](https://www.heroku.com/).

## Modelagem do projeto

Diagrama de caso de uso foi  criado para auxiliar no levantamento dos requistos.
![sessao-votacao-use-case](https://user-images.githubusercontent.com/13247216/79412990-32e92080-7f7d-11ea-9053-a35c1769aec4.png)

Diagrama de classe foi gerado inicialmente para especificar as classe principais (gerando as entidades básicas).
![sessao-votacao-diagrama-classe](https://user-images.githubusercontent.com/13247216/79412918-f61d2980-7f7c-11ea-84fc-e82c6a549567.png)


## Arquitetura do projeto
Para este projeto utilizou a divisão feature, contemplando assim cada feature sua regra de negócio (pauta, voto, voto associado), a arquitetura esta definida na seguinte estrutura de módulos. 
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
O build do projeto, execução de testes, geração de cobertura e deploy da aplicação pode ser visualizadas no remoto utilizando o [Travis CI](https://travis-ci.com/github/RonaldoGuastalli/sessao-votacao), sendo o build gerado através de integração continua com os commitis do GIT. Para deploy da aplicação foi utilizado o Heroku e é possivel realizar os commits sem a necessidade do deploy basta setar em application properties, como apresentado abaixo.
```
spring.profiles.active=prod
``` 
Desta forma as configurações para deploy são acionadas. No [Heroku](https://www.heroku.com/) utilizou como recurso o banco de dados Heroku Postgres, um banco baseado no [PostgreSQL](https://www.postgresql.org/) executado pelo Heroku através da utilização de [add-ons](https://elements.heroku.com/addons). Utilizou-se a opção do “robby-dev” que é um dos níveis gratuitos do Heroku. As configurações do banco dados foram setadas no Travis (Environment Variables) de forma a garantir a segurança da aplicação.

O [SonarCloud](https://sonarcloud.io) foi utilizado para a cobertura análise da cobertura de teste. O relatório pode ser visualizado em: [sessao-votacao](https://sonarcloud.io/dashboard?id=RonaldoGuastalli_sessao-votacao).

## Mensageria com Kafka
Utilizou-se para mensageria o [Kafka](https://kafka.apache.org/), sendo um sistema de mensageria distribuido (publish-subscribe), que é desgnado para ser rápido, scalável e durável podendo suportar grande número de consumidores e reter grande quantidades de dados com pouca sobrecarga.

Para ter em possibilidade de testar, uma mensagem produzida (producer) pela aplicação através de um consumer externo criou-se a seguinte estrutura:

- Utilizou-se o [CloudKarafka](https://www.cloudkarafka.com/docs/index.html) que são servidores Apache Kafka gerenciados na nuvem.
- Criou-se uma instancia do CloudKarafka do tipo ```Developer Duck``` que possibilita o plano free.
- Quando uma pauta atinge a data de finalização, uma mensagem e publicada no tópico (```cloudkarafka.topic=08rv3461-default```).
- Para verificar a mensagem neste tópico criou-se uma projeto simples para servir como consumer [kafka-spring-consumer](https://github.com/RonaldoGuastalli/kafka-spring-consumer), para executar o consumer:

Primeira forma: No projeto acima realizar os seguintes passos
1. git clone https://github.com/RonaldoGuastalli/kafka-spring-consumer.git;
2. cd kafka-spring-consumer;
3. ```./gradlew clean build```;
4. na raiz do projeto executar ```java -jar build/libs/kafka-spring-0.0.1-SNAPSHOT.jar```;
3. criar uma sessão vinculada a uma pauta e quando este estiver com a situação de ENCERRADA será publicada no tópico, portanto este consumer receberá esta mensagem;

Segunda forma: No projeto acima realizar os seguintes passos
1. baizar o .zip com o .jar em [kafka-spring-0.0.1-SNAPSHOT.jar.zip](https://github.com/RonaldoGuastalli/kafka-spring-consumer/files/4484664/kafka-spring-0.0.1-SNAPSHOT.jar.zip);
2. descompactar o arquivo;
3. executar ```java -jar kafka-spring-0.0.1-SNAPSHOT.jar```;
4. aguardar o recebimento da mensagem

IMPORTANTE: a cada 60s é realizada uma verificação na base para identificar se alguma pauta mudou de status, portanto somente neste momento a mensagem será enviada para o tópico, ou seja, pode ter sido encerrada mas ainda não estar no tópico.

Mensagem recebida (consumer):
```
08rv3461-default-3[4] "{"message":{"id":15,"descricao":"Definir data pagamento dividendos.","dataCadastroPauta":{"month":"APRIL","dayOfWeek":"THURSDAY","dayOfYear":107,"nano":917443000,"year":2020,"monthValue":4,"dayOfMonth":16,"hour":0,"minute":20,"second":38,"chronology":{"id":"ISO","calendarType":"iso8601"}},"dataDesativacao":null,"sessoes":[{"id":15,"pautaId":15,"pautaDescricao":"Definir data pagamento dividendos.","situacao":"ENCERRADA","tempoAbertura":1,"dataAbertura":{"month":"APRIL","dayOfWeek":"THURSDAY","dayOfYear":107,"nano":754451000,"year":2020,"monthValue":4,"dayOfMonth":16,"hour":0,"minute":20,"second":45,"chronology":{"id":"ISO","calendarType":"iso8601"}},"dataFinalizacao":{"month":"APRIL","dayOfWeek":"THURSDAY","dayOfYear":107,"nano":754507000,"year":2020,"monthValue":4,"dayOfMonth":16,"hour":0,"minute":21,"second":45,"chronology":{"id":"ISO","calendarType":"iso8601"}},"estatistica":{"votoContra":1,"votoFavor":0,"total":1}}]}}"
```

## Serviços

Para utilizar o serviço em produção (prod): ```https://sessao-votacao.herokuapp.com/```
Para utilizar teste no ambiente local (dev): ```http://localhost:9001```

Serviços disponiveis para utilizar:

Criar uma pauta
``` json
Request:
POST | https://sessao-votacao.herokuapp.com/sessao-votacao/v1/pauta
{
    "descricao": "Definir data pagamento dividendos."
}
Response:
{
    "id": 1,
    "descricao": "Definir data pagamento dividendos.",
    "dataCadastroPauta": "2020-04-16T00:28:55",
    "dataDesativacaoPauta": null,
    "sessoes": null
}

```

Criar uma sessão de uma pauta
``` json
Request:
POST | https://sessao-votacao.herokuapp.com/sessao-votacao/v1/sessao
{
    "pautaId": 1,
    "situacao": "ATIVA",
    "tempoAbertura": 1
}
Response:
{
    "id": 1,
    "pauta": 1,
    "dataAbertura": "2020-04-16T03:33:15",
    "dataTermino": "2020-04-16T03:34:15",
    "duracao": 1,
    "situacao": "ATIVA"
}
```

Votar em uma sessão especifica
``` json
Request:
POST | https://sessao-votacao.herokuapp.com/sessao-votacao/v1/pauta/1/sessao/2/voto-sessao
{
    "cpfAssociado": "41443405035",
    "voto": "SIM"
}
```

Dados estatiscos da pauta (votos computados para sessões)
``` json
Request:
GET | https://sessao-votacao.herokuapp.com/sessao-votacao/v1/pauta/1/pontuacao
{
    "pautaId": 1,
    "situacao": "ATIVA",
    "tempoAbertura": 1
}
Response:
{
    "id": 1,
    "descricao": "Definir data pagamento dividendos.",
    "dataCadastroPauta": "2020-04-16T00:28:55",
    "dataDesativacaoPauta": null,
    "sessoes": [
        {
            "id": 1,
            "pautaId": 1,
            "pautaDescricao": "Definir data pagamento dividendos.",
            "situacao": "ATIVA",
            "tempoAbertura": 1,
            "dataAbertura": "2020-04-16T03:33:15.939915",
            "dataFinalizacao": "2020-04-16T03:34:15.939934",
            "estatistica": {
                "votoContra": 0,
                "votoFavor": 0,
                "total": 0
            }
        },
        {
            "id": 2,
            "pautaId": 1,
            "pautaDescricao": "Definir data pagamento dividendos.",
            "situacao": "ATIVA",
            "tempoAbertura": 1,
            "dataAbertura": "2020-04-16T03:38:23.992112",
            "dataFinalizacao": "2020-04-16T03:39:23.99212",
            "estatistica": {
                "votoContra": 1,
                "votoFavor": 0,
                "total": 1
            }
        }
    ]
}
```



