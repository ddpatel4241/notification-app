package com.carcollation.firestorearchcomp.notification

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carcollation.firestorearchcomp.DetailsPageActivity
import com.carcollation.firestorearchcomp.R
import com.carcollation.firestorearchcomp.commons.extensions.loadImage
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.notificationlayoutitem.view.*
import kotlinx.android.synthetic.main.notificationlayoutitem.view.rlTopTenItem
import kotlinx.android.synthetic.main.notificationlayoutitem.view.tvtitle


/**
 * Created by aakash on 6/8/18.
 */
class NotificationAdapter(
    private val itemList: ArrayList<PostsItem?>,
    private val onNewsFeedClick: (position: Int) -> Unit,
    var activity: Activity?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var sbShareData: StringBuffer? = null
    companion object {
        const val VIEW_TYPE_LOADER = 0
        const val VIEW_TYPE_CONTENT = 1
    }

    fun addNull() {
        itemList.add(null)
        notifyItemInserted(itemList.size - 1)
    }

    fun removeNull() {
        itemList.remove(null)
        notifyItemRemoved(itemList.size)
    }

    override fun getItemViewType(position: Int) =
            if (itemList[position] == null) VIEW_TYPE_LOADER
            else VIEW_TYPE_CONTENT

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_CONTENT) {
            val newsModel = itemList[position]
            holder.itemView.apply {
                newsModel?.let {


                    tvtitle.text = itemList[position]?.plat_number

                    ivFeedImage.loadImage(itemList[position]?.main_img,isRoundedCorner = false)
                    tvdate.text=itemList[position]?.detected_time


                    rlTopTenItem.clicks().subscribe{
                        context.startActivity(
                            Intent(context, DetailsPageActivity::class.java)
                                .putExtra("id", newsModel.id)
                                .putExtra("user_id",  newsModel.user_id)
                                .putExtra("plat_number",  newsModel.plat_number)
                                .putExtra("vehicle_color",  newsModel.vehicle_color)
                                .putExtra("regi_reason",  newsModel.regi_reason)
                                .putExtra("detected_time",  newsModel.detected_time)
                                .putExtra("main_img",  newsModel.main_img)
                                .putExtra("plate_img",  newsModel.plate_img)
                        )
                    }


                }
            }

        }
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType == VIEW_TYPE_CONTENT) {
            HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notificationlayoutitem, parent, false))
        } else {
            LoaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loader_view, parent, false))
        }
    }

    inner class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


