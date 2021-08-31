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

class VideoItemView : ViewGroup {

    val textureView = TextureView(context).apply {
        this@VideoItemView.addView(this)
    }

    val cover = ImageView(context).apply {
        this@VideoItemView.addView(this)
    }

    private var player: SimplePlayer? = null

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

    fun play(videoUri: Uri) {
        VideoPlayCounter.play()
        stop()
        VideoPlayCounter.play()
        player = SimplePlayer.Builder(context)
            .setVideoView(textureView)
            .setVideoUri(videoUri)
            .build()
        player?.play()
        cover.invisible()
    }

    fun stop() {
        player?.stop()
        player = null
        cover.visible()
        VideoPlayCounter.stop()
    }
}