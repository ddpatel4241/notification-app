package com.carcollation.firestorearchcomp

import android.app.Dialog
import android.content.Context
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import com.carcollation.firestorearchcomp.R
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.carcollation.firestorearchcomp.ResetPasswordActivity
import com.carcollation.firestorearchcomp.LoginActivity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carcollation.firestorearchcomp.commons.ProgressDialogshow
import com.carcollation.firestorearchcomp.commons.extensions.snack
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SignupActivity : AppCompatActivity() ,OtpVerifyPresenter.FragmentView{
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var inputName: EditText? = null
    private var btnSignIn: Button? = null
    private var btnSignUp: Button? = null
    private var btnResetPassword: Button? = null

    private var auth: FirebaseAuth? = null

    @Inject
    lateinit var mPresenter: OtpVerifyPresenter
    @Inject
    lateinit var mSessionHolder: SessionHolder
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Get Firebase auth instance

        (applicationContext as AppApplication).mComponent.inject(this)
        mPresenter.injectView(this)
        mSessionHolder.ACCESS_TOKEN = "rGh41zG1sw_dDP5r65!DjhDwsPd1_3x8xS94Nb3"

        dialog = ProgressDialogshow.progressDialog(this)

        btnSignIn = findViewById<View>(R.id.btn_signup) as Button
        btnSignUp = findViewById<View>(R.id.sign_up_button) as Button
        inputName = findViewById<View>(R.id.name) as EditText
        inputEmail = findViewById<View>(R.id.email) as EditText
        inputPassword = findViewById<View>(R.id.password) as EditText

        btnResetPassword = findViewById<View>(R.id.btn_reset_password) as Button
        btnResetPassword!!.setOnClickListener {
            startActivity(
                Intent(
                    this@SignupActivity,
                    ResetPasswordActivity::class.java
                )
            )
        }
        btnSignIn!!.setOnClickListener {
            startActivity(
                Intent(
                    this@SignupActivity,
                    LoginActivity::class.java
                )
            )
        }
        btnSignUp!!.setOnClickListener(View.OnClickListener {

            val name = inputName!!.text.toString()
            val email = inputEmail!!.text.toString()
            val password = inputPassword!!.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(applicationContext, "Enter name!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            mPresenter.RegisterApi(name,email,password)
            //create user
        })
    }

    override fun onResume() {
        super.onResume()

    }

    override fun showErrorSnack(msg: String) {

        signuplayout.snack(msg)
    }

    override fun getuserlogindata(userid: String,msg:String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        startActivity<LoginActivity>()
        finish()
    }

    override fun setNewsList(
        itemList: ArrayList<PostsItem?>,msg: String,
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