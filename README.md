# Video Editor Demo

## [Extract Music from Mp4](/app/src/main/java/xyz/juncat/videoeditor/MusicExtractorActivity.kt)

## [Extract Bitmap from video](app/src/main/java/xyz/juncat/videoeditor/frames/FramesExtractActivity.kt)
- `MediaMetadataRetriever`
  - fastest
  - `getScaledFrameAtTime()` need API >= 27
- `FFMpeg`
- `MediaCodec`
  - > https://bigflake.com/mediacodec/ExtractMpegFramesTest_egl14.java.txt

