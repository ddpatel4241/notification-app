package com.carcollation.firestorearchcomp


import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carcollation.firestorearchcomp.commons.extensions.isVisible
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import com.carcollation.firestorearchcomp.notification.CategoryAdapter
import com.carcollation.firestorearchcomp.notification.NotificationAdapter
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem
import com.google.android.material.navigation.NavigationView
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.contain_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import javax.inject.Inject

class MainActivity : AppCompatActivity(),OtpVerifyPresenter.FragmentView {

    private val itemList = ArrayList<PostsItem?>()
    private val itemList1 = ArrayList<PostsItem?>()

    @Inject
    lateinit var mPresenter: OtpVerifyPresenter

    @Inject
    lateinit var mSessionHolder: SessionHolder

    private var isLoading = false

    private var drawer_layout: DrawerLayout? = null
    private var rv_category_videos: RecyclerView? = null
    var ll_back: LinearLayout? = null
    private var navigationView: NavigationView? = null

    var storeid:String ="0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        (applicationContext as AppApplication).mComponent.inject(this)
        mPresenter.injectView(this)


        ll_back = findViewById(R.id.ll_back)
        navigationView = findViewById<NavigationView>(R.id.navigationView)
        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val inflate = LayoutInflater.from(this).inflate(R.layout.drawer_view, null as ViewGroup?)


        rv_category_videos = inflate.findViewById<RecyclerView>(R.id.rv_category_videos)
        navigationView?.addView(inflate)

        setupUi()

        mPresenter.fetchcategoryList(false)
        mPresenter.fetchNotificationList("0",false)

        ll_back?.setOnClickListener { view: View? ->
            drawer_layout?.openDrawer(GravityCompat.START, true)
        }

    }



    private fun setupUi() {




        logout.clicks().subscribe {
            Toast.makeText(this,"Logout Successfully", Toast.LENGTH_SHORT).show()
            mSessionHolder.USER_ID=0
            startActivity<LoginActivity>()
            finish()
        }

        swiperefreshLayout.onRefresh {
            isLoading = true
            mPresenter.fetchNotificationList(storeid,true)


        }
        rv_all_videos?.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = NotificationAdapter(itemList,
                onNewsFeedClick = { position ->
                    itemList[position]?.let {


                    }

                },activity = this@MainActivity
            )

        }

        rv_category_videos?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryAdapter(itemList1,
                onNewsFeedClick = { position ->
                    itemList1[position]?.let {
                        drawer_layout?.closeDrawer(GravityCompat.START, true)
                        titlebar.text=itemList1[position]?.store_name.toString()
                        storeid=itemList1[position]?.store_id.toString()
                        mPresenter.fetchNotificationList(itemList1[position]?.store_id.toString(),false)
                    }

                },activity = this@MainActivity
            )

        }
    }

    override fun showErrorSnack(msg: String) {

    }

    override fun getuserlogindata(userid:String,msg:String) {

    }

    override fun setNewsList(
        itemList: ArrayList<PostsItem?>,msg: String,
        resetFlag: Boolean,
        isFromSwipe: Boolean
    ) {

        if(itemList.isEmpty()) {
            nodatafound.text=msg
            swiperefreshLayout.visibility=View.GONE
            nodatafound.visibility=View.VISIBLE

        }else
        {

            this.itemList.clear()
            swiperefreshLayout.visibility=View.VISIBLE
            nodatafound.visibility=View.GONE
            isLoading = resetFlag
            if (isFromSwipe) {
                this.itemList.clear()
            } else {
                (rv_all_videos.adapter as NotificationAdapter).removeNull()
            }
            this.itemList.addAll(itemList)
            rv_all_videos.adapter?.notifyDataSetChanged()

        }
    }

    override fun setCategoryList(
        itemList: ArrayList<PostsItem?>,
        msg: String,
        resetFlag: Boolean,
        isFromSwipe: Boolean
    ) {

        var item=PostsItem()
        item.store_id="0"
        item.store_name="All"
        itemList1.add(item)

        isLoading = resetFlag
        if (isFromSwipe) {
            this.itemList1.clear()
        } else {
            (rv_category_videos?.adapter as CategoryAdapter).removeNull()
        }
        this.itemList1.addAll(itemList)
        rv_category_videos?.adapter?.notifyDataSetChanged()

    }

    override val mContext: Context?
        get() = this
    override val mCompositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    override fun showProgress(visibility: Boolean) {

        if (!visibility) {
            swiperefreshLayout.isRefreshing = false
        }
        if (!isLoading) {
            ll_no_data_available.visibility = visibility.isVisible()
        }

    }

    override fun showContent(visibility: Boolean) {

    }

    override fun showEmptyView(visibility: Boolean, message: String) {

    }

    override fun handleNoInternet() {

    }
}