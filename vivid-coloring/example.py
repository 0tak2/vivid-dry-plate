from deoldify import device
from deoldify.device_id import DeviceId
from deoldify.visualize import *

#choices:  CPU, GPU0...GPU7
device.set(device=DeviceId.GPU0)

colorizer = get_image_colorizer(artistic=True)

#NOTE:  Max is 45 with 11GB video cards. 35 is a good default
render_factor=35
#NOTE:  Make source_url None to just read from file at ./video/source/[file_name] directly without modification
source_url = None
source_path = 'test_images/pan000005.jpg'
result_path = None

if source_url is not None:
    result_path = colorizer.plot_transformed_image_from_url(url=source_url, path=source_path, render_factor=render_factor, compare=True)
else:
    result_path = colorizer.plot_transformed_image(path=source_path, render_factor=render_factor, compare=True)

print(result_path)