# Crud de contatos - RESTful API

Crud de contatos - RESTful API
construída em Java 17 com Spring Boot 3.

## Principais Tecnologias
 - **Java 17**: Utilizaremos a versão LTS mais recente do Java para tirar vantagem das últimas inovações que essa linguagem robusta e amplamente utilizada oferece;
 - **Spring Boot 3**: Trabalharemos com a mais nova versão do Spring Boot, que maximiza a produtividade do desenvolvedor por meio de sua poderosa premissa de autoconfiguração;
 - **Spring Data JPA**: Exploraremos como essa ferramenta pode simplificar nossa camada de acesso aos dados, facilitando a integração com bancos de dados SQL;
 - **OpenAPI (Swagger)**: Vamos criar uma documentação de API eficaz e fácil de entender usando a OpenAPI (Swagger), perfeitamente alinhada com a alta produtividade que o Spring Boot oferece;
 - **Railway**: facilita o deploy e monitoramento de nossas soluções na nuvem, além de oferecer diversos bancos de dados como serviço e pipelines de CI/CD.


## Diagrama de Classes (Domínio da API)

```mermaid
classDiagram
  class Contact {
    -String name
    -String phoneNumber
    -String email
    -Address address
    -String dateOfBirth
    -String company
    -String notes
    -String name
    -SocialNetwork[] socialNetworks
    -Group[] groups
  }

  class Group {
    -String nameItem
    -String description
  }

  class Address {
    -String street
    -String number
    -String district
    -String city
    -String state
    -String country
  }

  class SocialNetwork {
    -String nameItem
    -String description
  }

  Contact "1" *-- "N" Group
  Contact "1" *-- "1" Address
  Contact "1" *-- "N" SocialNetwork
```

