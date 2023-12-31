# natural-person
## Health Insurance Beneficiaries

### API para manutenção do cadastro de Beneficiários

natural-person foi desenvolvimento utilizando como arquitetura interna o modelo Hexagonal de cockburn [Arquitetura Hexagonal](https://alistair.cockburn.us/hexagonal-architecture/)

![beneficiario-hexagonal.png](https://github.com/helsonsant/natural-person/blob/8fa5be84639445b85e5be2afe04a832d5c9b0c26/beneficiario-hexagonal.png)

O objetivo desta arquitetura é isolar a camada de domínio da aplicação das camadas de infraestrutura, tanto para acesso
ao domínio da aplicação via HTTP Rest, neste caso, quanto para a camada de acesso a base de dados (H2).

A documantação da aplicação pode ser consultada no endpoint do OpenAPI:  localhost:8090/public/docs-ui.html

![beneficiario-swagger.png](https://github.com/helsonsant/natural-person/blob/353e7e62f8de5990ebf975c299300485ba91069a/beneficiario-swagger.png)



Os endpoints estão protegidos com Basic Authentication:

Usuário: admin senha: secretadminpassword
Usuário: user  senha:notsosecretuserpassword

Para executar a aplicação basta, na pasta da aplicação digitar:

    mvn sprint-boot:run

