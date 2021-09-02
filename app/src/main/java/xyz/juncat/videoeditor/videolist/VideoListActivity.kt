package xyz.juncat.videoeditor.videolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import xyz.juncat.videoeditor.R
import xyz.juncat.videoeditor.databinding.ActivityVideoListBinding

/**
 * RecyclerView 中多个视频情况
 */
class VideoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chipGroup.isSelectionRequired = true
        binding.chipGroup.isSingleSelection = true
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            VideoPlayerManager.selectedPlayer = when (checkedId) {
                R.id.chip_exo -> {
                    VideoPlayerManager.PLAYER_EXO
                }
                else -> {
                    VideoPlayerManager.PLAYER_IJK
                }
            }
            intent.data?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, VideoListFragment.newInstance(it))
                    .commitAllowingStateLoss()
            }
        }
    }
}