from server.rabbitmq_connection import RabbitMQConnection
from deoldify import device
from deoldify.device_id import DeviceId
from deoldify.visualize import *
import json
from support.log_util import get_logger

class ColoringServer:
    def __init__(self, images_path: str, rabbitmq_host: str, rabbitmq_port: int, rabbitmq_username: str, rabbitmq_password: str) -> None:
        self.logger = get_logger('coloring_server')

        device.set(device=DeviceId.GPU0)
        self.colorizer = get_image_colorizer(artistic=True)
        self.render_factor = 35
        
        self.images_path = os.path.abspath(images_path)

        self.mq = RabbitMQConnection(rabbitmq_host, rabbitmq_port, rabbitmq_username, rabbitmq_password)
        self.mq.start(self.onRequest)
    
    def onRequest(self, ch, method, properties, body):
        self.logger.info(f'Received request body={body}')
        try:
            request_body = json.loads(body)
            filename = request_body['filename']
            result_path = self.colorize(filename)
            head, tail = os.path.split(result_path)
            self.mq.send(json.dumps({
                'success': True,
                'filename': tail
            }))
        except Exception as e:
            self.logger.error('error on colorizing...')
            self.logger.error(e)
            self.mq.send(json.dumps({
                'success': False
            }))
    
    def colorize(self, filename: str) -> str:
        source_path = os.path.join(self.images_path, 'original', filename)
        dest_path = Path(os.path.join(self.images_path, 'result'))
        return self.colorizer.plot_transformed_image(path=source_path, render_factor=self.render_factor, compare=True, results_dir=dest_path, watermarked=False)
        
    def close(self):
        self.mq.close()
