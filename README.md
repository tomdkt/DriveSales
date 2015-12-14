# DriveSales
Sumarização da importação de movimentação de vendas no primeiro trimestre de algumas filiais por delimitados.

### To build you will need:
  - Maven 3.x
  - Java 1.7x
  - internet acess

### Para dar build e realizar testes(no pom.xml):
```sh
$ mvn clean install
```
ou
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

### Para criar novo template para importação de outros arquivos delimitados é necessario, basicamente, criar uma classe template e uma classe process:
```sh
@FilialPeriodoTotal
public class FilialPeriodoTotalTemplate {
    
    @Position(startIn = POSITION.ZERO)
    private String filial;
    
    @Position(startIn = POSITION._1)
    private String periodo;
    
    @Position(startIn = POSITION._2, isDecimal = true)
    private String total;

    public String getFilial() {
        return filial;
    }
    
	///getter and setters
```
para realizar o processamento:
```sh
public class FilialPeriodoTotalProcess implements DelimitedProcess {
    //...
    
    @Override
    public void parse(String line) throws InstantiationException, IllegalAccessException{
        if(line == null || line.isEmpty()){
            return;
        }
        String[] parsed = line.split(delimited.getValue(), -1);
        
        FilialPeriodoTotalTemplate template = parseReflectionService.getEntity(parsed, header);
        
        String location = template.getFilial();
        String month = template.getPeriodo();
        String total = template.getTotal();
        
        
        Branch branch = new Branch();
        branch.setName("");
        branch.setLocation(location);
        branch = getBranch(branch, company.getBranchs());
        if(branch == null){
            
        }
        
        Sale sale = new Sale();
        MonthPeriod currentMonth = monthConverter.getMonthPeriodFromMonthEnum(monthConverter.getMonth(month), null);
        sale.setInicialDate(currentMonth.getInicialDate());
        sale.setFinalDate(currentMonth.getFinalDate());
        sale.setTotal(MoneyHelper.convertFromBrazilian(total));
        branch.getSales().add(sale);
        
        this.company.getBranchs().add(branch);
    }
        //...
```


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