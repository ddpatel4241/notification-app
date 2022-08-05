package com.carcollation.firestorearchcomp.service

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by Mit Shah (itsmitshah@gmail.com) on 26-Mar-18.
 */

class NotificationModel {

    /**
     * image : https://preview.ibb.co/fTzyh7/sample_banner.png
     * is_background : 0
     * payload : {"updated_on":1522068237,"image":"https://image.ibb.co/kwxC9n/jobs_sample_banner.jpg","module_id":"5","created_on":1522068237,"item_type":"1","description":"Job Details: Required knowledge of test","title":"Job Posted: test,ttt","ItemId":"80"}
     * title : Job Posted: test,ttt
     * message : Job Details: Required knowledge of test
     * timestamp : 1522068237
     */

    class PayloadBean(var type: String?) : Serializable {

        /**
         * notification_type :
         * updated_on : 1525804140
         * image :
         * module_id : 5
         * created_on : 1525804140
         * item_type :
         * description : Job Details: With the knowledge of Android Development.
         * title : Job Posted: 'Post of Mobile Application developer' by Muscle Factory Unisex Gym
         * ItemId : 116
         * is_read : 0
         * noti_read_timestamp : 0
         */

        @SerializedName("id")
        var id: String? = null
        @SerializedName("post_author")
        var authorId: Int = 0
        @SerializedName("subtitle")
        var subtitle: String? = null
        @SerializedName("smallIcon")
        var smallIcon: String? = null
        @SerializedName("sound")
        var sound: String? = null
        @SerializedName("title")
        var title: String? = null
        @SerializedName("vibrate")
        var vibrate: String? = null
        @SerializedName("item_type")
        var itemType: String? = null
        @SerializedName("largeIcon")
        var largeIcon: String? = null
        @SerializedName("message")
        var message: String? = null
        @SerializedName("noti_type")
        var noti_type: String? = null
        @SerializedName("video_url")
        var video_url: String? = null
        @SerializedName("tickerText")
        var tickerText: String? = null


    }
}
