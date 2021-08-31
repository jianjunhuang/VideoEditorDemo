package xyz.juncat.videoeditor.videolist

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoListFragment : Fragment() {

    private val adapter = VideoListVerticalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecyclerView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@VideoListFragment.adapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            //todo resume play
                        }
                        else -> {
                            //todo pause play
                        }
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Uri>(KEY_VIDEO_URI)?.let {
            adapter.update(VideoDataFactory.genVideoListData(it))
        }
    }

    override fun onPause() {
        super.onPause()
        //todo pause video
    }

    override fun onResume() {
        super.onResume()
        //todo resume video
    }

    override fun onDestroy() {
        super.onDestroy()
        //todo stop
    }

    companion object {
        private const val KEY_VIDEO_URI = "key_video_uri"

        fun newInstance(videoUri: Uri): VideoListFragment {
            return VideoListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_VIDEO_URI, videoUri)
                }
            }
        }
    }
}