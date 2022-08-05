package com.carcollation.firestorearchcomp


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carcollation.firestorearchcomp.commons.extensions.loadImage
import com.carcollation.firestorearchcomp.commons.extensions.snack
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.detailspage_activity.*

import org.jetbrains.anko.startActivity
import javax.inject.Inject


class DetailsPageActivity : AppCompatActivity() ,OtpVerifyPresenter.FragmentView{
    @Inject
    lateinit var mPresenter: OtpVerifyPresenter
    @Inject
    lateinit var mSessionHolder: SessionHolder
    lateinit var dialog: Dialog



    var id:String=""
    var user_id:String=""
    var plat_number:String=""
    var vehicle_color:String=""
    var regi_reason:String=""
    var detected_time:String=""
    var main_img:String=""
    var plate_img:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailspage_activity)

        (applicationContext as AppApplication).mComponent.inject(this)
        mPresenter.injectView(this)
        mSessionHolder.ACCESS_TOKEN = "rGh41zG1sw_dDP5r65!DjhDwsPd1_3x8xS94Nb3"


        val intent = intent
        if (intent != null) {
            id = intent.getStringExtra("id").toString()
            user_id = intent.getStringExtra("user_id").toString()
            plat_number = intent.getStringExtra("plat_number").toString()
            vehicle_color = intent.getStringExtra("vehicle_color").toString()
            regi_reason = intent.getStringExtra("regi_reason").toString()
            detected_time = intent.getStringExtra("detected_time").toString()
            main_img = intent.getStringExtra("main_img").toString()
            plate_img = intent.getStringExtra("plate_img").toString()
        }



        ivFeedImage.loadImage(main_img,isRoundedCorner = false)
        ivplatmage.loadImage(plate_img,isRoundedCorner = false)

        platnumber.text=plat_number
        carcolor.text=vehicle_color
        regireson.text=regi_reason
        detectdtime.text=detected_time

        logout.clicks().subscribe{

        }
        home.clicks().subscribe{
           onBackPressed()
        }

    }




    override fun showErrorSnack(msg: String) {

    }



    override fun getuserlogindata(user:String,msg:String) {



        mSessionHolder.USER_ID=user.toInt()

        startActivity<MainActivity>()
        finish()
    }

    override fun setNewsList(
        itemList: ArrayList<PostsItem?>,msg: String,
        resetFlag: Boolean,
        isFromSwipe: Boolean
    ) {

    }

    override fun setCategoryList(
        itemList: ArrayList<PostsItem?>,
        msg: String,
        resetFlag: Boolean,
        isFromSwipe: Boolean
    ) {

    }

    override val mContext: Context?
        get() = this
    override val mCompositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    override fun showProgress(visibility: Boolean) {
        if (visibility) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    override fun showContent(visibility: Boolean) {

    }

    override fun showEmptyView(visibility: Boolean, message: String) {

    }

    override fun handleNoInternet() {


    }
}