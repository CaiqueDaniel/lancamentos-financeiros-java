# Lançamentos Financeiros

Essa API visa gerenciar o saldo dos usuários conforme os lançamentos
de gastos e recebimentos são informados.  

Essa aplicação também visa ser uma demonstração prática do uso do Java com o
framework Spring Boot.

Os requisitos da API foram retirados dessa [postagem](https://twitter.com/zanfranceschi/status/1601954872168837120?t=xljKwOhWVWM-_Uh3VMW-kg&s=19) no Twitter.

## Tecnologias

Esse projeto utiliza:

- Java EE 18
- Spring Boot 3
- MySQL Server

## Iniciar aplicação

Para iniciar a aplicação em modo de **desenvolvimento**, é necessário antes possuir uma IDE
Java EE como o [InteliJ Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows), por exemplo, 
e também um SGBD como o [MySQL](https://dev.mysql.com/downloads/installer/).  

Também será necessário gerar chaves RSAs. As instruções estão localizadas em **/src/resources/certs/README.md**.

Uma vez com o ambiente pronto, basta executar a aplicação através da classe **LancamentosFinanceirosApplication**.  

Você pode alterar as configurações da aplicação através do arquivo **src/main/resources/application.properties**.