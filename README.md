# DriveSales
Sumarização da importação de movimentação de vendas no primeiro trimestre de algumas filiais por delimitados.

### To build you will need:
  - Maven 3.x
  - Java 1.7x
  - internet acess

### Para realizar testes(no pom.xml):
```sh
$ mvn test
```

### Para subir o servidor(no pom.xml):
```sh
$ mvn spring-boot:run
```

### Para realizar o teste acesse o link abaixo e faço o upload do arquivo:
http://localhost:8080/

### Para trocar a porta do servidor acesse:
`src/main/resources/application.properties` e altere o `server.port`

### Estrategia
Utilizado reflexão e annotation para melhor manutenibilidade na inclusão de novos tipos de arquivos. Realizado foco em 'contract design'(algumas injeções de dependencia e factories serão realizadas posteriormente)

### Technologies
  - Java 1.7
  - maven-3.3.3
  - Spring Boot 1.3(Spring core, Spring data, Spring MVC, ThymeLeaf)
  - H2 database embutido e em memoria.
  - Jetty embutido - containner web
  - BootStrap + Jquery.
  - OS usado : "linux", version: "3.16.0-4-amd64", arch: "amd64", family: "unix"
  - 3.16.0-4-amd64 #1 SMP Debian 3.16.7-ckt11-1 (2015-05-24) x86_64 GNU/Linux
  - Netbeans 8.02 | Eclipse Mars