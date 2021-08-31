package xyz.juncat.videoeditor.videolist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jianjun.base.ext.dp

class VideoListHorizontalAdapter : RecyclerView.Adapter<VideoListHorizontalAdapter.ViewHolder>() {

    private val dataList = ArrayList<VideoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dstHeight = 180f.dp
        val dstWidth = dstHeight * when (viewType) {
            1 -> {
                4f / 3f
            }
            2 -> {
                16f / 9f
            }
            else -> {
                1f
            }
        }
        return ViewHolder(VideoItemView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(dstWidth.toInt(), dstHeight.toInt())
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].aspectRatio) {
            1f -> {
                0
            }
            4f / 3f -> {
                1
            }
            16f / 9f -> {
                2
            }
            else -> {
                0
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun update(list: List<VideoData>) {
        //todo diff
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.item.play(dataList[holder.adapterPosition].videoUri)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.item.stop()
    }

    class ViewHolder(val item: VideoItemView) : RecyclerView.ViewHolder(item) {

        fun bind(data: VideoData) {
            Glide.with(item.cover)
                .load(data.videoUri)
                .centerCrop()
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(item.cover)
            //todo play video
        }
    }
}


