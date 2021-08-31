package xyz.juncat.videoeditor.videolist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jianjun.base.ext.dp

class VideoListVerticalAdapter :
    RecyclerView.Adapter<VideoListVerticalAdapter.VerticalViewHolder>() {

    private val dataList = ArrayList<VideoListData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return VerticalViewHolder(VideoListItem(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200f.dp.toInt())
        })
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun update(list: List<VideoListData>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    class VerticalViewHolder(private val item: VideoListItem) : RecyclerView.ViewHolder(item) {

        fun bind(data: VideoListData) {
            if (item.videoRecyclerView.adapter == null) {
                item.videoRecyclerView.adapter = VideoListHorizontalAdapter()
            }
            (item.videoRecyclerView.adapter as? VideoListHorizontalAdapter)?.update(data.list)
            item.title.setText(data.category)
        }
    }
}