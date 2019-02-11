# Desafio Peixe Urbano

Desafio técnico para vaga de desenvolvedor Java na Peixe Urbano.

Desenvolvido por Nelson Mariano Leite Neto


## Pré-requisitos

Para compilar e executar o projeto é necessário
```
- Maven
- Docker
```

## Compilar o Projeto com Maven

```
mvn package
```

Este comando irá gerar:

```
/target/peixe-urbano-challenge-0.1.0.jar
```

## Rodando o Projeto com o Docker

Gerar a imagem com o Dockerfile:

```
docker build -f Dockerfile -t peixe-urbano-challenge .
```

Iniciar o container para o banco de dados mysql

```
docker run -p 6603:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=db_challenge" mysql:latest --default-authentication-plugin=mysql_native_password
```

Após a inicializaçao completa do banco, inciar o container peixe-urbano-challenge e criar link com o container mysql

```
docker run -t --name peixe-urbano-challenge --link docker-mysql:mysql -p 8080:8080 peixe-urbano-challenge 
```

URL do projeto:

```
localhost:8080
```

### Docker compose

Criei um arquivo docker-compose.yml para a mesma configuração acima, porém não funcionou corretamente e não entendi o motivo. Parece-me algo no container mysql, o projeto spring não consegue se conectar.

```
docker-compose up
```

## Requisitos conhecidos que ficaram faltando

* Gerar URL da oferta com slug baseado no nome do produto;
* Testes Unitários e de Integração
* Deploy da aplicação em ambiente AWS

## Anotações
No arquivo notes.txt existem anotações que fui fazendo conforme o desenvolvimento.