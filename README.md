# Messenger API - README

## **Visão Geral**
A Messenger API é um serviço backend desenvolvido com Spring Boot que fornece funcionalidades para gerenciamento de mensagens. Esta aplicação é integrada com PostgreSQL para persistência de dados e inclui monitoramento e observabilidade usando Grafana, Loki, Prometheus e Promtail.

O ambiente pode ser configurado e executado facilmente usando **Docker Compose**.


## Documentação Específica:
- [Testes Executados e sua Importância](docs/TestesExecutadosImportancia.md)



## **Pré-requisitos**

Certifique-se de ter os seguintes itens instalados em sua máquina:
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)



## **Configuração do Ambiente**

### **1. Estrutura dos Serviços no Docker Compose**

- **db**: Banco de dados PostgreSQL.
- **backend**: API desenvolvida com Spring Boot.
- **loki**: Serviço de centralização de logs.
- **promtail**: Coletor de logs para Loki.
- **grafana**: Painel de monitoramento.
- **k6**: Serviço para execução de testes de carga.
- **prometheus**: Ferramenta para monitoramento de métricas.

### **2. Como Inicializar os Serviços**

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/GuilhermeMLeal/MessengerMicrosservice.git
   cd MessengerMicrosservice
   ```

2. Suba os containers com o Docker Compose:
   ```bash
   docker-compose up --build
   ```  

3. Verifique se os serviços estão em execução:
    - **API**: Acesse [http://localhost:8080](http://localhost:8080).
    - **PostgreSQL**: Porta `5432`.
    - **Grafana**: Acesse [http://localhost:3000](http://localhost:3000).
    - **Prometheus**: Acesse [http://localhost:9090](http://localhost:9090).



## **Endpoints da API**

### **Base URL**
`http://localhost:8080/api/v1`

### **Endpoints Disponíveis**

| Método | Endpoint                    | Descrição                   | Exemplo de Uso                     |  
|--------|-----------------------------|-----------------------------|-------------------------------------|  
| GET    | `/products/listProducts`    | Lista todos os produtos.    | `http://localhost:8080/api/v1/products/listProducts` |  
| GET    | `/products/getOneProduct`   | Recupera um produto.        | `http://localhost:8080/api/v1/products/getOneProduct?id=1` |  
| POST   | `/products/createProduct`   | Cria um novo produto.       | `http://localhost:8080/api/v1/products/createProduct` |  
| PUT    | `/products/updateProduct`   | Atualiza um produto.        | `http://localhost:8080/api/v1/products/updateProduct` |  
| DELETE | `/products/delete`          | Remove um produto.          | `http://localhost:8080/api/v1/products/delete?id=1` |  



## **Monitoramento e Logs**

### **1. Grafana**
- Acesse o painel em [http://localhost:3000](http://localhost:3000).
- Credenciais padrão:
    - **Usuário**: `admin`
    - **Senha**: `admin`

### **2. Logs Centralizados (Loki)**
- Configuração para coletar logs do backend em `/src/main/br/com/martins/messengerMicrosservice/logs`.
- Logs podem ser visualizados no Grafana, através da fonte de dados Loki.

### **3. Prometheus**
- Ferramenta para coleta de métricas, disponível em [http://localhost:9090](http://localhost:9090).



## **Testes de Carga com k6**

### **Como Executar os Testes**
1. Certifique-se de que o serviço **k6** esteja configurado no Docker Compose.
2. O script de teste está localizado em `./k6/request_test_list_products.js`.
3. Execute o container k6 para rodar os testes:
   ```bash
   docker-compose up k6
   ```  

### **Resultado do Teste**
Os resultados do teste de carga são exibidos diretamente no terminal do container k6, indicando:
- Taxa de sucesso das requisições.
- Tempo médio de resposta.
- Requisições por segundo (RPS).



## **Encerrando os Serviços**

Para parar todos os containers, utilize:
  ```bash
    docker-compose down
   ```  
