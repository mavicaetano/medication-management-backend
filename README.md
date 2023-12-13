# Farmatec

Este projeto é uma API que visa centralizar o gerenciamento de medicamentos e farmácias.

### Tecnologias

- [Java 21](https://www.java.com/)
- [Spring Boot 3.2.0](https://spring.io/projects/spring-boot) com as seguintes bibliotecas:
  - Spring Data JPA
  - Spring Web
  - Spring Validation
- PostgreSQL Driver 42.6.0
- Jakarta Validation API 3.0.2
- Lombok 1.18.30
- [PostgreSQL Server 15](https://www.postgresql.org)
- [Maven 3.9.5](https://maven.apache.org)

### Instalação e execução

- Abra o projeto no IntelliJ;
- Em Maven, faça o clean e depois o install;
- Execute a classe [MedicationManagementApplication](./src/main/java/caetano/maria/medicationmanagement/MedicationManagementApplication.java) para rodar o projeto;
- Baixe e execute o [Postman](https://www.postman.com);
- Instale o [Postman Collection](https://github.com/mavicaetano/medication-management-backend/blob/feature/readme/src/main/resources/medication-management-backend.postman_collection.json) do projeto;

#### Utilizando os serviços da API

No Postman, você encontrará as seguintes requisições:

![Status HTTP](https://www.flickr.com/photos/198458787@N05/53393476201/in/dateposted-public/)

###### 1. POST INICIALIZAÇÃO
Este método POST inicializará os dados das farmácias, medicamentos e estoque no sistema.

###### 2. GET ALL MEDICAMENTOS
Enviando esta requisição, será possível visualizar todos os medicamentos disponíveis nas farmácias cadastradas (com os dados da inicialização).

###### 3. GET ONE MEDICAMENTO
Aqui, você deverá colocar, além do endpoint padrão (/medicamentos), o número de registro do medicamento que você quer buscar (/medicamentos/número-de-registro). Os medicamentos cadastrados e seus respectivos números de registro estão disponíveis na tabela "MEDICAMENTO". Caso o número de registro não exista, será retornado um erro.

###### 3. GET ALL FARMÁCIAS
Enviando estaa requisição, será possível visualizar todas as farmácias cadastradas.

###### 4. GET ONE FARMÁCIA
Nesta requisição, você deverá colocar o CNPJ da farmácia após o endpoint padrão (/farmacias/cnpj-da-farmacia). Caso não haja uma farmácia com o CNPJ utilizado, será retornado um erro.

###### 5. POST MEDICAMENTO
Neste método POST, na aba "Body", selecione "raw". Devem ser enviadas as informações, em JSON, no seguinte formato:
```sh
{
   "nroRegistro": 1101,
   "nome": "Pílula vermelha",
   "laboratorio": "Matrix",
   "dosagem": "1mg",
   "descricao": "Este medicamento é uma pílula para sair da Matrix",
   "preco": 10.0,
   "tipo": "COMUM"
}
```
Você pode mudar os dados da requisição para incluir outras medicações. Todos os campos, com exceção do campo "descricao", são obrigatórios. Caso um dos campos obrigatórios não seja preenchido, será retornado um erro.

###### 6. POST FARMÁCIA