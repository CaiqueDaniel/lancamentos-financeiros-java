Nessa pasta deverá ficar as chaves pública e privada que serão utilizadas para gerar os tokens JWT da aplicação.

Para gerar as chaves, basta inserir os comandos abaixo:

```shell
# Cria chaves pública e privada.
openssl genrsa -out keypair.pem 2048

# Extrai chave pública
openssl rsa -in keypair.pem -pubout -out public.pem

# Cria chave privada no formato PKCS#8
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```