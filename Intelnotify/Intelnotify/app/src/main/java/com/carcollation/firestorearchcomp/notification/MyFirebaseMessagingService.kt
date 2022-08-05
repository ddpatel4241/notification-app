package com.carcollation.firestorearchcomp.service


import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import com.carcollation.firestorearchcomp.AppApplication
import com.carcollation.firestorearchcomp.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson


import org.json.JSONObject



class MyFirebaseMessagingService : FirebaseMessagingService() {


    internal var intent: Intent? = null
    var flag: String = ""
    private var videoId = ""
    override fun onNewToken(s: String) {
        super.onNewToken(s)

        AppApplication.refreshtoken.setrefreshtoken= s.toString()

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        try {

            val jsonObject = JSONObject(remoteMessage!!.data as Map<*, *>)
            val gson = Gson()
            val taskModel =gson.fromJson(jsonObject.toString(),
                    NotificationModel.PayloadBean::class.java)


            sendPushNotification(taskModel)

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun sendPushNotification(taskModel: NotificationModel.PayloadBean) {

        var inputid:String=""
        try {
            val mNotificationManager = MyNotificationManager(applicationContext)
            if(isAppRunning(applicationContext,"com.carcollation.firestorearchcomp"))
            {
                flag="2"

            }else
            {
                flag="3"

            }
            var intent: Intent? = null
            
            intent = Intent(applicationContext, MainActivity::class.java)
            
            //intent.putExtra(AppConstants.IntentKey.NotiType, taskModel.getNotificationType()+"");
            intent!!.action = java.lang.Long.toString(System.currentTimeMillis())
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            if(taskModel.id!!.length > 3) {
                inputid = taskModel.id!!.substring(taskModel.id!!.length - 3);
            }else
            {
                inputid = taskModel.id!!
            }
            mNotificationManager.showSmallNotification(applicationContext,
                taskModel.id!!.toInt(),taskModel.largeIcon, taskModel.title, taskModel.subtitle, intent,taskModel.vibrate,taskModel.sound)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        val intent = Intent("com.carcollation.firestorearchcomp.MY_ACTION")
        intent.putExtra("data", "2")
        sendBroadcast(intent)



    }

    fun isAppRunning(context: Context, packageName: String): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val procInfos = activityManager.runningAppProcesses
        if (procInfos != null) {
            for (processInfo in procInfos) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && processInfo.processName == packageName) {
                    return true
                }
            }
        }
        return false
    }

    companion object {
        private val TAG = "notification"
    }
}