package com.carcollation.firestorearchcomp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carcollation.firestorearchcomp.commons.ProgressDialogshow
import com.carcollation.firestorearchcomp.commons.extensions.snack
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem

import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*


import org.jetbrains.anko.startActivity
import javax.inject.Inject


class LoginActivity : AppCompatActivity() ,OtpVerifyPresenter.FragmentView{
    @Inject
    lateinit var mPresenter: OtpVerifyPresenter
    @Inject
    lateinit var mSessionHolder: SessionHolder
    lateinit var dialog: Dialog



    var familycode:String=""
    var mobileno:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        (applicationContext as AppApplication).mComponent.inject(this)
        mPresenter.injectView(this)
        mSessionHolder.ACCESS_TOKEN = "rGh41zG1sw_dDP5r65!DjhDwsPd1_3x8xS94Nb3"

        dialog = ProgressDialogshow.progressDialog(this)
        if(mSessionHolder.USER_ID != 0)
        {
            startActivity<MainActivity>()
            finish()
        }

        btn_login.clicks().subscribe {
            validateData()

            //  startActivity<VerifyActivity>()
            // finish()
        }


        sign_in_button.clicks().subscribe {
            startActivity<SignupActivity>()
            finish()
        }


    }

    private fun validateData() {
        try {

            if(TextUtils.isEmpty(email.text.toString())) {
                loginlayout.snack(getString(R.string.err_invalid_id))
            }else if(TextUtils.isEmpty(password.text.toString())) {
                loginlayout.snack(getString(R.string.err_invalid_password))
            }
            else
            {
                mPresenter.LoginApi(email.text.toString(),password.text.toString())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun showErrorSnack(msg: String) {
        loginlayout.snack(msg)
    }



    override fun getuserlogindata(user:String,msg:String) {


        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        mSessionHolder.USER_ID=user.toInt()

        startActivity<MainActivity>()
        finish()
    }

    override fun setNewsList(
        itemList: ArrayList<PostsItem?>,msg:String,
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

    override fun setCategoryList(
        itemList: ArrayList<PostsItem?>,
        msg: String,
        resetFlag: Boolean,
        isFromSwipe: Boolean
    ) {

    }
}