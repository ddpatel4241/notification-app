package com.carcollation.firestorearchcomp.notification

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carcollation.firestorearchcomp.DetailsPageActivity
import com.carcollation.firestorearchcomp.R
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.category_item.view.*


/**
 * Created by aakash on 6/8/18.
 */
class CategoryAdapter(
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

                    tvtitle.text = itemList[position]?.store_name




                    rlTopTenItem.clicks().subscribe {
                        onNewsFeedClick(position)
                    }


                }
            }

        }
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CONTENT) {
            HomeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
            )
        } else {
            LoaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loader_view, parent, false)
            )
        }
    }

    inner class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


