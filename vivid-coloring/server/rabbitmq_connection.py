import pika
import logging
from support.log_util import get_logger

REQUEST_QUEUE='colorizing_request'
RESPONSE_QUEUE='colorizing_response'

class RabbitMQConnection:
    def __init__(self, host: str, port: int, username: str, password: str) -> None:
        self.logger = get_logger('rabbit_mq_connection')
        
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(host,
                                                                port,
                                                                '/',
                                                                pika.PlainCredentials(username, password)))
        self.request_channel = self.connection.channel()
        self.request_channel.queue_declare(queue=REQUEST_QUEUE)
        self.response_channel = self.connection.channel()
        self.response_channel.queue_declare(queue=RESPONSE_QUEUE)

    def start(self, onRequest):
        self.logger.info(f'start to consume `{REQUEST_QUEUE}` ...')

        self.request_channel.basic_consume(queue=REQUEST_QUEUE,
                auto_ack=True,
                on_message_callback=onRequest)
        self.request_channel.start_consuming()
    
    def send(self, body):
        self.response_channel.basic_publish(exchange='',
                            routing_key=RESPONSE_QUEUE,
                            body=body)

    def close(self):
        self.connection.close()
