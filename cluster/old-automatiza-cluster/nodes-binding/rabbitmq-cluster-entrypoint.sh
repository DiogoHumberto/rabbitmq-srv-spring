#!/bin/bash
set -e

# Substitua os valores abaixo conforme necessário
RABBITMQ_USER="user"
RABBITMQ_PASSWORD="password"
# Lista dos hostnames dos nós do cluster
CLUSTER_NODES=("rabbitmq1" "rabbitmq2" "rabbitmq3")

# Espera a rede estar pronta
sleep 10

# Configura o RabbitMQ
echo "Configurando RabbitMQ..."
rabbitmqctl add_user $RABBITMQ_USER $RABBITMQ_PASSWORD
rabbitmqctl set_user_tags $RABBITMQ_USER administrator
rabbitmqctl set_permissions -p / $RABBITMQ_USER ".*" ".*" ".*"

# Logica para formar o cluster
# Este é um exemplo e deve ser adaptado conforme a necessidade
if [ "$HOSTNAME" != "rabbitmq1" ]; then
    rabbitmqctl stop_app
    rabbitmqctl join_cluster rabbit@rabbitmq1
    rabbitmqctl start_app
else
    rabbitmqctl start_app
fi

echo "RabbitMQ configurado."

# Manter o container em execução
exec rabbitmq-server
