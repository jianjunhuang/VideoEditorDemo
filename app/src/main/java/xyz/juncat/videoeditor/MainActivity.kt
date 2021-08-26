package xyz.juncat.videoeditor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import xyz.juncat.videoeditor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var clickId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.children.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        clickId = v?.id ?: -1
        val targetIntent = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "video/mp4"
        }
        startActivityForResult(Intent.createChooser(targetIntent, "select video"), 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200 && data != null) {
            val videos = ArrayList<Uri>()
            if (data.data != null) {
                data.data?.let { uri ->
                    videos.add(uri)
                }
            } else if (data.clipData != null) {
                for (index in 0 until (data.clipData?.itemCount ?: 0)) {
                    data.clipData?.getItemAt(index)?.let {
                        videos.add(it.uri)
                    }
                }
            }
            videos.firstOrNull()?.let {
                startIntent(clickId, it)
            }
        }
    }

    private fun startIntent(viewId: Int, videoUri: Uri) {
        val intent = Intent().apply {
            data = videoUri
        }
        when (viewId) {
            R.id.btn_extract_music -> {
                intent.setClass(this, MusicExtractorActivity::class.java)
            }
        }
        if (viewId > 0) startActivity(intent)
    }
}