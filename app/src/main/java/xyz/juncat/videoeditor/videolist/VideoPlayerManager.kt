package xyz.juncat.videoeditor.videolist

import android.util.Log
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

/**
 * delay player.release() to prevent ui freezing
 */
object VideoPlayerManager {

    private val pendingReleaseQueue = LinkedList<WeakReference<VideoItemView>>()
    private const val TAG = "VideoPlayerManager"
    fun add(player: VideoItemView) {
        pendingReleaseQueue.add(WeakReference(player))
    }

    //TODO: Optimization: stop clear when user scroll again
    fun clear() {
        while (pendingReleaseQueue.isNotEmpty()) {
            pendingReleaseQueue.pollFirst()?.get()?.stop()
        }
    }

    fun destroy() {
        pendingReleaseQueue.clear()
    }
}