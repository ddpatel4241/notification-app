package com.carcollation.firestorearchcomp.shareddata.base

import android.content.Intent
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class HomeBaseFragment : Fragment() {

    open var mCompositeDisposable = CompositeDisposable()

    companion object {
        const val REQUEST_CODE_NO_INTERNET = 1001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_NO_INTERNET && REQUEST_CODE_NO_INTERNET == resultCode) {
            onInternetReconnected()
        }
    }

    private fun clearDisposible() {
        mCompositeDisposable.clear()
        mCompositeDisposable.dispose()

    }

    override fun onDetach() {
        super.onDetach()
        clearDisposible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearDisposible()
    }



    abstract fun onInternetReconnected()

    fun reloadFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }
}