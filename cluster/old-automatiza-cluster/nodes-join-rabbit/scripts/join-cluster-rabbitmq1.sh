#!/bin/bash

# Definir a variável de ambiente para ignorar o aviso
export RABBITMQ_IGNORE_WARNING_RABBITMQ_ERLANG_COOKIE=true

# Esperar até que o RabbitMQ esteja pronto para formar o cluster
wait_for_rabbitmq() {
    until rabbitmqctl node_health_check 2>&1; do
        echo "Aguardando o RabbitMQ ficar pronto..."
        sleep 10
    done
}

# Chamar a função wait_for_rabbitmq para esperar o RabbitMQ
wait_for_rabbitmq

# Junta o nó rabbitmq2 ao cluster
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app