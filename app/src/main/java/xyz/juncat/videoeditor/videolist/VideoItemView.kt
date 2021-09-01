package xyz.juncat.videoeditor.videolist

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.jianjun.base.ext.invisible
import com.jianjun.base.ext.visible
import xyz.juncat.videoeditor.SimplePlayer

class VideoItemView : ViewGroup, SimplePlayer.Callback {

    val cover = ImageView(context).apply {
        this@VideoItemView.addView(this)
    }

    val textureView = TextureView(context).apply {
        this@VideoItemView.addView(this)
    }

    private var player: SimplePlayer? = null
    private var videoUri: Uri? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val matchMeasureWidth =
            MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY)
        val matchMeasureHeight =
            MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY)
        textureView.measure(matchMeasureWidth, matchMeasureHeight)
        cover.measure(matchMeasureWidth, matchMeasureHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        textureView.layout(0, 0, width, height)
        cover.layout(0, 0, width, height)
    }

    fun setVideoUri(videoUri: Uri) {
        this.videoUri = videoUri
    }

    fun play(videoUri: Uri) {
        setVideoUri(videoUri)
        if (player == null)
            VideoPlayCounter.play()
        stop()
        player = SimplePlayer.Builder(context)
            .setVideoView(textureView)
            .setVideoUri(videoUri)
            .setCallback(this)
            .build()
        player?.play()
        textureView.visible()
    }

    fun stop() {
        if (player != null)
            VideoPlayCounter.stop()
        player?.stop()
        player = null
        textureView.invisible()
    }

    fun restart() {
        if (player != null) {
            return
        }
        videoUri?.let {
            play(it)
        }
    }

    override fun onPlayingCallback(isPlaying: Boolean) {
        if (isAttachedToWindow)
            if (isPlaying) {
                textureView.visible()
            } else {
                textureView.invisible()
            }
    }

}