# idbrasil-test

Foi proposta criar um endpoint que receba dados de um produto e seu valor.
Faz a consulta na API taxa Selic e retorna uma lista de taxas do último mês.
Retorna uma lista de parcelas com o número da parcela, valor da parcela e a taxa a ser implicada.

* Não é necessário banco de dados.
* Foram implementados testes unitários.
* Swagger configurado.

## endpoint
/v1/venda/calcular-venda

## JSON request Body
{
  "product": {
    "id": 0,
    "name": "string",
    "price": 0
  },
  "payment": {
    "entry": 0,
    "installments": 0
  }
}

## Swagger
http://localhost:8080/swagger-ui/index.html#


## Passos a serem executados na máquina 

Compilar maven e executar:
 - mvn clean install -DskipTests
 - cd target
 - java -jar .\idbrasil-0.0.1.jar
 


