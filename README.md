# Video Editor Demo

## [Extract Music from Mp4](/app/src/main/java/xyz/juncat/videoeditor/MusicExtractorActivity.kt)

## [Extract Bitmap from video](app/src/main/java/xyz/juncat/videoeditor/frames/FramesExtractActivity.kt)
- `MediaMetadataRetriever`
  - fastest
  - `getScaledFrameAtTime()` need API >= 27
- `FFMpeg`
- `MediaCodec`
  - > https://bigflake.com/mediacodec/ExtractMpegFramesTest_egl14.java.txt

## [Video List](app/src/main/java/xyz/juncat/videoeditor/videolist)
Play multi videos at `RecyclerView`

- Tips
  - Do not play videos on `SCROLL_STATE_DRAGGING` ,`SCROLL_STATE_SETTLING` and fling
  - Delay release player. The release operation will occupy the main thread and cause lag

- `ExoPlayer`
- `IJKPlayer` 
- `MediaPlayer`
