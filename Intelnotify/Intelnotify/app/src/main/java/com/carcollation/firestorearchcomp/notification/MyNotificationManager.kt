package com.carcollation.firestorearchcomp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import android.text.Html
import android.widget.RemoteViews
import com.carcollation.firestorearchcomp.R

import java.net.HttpURLConnection
import java.net.URL

class MyNotificationManager internal constructor(private val mCtx: Context) {

    /*The method will show a small notification
    parameters are title for title title, title for title text and an intent that will open
    when you will tap on the notification*/

    internal fun showSmallNotification(context: Context ,id: Int, imageUrl: String?,title: String?, desc: String?, intent: Intent?,vibrate: String?,sound: String?) {

        var resource = getBitmapfromUrl(imageUrl)
        val channelId = mCtx.getString(R.string.default_notification_channel_id)
        ID_SMALL_NOTIFICATION = ID_SMALL_NOTIFICATION + 1
        val resultPendingIntent = PendingIntent.getActivity(mCtx, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT)
        val mBuilder = NotificationCompat.Builder(mCtx)
        val notification: Notification


        val defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        if(sound=="1")
        {
           // defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        /*Picasso.get()
                  .load(imageUrl)
                 .into(notificationLayoutExpanded, R.id.image_pic, ID_SMALL_NOTIFICATION, notification);*/
        val v:LongArray
        if(vibrate=="1") {
            v = longArrayOf(500, 1000)

          /*  notification = mBuilder.setWhen(0)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(Html.fromHtml(desc)))
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(desc).toString().substring(0, 20))
                    .setSmallIcon(R.drawable.logo)
                    .setLargeIcon(resource)
                    .setVibrate(v)
                    .build()*/


            notification = NotificationCompat.Builder(context, channelId)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(Html.fromHtml(desc)))
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(desc))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(resource)
                    .setVibrate(v)
                    .setChannelId(channelId)
                    .build()

            notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            val notificationManager = mCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Since android Oreo notification channel is needed.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId,
                        mCtx.getString(R.string.default_notification_channel_name),
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(id, notification)
        }else
        {
           /* notification = mBuilder.setWhen(0)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(Html.fromHtml(desc)))
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(desc).toString().substring(0, 20))
                    .setSmallIcon(R.drawable.logo)
                    .setLargeIcon(resource)
                    .build()*/



            notification = NotificationCompat.Builder(context, channelId)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(Html.fromHtml(desc)))
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(desc).toString())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(resource)
                    .setChannelId(channelId)
                    .build()

            notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            val notificationManager = mCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Since android Oreo notification channel is needed.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId,
                        mCtx.getString(R.string.default_notification_channel_name),
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(id, notification)
        }
    }





    fun getApplicationName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }

    /*
    *To get a Bitmap image from the URL received
    * */
    fun getBitmapfromUrl(imageUrl: String?): Bitmap? {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    companion object {
        private var ID_SMALL_NOTIFICATION = 235
    }
}
