package com.suprize.app.shareddata.endPoints

/**
 * Created by stllpt065 on 2/11/17.
 */
object ResponseCode {
    val OK = 200

    val BAD_REQUEST = 400

    val AUTHENTICATION_FAILED = 401

    val UNAUTHORIZED_ACCESS = 403

    val RESOURCE_NOT_FOUND = 404

    val CONFLICT_OCCURS = 409

    val UNPROCESSABLE_ENTITY = 422

    val INTERNAL_UNKNOWN_EXCEPTION = 500

    val GATEWAY_TIMEOUT_ERROR = 504

    object RC {

        const val LOCATION = 8461

        const val NOTIFICATION = 6936

        const val CONTACT = 5043

        const val GOOGLE_RC_SIGN_IN = 2001
    }

}
