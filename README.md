# quiroprax-api
Quiroprax API

## Necessário (Recomendado)
* Java 17 (v17.0.3)
* Apache Maven 3.8.6
* MySQL


## Rodar Backend (dev)
```bash
cd api
mvn clean install -D skipTests -P dev
mvn spring-boot:run -P dev
```


## Documentação
API Docs disponível em: ```/swagger-ui.html``` 


## Autenticação
Executando a requisição abaixo, o token de acesso ADMIN será retornado
```json
{
  "login": "atendente1",
  "senha": "senha"
}
```

Use-o como valor para o header 'Authorization' nas requisições no formato: `Bearer [token]`

ou<br>

Insira ele na opção de autorização do [Swagger](#documentação): <img align="center" alt="Swagger 'Authorization'" height="35" width="110" src="https://github.com/user-attachments/assets/cbdeb0b5-7cff-4288-ad3f-e9e55e373690"/>


