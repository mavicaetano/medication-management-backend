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
- Importe o [Postman Collection](https://github.com/mavicaetano/medication-management-backend/blob/feature/readme/src/main/resources/medication-management-backend.postman_collection.json) do projeto;

#### Utilizando os serviços da API

No Postman, você encontrará as seguintes requisições:

![Status HTTP](https://www.flickr.com/photos/198458787@N05/53393476201/in/dateposted-public/)

###### 1. POST INICIALIZAÇÃO
`http://localhost:8080/inicializacao`

Esta deve ser a primeira requisição a ser enviada. Este método POST inicializará os dados das farmácias, medicamentos e estoque no sistema.

###### 2. GET ALL MEDICAMENTOS
`http://localhost:8080/medicamentos`

Enviando esta requisição, será possível visualizar todos os medicamentos disponíveis nas farmácias cadastradas (com os dados da inicialização).

###### 3. GET ONE MEDICAMENTO
`http://localhost:8080/medicamentos/8880`

Aqui, você deverá colocar, além do endpoint padrão (/medicamentos), o número de registro do medicamento que você quer buscar (/medicamentos/número-de-registro). Os medicamentos cadastrados e seus respectivos números de registro estão disponíveis na tabela "MEDICAMENTO". Caso o número de registro não exista, será retornado um erro.

###### 3. GET ALL FARMÁCIAS
`http://localhost:8080/farmacias`

Enviando esta requisição, será possível visualizar todas as farmácias cadastradas.

###### 4. GET ONE FARMÁCIA
`http://localhost:8080/farmacias/43178995000198`

Nesta requisição, você deverá colocar o CNPJ da farmácia após o endpoint padrão (/farmacias/cnpj-da-farmacia). Caso não haja uma farmácia com o CNPJ utilizado, será retornado um erro.

###### 5. POST MEDICAMENTO
`http://localhost:8080/medicamentos`

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
Você pode substituir os dados da requisição para incluir outras medicações. Todos os campos, com exceção do campo "descricao", são obrigatórios. Caso um dos campos obrigatórios não seja preenchido, será retornado um erro.

###### 6. POST FARMÁCIA
`http://localhost:8080/farmacias`

Na aba "Body" (mantenha as configurações anteriores), envie a requisição no seguinte formato:
```sh
{
   "cnpj": 3333333333,
   "razaoSocial": "NovaFarm Ltda",
   "nomeFantasia": "Farmácia NovaFarm",
   "email": "email10@email.com",
   "telefone": "(44)4444-444",
   "celular": "(55)5555-555",
   "endereco": {
       "cep": 88090777,
       "logradouro": "Rua Paris",
       "numero": 255,
       "bairro": "Centro",
       "cidade": "Madagascar",
       "estado": "SP",
       "complemento": "Apto 201",
       "latitude": 12,
       "longitude": 13
   }
}
```
Os dados da farmácia e do endereço podem ser substituídos. Todos os campos, exceto "telefone" e "complemento", são obrigatórios. Caso um dos campos obrigatórios não seja preenchido, será retornado um erro.

###### 7. GET ESTOQUE
`http://localhost:8080/estoque/43178995000198`

Após o endpoint padrão, coloque o CNPJ da farmácia cujo estoque você quer consultar (/estoque/cnpj-da-farmacia).
Caso o CNPJ informado não seja cadastrado, será retornado um erro. Caso a farmácia não possua estoque, o retorno será uma lista vazia.

###### 8. POST ESTOQUE
`http://localhost:8080/estoque`

No corpo da requisição, envie no seguinte formato:
```sh
{
	"cnpj": 90561736000121,
	"nroRegistro": 2233,
	"quantidade": 3
}
```
Você pode substituir os dados para cadastrar um novo estoque. Todos os campos são obrigatórios.

###### 9. DELETE ESTOQUE
`http://localhost:8080/estoque`

Para remover um medicamento do estoque, você deverá fazer uma requisição no seguinte formato:
```sh
{
	"cnpj": 90561736000121,
	"nroRegistro": 2233,
	"quantidade": 3
}
```
Informe o CNPJ da farmácia, o número de registro do medicamento e a quantidade de medicamentos a serem removidos. Caso um dos campos for retirado, será retornado um erro.

###### 10. PUT ESTOQUE
`http://localhost:8080/estoque`

Com esta requisição, será possível fazer trocas de medicamentos entre as farmácias, para equilibrar o estoque. A requisição deverá ser enviada no seguinte formato:
```sh
{
	"cnpjOrigem": 90561736000121,
	"cnpjDestino": 43178995000198,
	"nroRegistro": 1010,
	"quantidade": 2
}
```
Informe o CNPJ da farmácia de origem do medicamento, depois o CNPJ da farmácia de destino do medicamento, informe também o número de registro do medicamento que você quer enviar e a quantidade. Caso um dos campos for retirado, será retornado um erro.

###### 11. GET HEALTH CHECK
`http://localhost:8080/health`

Esta requisição é apenas um teste para saber se o programa está funcionando :)