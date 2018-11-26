#!/bin/bash
python3 object_detection.py --target=0 --model=yolov3-tiny-obj_3900.weights --config=obj.cfg --classes=obj.names  --width=448 --height=448 --scale=0.0392 --rgb
