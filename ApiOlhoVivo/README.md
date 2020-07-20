
## Teste Aiko

### Video

Você poderá ver o video clicando nesse link [Link
](www.youtube.com)
### API - Olho Vivo

 - Versão: 1.0

v. 1.0

### Tools
- Java 11
- Spring Security
- Spring MVC
- Spring Boot
- Swagger
- MySql
- Docker/DockeCompose


### Arquitetura
A arquitetura da aplicação segue os pelo Spring, tendo a seguinte organização:

 - Secutiry -> Classes para geração de artefatos de segurança da aplicação
 - Controllers -> Classes que recebem as requisições
 - Entities -> Entidades do banco de dados
 - Service ->Classes que tem com funções implementar as validações e regras de negocios antes de salvar no bando de dados ou retornar uma informação;
 - Repository -> Classes de manipulação do banco de dados
 - Utils -> Classes que contem outras funções uteis para a aplicação (Calculo por exemplo)
 - Configure -> Classes de configuração da aplicação
 - Exception -> Classes para geração de exceptivos personalizados

### Rodar aplicação localmente

##### Rodando em contâiners

    mvn clean install -DskipTests
    docker-compose up
##### Rodando diretamente na máquina
Inicialmente é necessário ter um banco de dados criado como nome "olhoVivo". é necessário também configurar a senha e o usuário do bando de dados no application.properties, após isso, rodar:

    mvn clean install -DskipTests
    mvn spring-boot:run

 

 

### API Documentation

Para acessar a documentação Swagger, após rodar a aplicação, deverá acessar o link: "http://localhost:8000/swagger-ui.html#!", atentar para a porta, caso você tenha mudado nas configurações;

