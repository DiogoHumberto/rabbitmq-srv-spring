#!/bin/bash

# Nome do primeiro nó do RabbitMQ
FIRST_NODE_NAME="rabbitmq1"

# Adiciona um nó ao cluster
join_cluster() {
    local node=$1
    local target_node=$2

    echo "->Iniciando Join cluster **** $node com ->>> $target_node"
    rabbitmqctl stop_app
    sleep 20
    rabbitmqctl reset
    sleep 20
    rabbitmqctl join_cluster rabbit@$target_node
    sleep 20
    rabbitmqctl start_app
}

echo "!!! -> Iniciando script para host : $HOST_NAME_NODE"

# Espera 30 segundos
echo "-->>>-- Aguardando 2 min para Inicializar..."
sleep 120


# Adiciona os outros nós ao cluster
if [ "$HOST_NAME_NODE" != "$FIRST_NODE_NAME" ]; then
    # Espera 30 segundos
    echo "-->>>-- $HOST_NAME_NODE processando 2min para join_cluster..."
    sleep 120
    join_cluster $HOST_NAME_NODE $FIRST_NODE_NAME

else
    # Configuração do nó principal
    echo "---> Iniciando node principal $HOST_NAME_NODE"
    rabbitmqctl stop_app
    sleep 30
    rabbitmqctl reset
    sleep 30
    rabbitmqctl start_app

fi
