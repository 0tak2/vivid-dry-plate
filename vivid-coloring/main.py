import sys
import os
from dotenv import load_dotenv
from server.coloring_server import ColoringServer
from support.log_util import get_logger

logger = get_logger('main')
server = None

def main():
    images_path = os.getenv('IMAGE_STORAGE_PATH')
    rabbitmq_host = os.getenv('RABBITMQ_HOST')
    rabbitmq_port = os.getenv('RABBITMQ_PORT')
    rabbitmq_username = os.getenv('RABBITMQ_USERNAME')
    rabbitmq_password = os.getenv('RABBITMQ_PASSWORD')
    server = ColoringServer(images_path, rabbitmq_host, rabbitmq_port, rabbitmq_username, rabbitmq_password)

if __name__ == '__main__':
    logger.info('Server init ...')

    load_dotenv(verbose=True)

    try:
        main()
    except KeyboardInterrupt:
        logger.info('Server interrupted')
        try:
            if server is not None:
                server.close()
            sys.exit(0)
        except SystemExit:
            os._exit(0)
