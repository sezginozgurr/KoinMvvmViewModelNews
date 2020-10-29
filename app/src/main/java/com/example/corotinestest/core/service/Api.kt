package com.example.corotinestest.core.service


import com.brkcnszgn.networkresponse.NetworkResponse
import com.example.corotinestest.core.model.*
import com.example.corotinestest.core.model.trnews.TurkeyNewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("users")
    suspend fun getUser(): NetworkResponse<UserResponseModel, ErrorResponse>

    @GET("users/1/albums")
    suspend fun getUserAlbum(): NetworkResponse<AlbumsResponseModel, ErrorResponse>

    @GET("albums/1/photos")
    suspend fun getUserPhotos(): NetworkResponse<PhotosResponseModel, ErrorResponse>

    @GET("posts/1/comments")
    suspend fun getUserComment(): NetworkResponse<CommentResponseModel, ErrorResponse>

    @GET("v2/top-headlines?country=tr")

    suspend fun getHomeNews(@Query("apiKey") apiKey: String): NetworkResponse<TurkeyNewsModel, ErrorResponse>

}

//    @POST("kidsvid/auth/authenticate")
//    suspend fun getUser(
//        @Body requestTokenModel: RequestTokenModel
//    ): NetworkResponse<LoginModel, ErrorBody>
//
//    @POST("kidsvid/users")
//    suspend fun createUser(
//        @Body createUserRequestModel: CreateUserRequestModel
//    ): NetworkResponse<CreateUserModel, ErrorBody>
//
//    @POST("kidsvid/users")
//    suspend fun aa(
//        @Query("filter[parent]") filter: String,
//        @Body createChildRequestModel: CreateChildRequestModel
//    ): NetworkResponse<CreateChildResponseModel, ErrorBody>
//
//    @POST("kidsvid/users")
//    suspend fun createChild(
//        @Body createChildRequestModel: CreateChildRequestModel
//    ): NetworkResponse<CreateChildResponseModel, ErrorBody>
//
//    @PATCH("kidsvid/users/{id}")
//    suspend fun updateTeacherForm(
//        @Path("id") id: Int,
//        @Body updateTeacherForm: UpdateTeacherRequestModel
//    ): NetworkResponse<UserUpdate, ErrorBody>
//
//    @GET("kidsvid/users")
//    suspend fun getChild(
//
//        @Query("filter[parent][eq]") filter: Int,
//    ): NetworkResponse<GetChildModel, ErrorBody>
//
//    @GET("kidsvid/items/categories")
//    suspend fun getVideoCategory(
//        @Query("filter[parent_category][null]") filter: String,
//    ): NetworkResponse<GetVideoCategoryModel, ErrorBody>
//
//    @GET("kidsvid/items/interests")
//    suspend fun getInterest(): NetworkResponse<InterestRequestModel, ErrorBody>
//
//
//    @Multipart
//    @POST("kidsvid/files")
//    fun upload(
//        @Part image: MultipartBody.Part,
//        @Query("folder") id: Int
//    ): Call<UploadResponse>
//
//    @Multipart
//    @POST("kidsvid/files")
//    suspend fun uploadFile(
//        @Part image: MultipartBody.Part,
//        @Query("folder") id: Int
//    ): NetworkResponse<UploadResponse, ErrorBody>
//
//
//    @GET("kidsvid/items/categories?filter[type][eq]=egitim&filter[parent_category][nnull]=\"\"&fields=*.*,icon.*,parent_category.*.*")
//    suspend fun getCategories(): NetworkResponse<CategoriesModel, ErrorBody>