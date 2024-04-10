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

sleep 60
# Continuar com as etapas de join_cluster após o RabbitMQ estar pronto
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@rabbit1
rabbitmqctl start_app