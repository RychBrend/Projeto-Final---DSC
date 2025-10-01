
   <img width="320" height="320" alt="Gemini_Generated_Image_6wgbgn6wgbgn6wgb-Photoroom" src="https://github.com/user-attachments/assets/73f35ec8-0844-4de2-a44a-6547cdb01337" />




# Gerenciamento de Oficina de Moto  🏍️⚙️




## Funcionalidades:
* CRUD Completo de Veículos: Crie, leia, atualize e delete registros de veículos.

* Arquitetura em Camadas: Organização clara do código em Controllers, Services, Repositories e Models.

* Padrão DTO: Separação entre os objetos de domínio e os objetos expostos pela API, garantindo mais segurança e flexibilidade.

* Validação de Dados: Utilização do Bean Validation para garantir a integridade dos dados de entrada na API.

* Tratamento Centralizado de Exceções: Uso do @ControllerAdvice para capturar exceções e retornar respostas de erro padronizadas e amigáveis.

* Segurança e Autenticação: Implementação de autenticação HTTP Basic com Spring Security para proteger os endpoints.

* Documentação Interativa com Swagger: Geração automática de documentação da API com Springdoc, permitindo a fácil visualização e teste dos endpoints.

* Persistência de Dados com Spring Data JPA: Abstração da camada de acesso a dados, facilitando a comunicação com o banco de dados.


## Tecnologias utilizadas:

* Linguagem: Java
* Framework: Spring Boot
* Banco de dados: Postgres
* Utilitários: Lombok
* Build: Maven
* Documentação: Springdoc OpenAPI (Swagger)

## Configuração
         O projeto já vem pré-configurado para se conectar a um banco de dados PostgreSQL na nuvem (Neon), portanto, não é necessário instalar ou configurar um banco de dados localmente.
         
         As credenciais de acesso e a chave secreta do JWT estão no arquivo src/main/resources/application.properties.
         
         Como Rodar o Projeto
         Siga os passos abaixo para executar a aplicação:
         
         Clone o repositório:
         
         Bash
         
         git clone <URL_DO_SEU_REPOSITORIO>
         Navegue até o diretório do projeto:
         
         Bash
         
         cd <NOME_DA_PASTA_DO_PROJETO>
         Execute a aplicação usando o Maven Wrapper:
         
         
         mvnw.cmd spring-boot:run
         A aplicação irá iniciar e estará disponível em http://localhost:8080.
         
         ## Testando a API com o Swagger
         A forma mais fácil de interagir e testar a API é através da documentação interativa do Swagger.
         
         * Acesse a Documentação:
         Após iniciar a aplicação, abra seu navegador e acesse a seguinte URL:
         http://localhost:8080/swagger-ui.html

Fluxo de Autenticação (Obrigatório):
Quase todos os endpoints são protegidos e exigem um token de autenticação (JWT). Siga este fluxo:

* a. Registre um usuário:

         Encontre o endpoint POST /api/auth/register.
         
         Clique em "Try it out" e use o JSON abaixo para criar um novo usuário:
         
         JSON
         
         {
           "nome": "Seu Nome",
           "username": "seu_usuario",
           "password": "sua_senha_123",
           "cargo": "ADMIN"
         }
         Clique em "Execute". Você deve receber uma mensagem de sucesso.

* b. Faça login para obter um token:

         Encontre o endpoint POST /api/auth/login.
         
         Clique em "Try it out" e use as credenciais que você acabou de registrar:
         
         JSON
         
         {
           "username": "seu_usuario",
           "password": "sua_senha_123"
         }
         Clique em "Execute". A resposta conterá um token. Copie este token.

* c. Autorize suas requisições:

         No topo da página do Swagger, clique no botão "Authorize".
         
         Na janela que abrir, cole o token no campo "Value", no formato Bearer <seu_token>. Exemplo:
         
         Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3Mub...
         Clique em "Authorize" e depois em "Close". Agora você está autenticado!

## Teste os Endpoints Protegidos:
Com a autorização configurada, você pode agora testar os outros endpoints, como POST /api/clientes, GET /api/pecas, etc. O token será automaticamente incluído no cabeçalho de todas as requisições feitas pela interface do Swagger.
