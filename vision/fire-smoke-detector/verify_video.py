"""Headless execution check on the included video, without opening a webcam."""
from pathlib import Path
import cv2
from detector import FireSmokeDetector


def main():
    path = Path(__file__).with_name('video_teste_real.mp4')
    cap = cv2.VideoCapture(str(path))
    if not cap.isOpened():
        raise RuntimeError(f'Cannot open included sample: {path.name}')
    detector = FireSmokeDetector()
    frames = candidates = 0
    try:
        while True:
            ok, frame = cap.read()
            if not ok:
                break
            frames += 1
            detections = detector.detect(frame)
            for detection in detections:
                if detection['label'] not in {'FOGO', 'FUMACA'}:
                    raise ValueError('Unexpected detection label')
                x, y, width, height = detection['bbox']
                if min(x, y) < 0 or min(width, height) <= 0:
                    raise ValueError('Invalid bounding box')
            if frames > 15 and detections:
                candidates += 1
    finally:
        cap.release()
    if frames <= 15 or candidates == 0:
        raise RuntimeError('Sample did not produce detections after warmup')
    print(f'Decoded {frames} frames; {candidates} frames with candidates.')
    print('Execution check only; this does not measure detection accuracy.')


if __name__ == '__main__':
    main()
