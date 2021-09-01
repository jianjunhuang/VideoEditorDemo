package xyz.juncat.videoeditor.videolist

import xyz.juncat.videoeditor.SimplePlayer
import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArrayList

object VideoPlayerManager {

    private val pendingReleaseQueue = CopyOnWriteArrayList<WeakReference<VideoItemView>>()

    fun add(player: VideoItemView) {
        pendingReleaseQueue.add(WeakReference(player))
    }

    fun clear() {
        pendingReleaseQueue.forEach {
            it.get()?.stop()
        }
        pendingReleaseQueue.clear()
    }

}