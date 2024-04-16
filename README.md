# Sistema de Transações de Dinheiro

Este é um projeto de sistema simples de transação de dinheiro entre dois clientes, com suporte para tipos de cliente COMMON e MERCHANT. O cliente MERCHANT só pode receber dinheiro e não pode realizar transferências.

## Funcionalidades Principais

- **Tipos de Cliente:** Os clientes podem ser do tipo COMMON ou MERCHANT. O tipo MERCHANT só pode receber dinheiro.
- **Verificação de Saldo:** Antes de realizar uma transferência, o sistema verifica se o cliente tem saldo suficiente na conta.
- **Autorização de Transação:** O sistema utiliza um mockup para gerar um arquivo JSON com o status "Autorizado" ou "Não autorizado", simulando um ambiente real.
- **Notificações:** Os usuários recebem notificações confirmando que a transação foi realizada com sucesso.

## Configuração

1. **Clonagem do Repositório:** Clone este repositório em sua máquina local.
2. **Compilação e Execução:** Utilize Maven para compilar e executar o projeto.

```
mvn clean install
mvn spring-boot:run
```

3. **Acesso à Aplicação:** Acesse a aplicação em `http://localhost:8080`.

## Endpoints

### Transações

- **GET /transactions**: Retorna todas as transações cadastradas no sistema.
- **POST /transactions**: Insere uma nova transação no sistema.

Exemplo de corpo da requisição para inserir uma transação:

```json
{
  "amount": 500,
  "payerId": 2,
  "payeeId": 1
}
```

### Usuários

- **GET /users**: Retorna todos os usuários cadastrados no sistema.
- **POST /users**: Insere um novo usuário no sistema.

Exemplo de corpo da requisição para inserir um usuário:

```json
{
  "name": "Jorge",
  "document": "987456123",
  "balance": 20,
  "email": "jorge@email",
  "password": "12345",
  "userType": "MERCHANT"
}
```

## DTOs (Data Transfer Objects)

DTOs são objetos usados para transferir dados entre subsistemas da aplicação. Eles facilitam a manipulação e transporte de dados, permitindo uma separação clara entre a camada de apresentação e a camada de serviço.

### Usuário DTO

O DTO para a entidade Usuário contém os seguintes campos:

- **name**: Nome do usuário.
- **document**: Documento de identificação do usuário.
- **balance**: Saldo da conta do usuário.
- **email**: Endereço de e-mail do usuário.
- **password**: Senha do usuário (geralmente armazenada de forma segura, como um hash).
- **userType**: Tipo do usuário, que pode ser "COMMON" ou "MERCHANT".

Exemplo de JSON para Usuário DTO:

```json
{
    "name": "Jorge",
    "document": "987456123",
    "balance": 20,
    "email": "jorge@email",
    "password": "12345",
    "userType": "MERCHANT"
}
```

### Transação DTO

O DTO para a entidade Transação contém os seguintes campos:

- **amount**: Valor da transação.
- **payerId**: ID do usuário pagador.
- **payeeId**: ID do usuário recebedor.

Exemplo de JSON para Transação DTO:

```json
{
    "amount": 500,
    "payerId": 2,
    "payeeId": 1
}
```

Esses DTOs são utilizados para facilitar a manipulação de dados em diversas partes da aplicação, garantindo uma estrutura consistente e coerente. Se precisar de mais alguma coisa, estou à disposição!
