package xyz.juncat.videoeditor

import android.content.Context
import android.net.Uri
import android.view.TextureView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

class SimplePlayer private constructor(
    private val context: Context,
    private val lifecycle: Lifecycle?,
    private val uri: Uri?,
    private val textureView: TextureView
) : LifecycleObserver {

    private var player: SimpleExoPlayer? = null

    init {
        lifecycle?.addObserver(this)
        initPlayer()
    }

    private fun initPlayer() {
        stop()
        uri?.let {
            player = SimpleExoPlayer.Builder(context).build()
            val mediaItem = MediaItem.fromUri(it)
            player?.setMediaItem(mediaItem)
            player?.setVideoTextureView(textureView)
            player?.repeatMode = Player.REPEAT_MODE_ALL
            player?.addListener(object : Player.Listener {
                override fun onPlayerError(error: ExoPlaybackException) {
                    super.onPlayerError(error)
                    error.printStackTrace()
                }
            })
            player?.playWhenReady = false
            player?.prepare()
        }
    }

    fun play() {
        initPlayer()
        player?.playWhenReady = true
        player?.play()
    }

    fun resume() {
        player?.playWhenReady = true
    }

    fun pause() {
        player?.playWhenReady = false
    }

    fun stop() {
        player?.release()
        player = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        stop()
    }

    class Builder(private val context: Context) {

        private var lifecycle: Lifecycle? = null
        private var uri: Uri? = null
        private var view: TextureView? = null

        fun registerLifecycle(lifecycle: Lifecycle): Builder {
            this.lifecycle = lifecycle
            return this
        }

        fun setVideoUri(uri: Uri?): Builder {
            this.uri = uri
            return this
        }

        fun setVideoView(view: TextureView): Builder {
            this.view = view
            return this
        }

        fun build(): SimplePlayer {
            return SimplePlayer(context, lifecycle, uri, view ?: TextureView(context))
        }
    }

}