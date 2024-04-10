#!/bin/bash

# Inicia o RabbitMQ
rabbitmq-server -detached

# Espera até que o RabbitMQ esteja pronto para aceitar conexões
rabbitmqctl wait /var/run/rabbitmq/pid

# Executa o script de join_cluster
/scripts/configure_cluster.sh
