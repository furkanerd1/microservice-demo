## Acknowledgements
This repository has been created based on the tutorial found at [FolksDev Spring Microservice Playlist](https://www.youtube.com/watch?v=R6Qdmq2iTNA&list=PLCp1YoRkzkpZ3OinTeT2-8A1B7HAujTAu).  
Special thanks to the FolksDev for the valuable guidance and resources.

### docker-compose.yml for Vault
```<i> docker-compose.yml</i>version: "2"
services:
  vault:
    image: hashicorp/vault:latest
    container_name: vault
    ports:
      - "8200:8200"
    restart: always
    volumes:
      - ./volumes/logs:/vault/logs
      - ./volumes/file:/vault/file
      - ./volumes/config:/vault/config
    cap_add:
      - IPC_LOCK
    environment:
      VAULT_ADDR: http://127.0.0.1:8200
    entrypoint: ["vault", "server", "-config=/vault/config/vault.json"]
```

### vault.json
```<i> json</i>{
"backend": {
    "file": {
      "path": "/vault/file"
    }
  },
  "listener": {
    "tcp": {
      "address": "0.0.0.0:8200",
      "tls_disable": true
    }
  },
  "ui": true,
  "disable_mlock": true
}