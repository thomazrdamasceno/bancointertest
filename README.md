# API

Api desenvolvida utilizando Spring Boot versão 2.3.4. 

# Pré-requisitos
| |  |
| ------ | ------ |
| JDK 1.8 | https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html |
| Maven | https://maven.apache.org/download.cgi |

# Como executar a aplicação
 - Clone este repositório
 - No terminal, entre na pasta referente ao projeto e execute o comando **mvn clean package**  para fazer o build
 - Aguarde o processo terminar, depois execute o seguinte comando para iniciar a aplicação
  ```sh
      java -jar -Dspring.profiles.active=test target/bancointer-0.0.1-SNAPSHOT.jar
ou
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```

Se tudo ocorreu bem, a aplicação estará disponível no endereço: http://localhost:8080/

# Testes
Para execução de testes, utilize o comando **mvn test**

# Banco de dados
A aplicação realiza a persistência de dados em memória, utilizando o banco de dados H2, as informações de acesso estão definidas no arquivo **application.properties**. A interface do banco pode ser acessada através do endereço http://localhost:8080/h2-console

Usuário: sa

Senha: password

# Documentação
A documentação completa da API, pode ser consultada em http://localhost:8080/swagger-ui/index.html

# Importante!
Ao inserir um novo usuário, deve-se especificar a chave publica a ser utilizada para criptografar as informações no campo **publicKey**. 

Exemplo: 
  ```sh
{
    "nome": "Thomaz Reis Damasceno",
    "email": "email@gmail.com",
    "publicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAydWojUdRIwKngdOqfnlgeY67CX5Xw1krxdPJzzu4UgYeGoq26KrsJeh/ojYuh3763KmsbZUWxOnank/7OJvSeoeDC9YMj3zTHBxkkTokrSk1KE9g8QQqh6cpki4rRKi/dkJn2w72RzpYvdLuSnH/W2nvBeRUnUAYgIowZbMSPmlUxsSQemmZC22r4IePYx/cmAY76K50Q9HJrIr64pFRr+POGBswj5hJnY4uE9EI0Tek+WGLk4800uDVUD7YAa5QLNb7xEEluec2ZYOrJFM8ouoZPvC34YWqTFwL+e9mEC+So8h1p/tQo0vEsJlLPFUPgTuQY95aoCu66lVAmhcyAQIDAQAB"
}
```

Você pode gerar um par de chaves na própria aplicação, através do endereço http://localhost:8080/api/criptografia/gerar-key-pair, ou utilizar um gerador externo como https://travistidwell.com/jsencrypt/demo/. Vale lembrar que o sistema aceita somente chaves de tamanho 2048, geradas utilizando o algoritmo RSA.
Como o sistema utiliza criptografia assimétrica (também conhecida como criptografia de chave pública), a chave pública é utilizada para criptografia, e a chave privada(de posse somente do usuário) é utilizada para descriptografar as informações. Para visualização dos dados descriptografados, você pode utilizar o site (https://travistidwell.com/jsencrypt/demo/).
Para qualquer dúvida, entre em contato através do e-mail thomazrdamasceno@gmail.com

