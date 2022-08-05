package com.carcollation.firestorearchcomp.shareddata.endPoints

import com.carcollation.firestorearchcomp.shareddata.model.*
import com.suprize.app.shareddata.model.GlobalResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface EndPoints {




    @GET
    fun get_allcategories (
        @Url string: String,
        @Header("Authorization") token: String
    ): Observable<Response<Categorydata>>

    @GET
    fun get_categorieswisenew (
        @Url string: String,
        @Header("Authorization") token: String
    ): Observable<Response<CommonPaginationResponse>>





    @POST("ciapi/chovdiyaparivarapi/get_post")
    fun get_post(
            @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/chovdiyaparivarapi/get_related_posts")
    fun get_relatedpost(
            @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/chovdiyaparivarapi/get_posts_categorywise")
    fun post_category_wise(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/chovdiyaparivarapi/get_banners")
    fun get_banners(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/chovdiyaparivarapi/get_mobile_ads")
    fun get_ads(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/Chorvadiyahapi/send_otp")
    fun sendotp(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/reset_password")
    fun reset_password(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/Chorvadiyahapi/get_user_list")
    fun get_user_list(
        @Body body: RequestBody
    ): Observable<Response<BusinessResponse>>



    @POST("api/add_najik_na_sabhya")
    fun add_najik_na_sabhya(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/remove_najik_na_sabhya")
    fun remove_najik_na_sabhya(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/get_najik_na_sabhya")
    fun get_najik_na_sabhya(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>





    @POST("send_notification/api/user/login")
    fun user_login(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("send_notification/api/user/register")
    fun user_register(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/home_banners")
    fun home_banners(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/gallery_banners")
    fun gallery_banners(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("api/amarantran_banners")
    fun amarantran_banners(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/Chorvadiyahapi/get_recent_posts")
    fun committee_memberapi(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("api/get_business_list")
    fun get_business_list(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("api/city_wise_users")
    fun city_wise_users(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("api/get_user_details")
    fun get_user_details(
        @Body body: RequestBody
    ): Observable<Response<AddLink>>


    @POST("api/update_user_details")
    fun update_user_details(
        @Body body: RequestBody
    ): Observable<Response<AddLink>>


    @POST("api/image_upload_in_content")
    fun image_upload_in_content(
        @Body body: RequestBody
    ): Observable<Response<AddLink>>

    @POST("api/insert_gallery")
    fun insert_gallery(
        @Body body: RequestBody
    ): Observable<Response<AddLink>>


    @Multipart
    @POST("api/image_upload_in_content")
    fun postImage(
        @Part image: MultipartBody.Part,
    ): Call<ResponseBody?>?

    @POST("api/check_profile_status")
    fun check_profile_status(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

   

    //ciapi/Chorvadiyahapi/get_recent_posts'

    @POST("ciapi/Chorvadiyahapi/get_recent_posts")
    fun get_recent_posts(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/Chorvadiyahapi/verify_otp")
    fun verifyotp(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/chovdiyaparivarapi/save_contactDetails")
    fun save_contactDetails(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("ciapi/chovdiyaparivarapi/get_category_list")
    fun fetchcategory_list(
            @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("ciapi/chovdiyaparivarapi/get_search_results")
    fun fetchSearchItems(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>
    @POST("ciapi/chovdiyaparivarapi/insert_regi_id")
    fun insert_regi_id(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("ciapi/chovdiyaparivarapi/noti_read_status")
    fun noti_read_status(
            @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("ciapi/chovdiyaparivarapi/add_view")
    fun add_view(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("send_notification/api/noti/list_notification")
    fun get_notification_list(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>

    @POST("send_notification/api/store/get_store_list")
    fun get_store_list(
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/chovdiyaparivarapi/notification_read_all")
    fun notification_read_all(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>



    @POST("ciapi/PuntchAppApi/get_posts_categorywise")
    fun fetchCategoryPost1(
        @Body body: RequestBody
    ): Observable<Response<CommonPaginationResponse>>


    @POST("ciapi/chovdiyaparivarapi/get_live_link")
    fun get_live_link (
        @Body body: RequestBody
    ): Observable<Response<AddLink>>

    @POST("ciapi/chovdiyaparivarapi/get_category_list")
    fun get_all_categories (
        @Body body: RequestBody
    ): Observable<Response<Categorydata>>


    @POST("ciapi/chovdiyaparivarapi/encrypt")
    fun get_encrypt (
        @Body body: RequestBody
    ): Observable<Response<GlobalResponse>>

    @POST("ciapi/chovdiyaparivarapi/encrypt_clip")
    fun encrypt_clip (
        @Body body: RequestBody
    ): Observable<Response<GlobalResponse>>










}