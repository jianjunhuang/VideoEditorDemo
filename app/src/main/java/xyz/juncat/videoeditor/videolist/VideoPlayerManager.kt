package xyz.juncat.videoeditor.videolist

import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArrayList

/**
 * delay player.release() to prevent ui freezing
 */
object VideoPlayerManager {

    private val pendingReleaseQueue = CopyOnWriteArrayList<WeakReference<VideoItemView>>()

    fun add(player: VideoItemView) {
        pendingReleaseQueue.add(WeakReference(player))
    }

    //TODO: Optimization: stop clear when user scroll again
    fun clear() {
        pendingReleaseQueue.forEach {
            it.get()?.stop()
        }
        pendingReleaseQueue.clear()
    }

}