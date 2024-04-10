FROM rabbitmq:3.8-management

# Copia o script de entrada personalizado para o container
COPY rabbitmq-cluster-entrypoint.sh /usr/local/bin/

# Torna o script executável
RUN chmod +x /usr/local/bin/rabbitmq-cluster-entrypoint.sh

# Define o script personalizado como o ponto de entrada do container
ENTRYPOINT ["rabbitmq-cluster-entrypoint.sh"]

