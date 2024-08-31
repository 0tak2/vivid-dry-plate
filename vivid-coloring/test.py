import sys
import os
import pika
from dotenv import load_dotenv
import json

load_dotenv(verbose=True)

connection = None
request_channel = None
response_channel = None

def onResponse(ch, method, properties, body):
    print(f' [x] Received body={body}')

    response_body = json.loads(body)
    if response_body['success']:
        print(f'Colorizing completed... filename={response_body["filename"]}')
    else:
        print('Colorizing failed...')

def init():
    global request_channel
    global response_channel

    connection = pika.BlockingConnection(pika.ConnectionParameters(os.getenv('RABBITMQ_HOST'),
    os.getenv('RABBITMQ_PORT'),
    '/',
    pika.PlainCredentials(os.getenv('RABBITMQ_USERNAME'), os.getenv('RABBITMQ_PASSWORD'))))

    request_channel = connection.channel()
    request_channel.queue_declare(queue='colorizing_request')

    response_channel = connection.channel()
    response_channel.queue_declare(queue='colorizing_response')
    response_channel.basic_consume(queue='colorizing_response',
                    auto_ack=True,
                    on_message_callback=onResponse)

def main():
    init()

    request_channel.basic_publish(exchange='',
                        routing_key='colorizing_request',
                        body=json.dumps({
                            'filename': 'pan000005.jpg'
                        }))
    print(" [x] Sent request")
    response_channel.start_consuming()

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print('Interrupted')
        try:
            if connection is not None:
                connection.close()
            sys.exit(0)
        except SystemExit:
            os._exit(0)
