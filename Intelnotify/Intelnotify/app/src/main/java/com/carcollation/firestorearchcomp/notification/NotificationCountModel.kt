package com.carcollation.firestorearchcomp.service

import com.google.gson.annotations.SerializedName

class rNotificationCountModel {

    /**
     * status : 1
     * message : Number of unread Notification.
     * details : {"unread_noti_count":"42"}
     */

    @SerializedName("status")
    var status: Int = 0
    @SerializedName("message")
    var message: String? = null
    @SerializedName("details")
    var details: DetailsBean? = null

    class DetailsBean {
        /**
         * unread_noti_count : 42
         */

        @SerializedName("unread_noti_count")
        var unreadNotiCount: String? = null
    }
}
