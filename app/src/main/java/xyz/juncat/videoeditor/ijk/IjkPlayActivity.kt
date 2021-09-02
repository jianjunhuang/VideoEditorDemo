package xyz.juncat.videoeditor.ijk

import android.content.Context
import android.graphics.SurfaceTexture
import android.os.Bundle
import android.util.AttributeSet
import android.view.Surface
import android.view.SurfaceHolder
import android.view.TextureView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import xyz.juncat.videoeditor.databinding.ActivityIjkPlayerBinding

class IjkPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIjkPlayerBinding
    private var player: IjkMediaPlayer? = null
    private var playerSurface: Surface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIjkPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                intent.data?.let {
                    player = IjkMediaPlayer()
                    player?.setDataSource(this@IjkPlayActivity, it)
                    playerSurface = Surface(surface)
                    player?.setSurface(playerSurface)
                    player?.isLooping = true
                    player?.prepareAsync()
                }
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                player?.release()
                playerSurface?.release()
                return true
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
            }
        }
    }

}