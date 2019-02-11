# Desafio Peixe Urbano

Desafio técnico para vaga de desenvolvedor Java na Peixe Urbano.

Desenvolvido por Nelson Mariano Leite Neto

## Requisitos não atendidos

* Gerar URL da oferta com slug baseado no nome do produto;
* Testes Unitários e de Integração
* Deploy da aplicação em ambiente AWS

## Pré-requisitos para rodar o sistema

Para compilar e executar o projeto é necessário
```
- Maven
- Docker
```

## Compilar o Projeto com Maven

Compilar o projeto com o maven.

(O projeto possui alguns poucos testes unitários, porém o maven tenta subir a aplicação durante os testes e ocorre falha com o banco, por isso o -DskipTests=true)
```
mvn package -DskipTests=true
```

JAR será gerado em:

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

Criei um arquivo docker-compose.yml para a mesma configuração acima, porém não funcionou corretamente e não entendi o motivo. Parece-me algo no container mysql, o projeto spring não consegue se conectar com o banco.

```
docker-compose up
```

## Anotações
No arquivo notes.txt existem anotações que fui fazendo conforme o desenvolvimento.