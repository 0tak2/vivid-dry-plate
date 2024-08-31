import logging
import sys

def get_logger(name: str):
    logger = logging.getLogger(name)

    logger.propagate = False

    stdout_handler = logging.StreamHandler(sys.stdout)
    stdout_handler.setFormatter(logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s'))
    logger.addHandler(stdout_handler)

    return logger
