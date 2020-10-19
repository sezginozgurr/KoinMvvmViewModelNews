package com.example.corotinestest.core.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.corotinestest.R


object Util {
    fun loadImage(
        view: ImageView, url: String?, progressDrawable: CircularProgressDrawable?
    ) {
        val options: RequestOptions = RequestOptions()
            .placeholder(progressDrawable)
            .encodeQuality(70)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(view.context)
            .setDefaultRequestOptions(options)
            .asBitmap()

            .apply(RequestOptions.centerCropTransform())
            .load(url)
            .into(view)
    }

    fun getProgressDrawable(context: Context?): CircularProgressDrawable {
        val progressDrawable = CircularProgressDrawable(context!!)
        progressDrawable.strokeWidth = 10f
        progressDrawable.centerRadius = 50f
        progressDrawable.start()
        return progressDrawable
    }


    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }


    fun <T> Context.extStartActivity(className: Class<T>, bundle: Bundle?) {
        val intent = Intent(this, className).putExtra("bundle", bundle)
        startActivity(intent)
    }

    fun <T> Context.extStartActivity(className: Class<T>?) {
        val intent = Intent(this, className)
        startActivity(intent)
    }

//    open fun create(list: List<UserModel>?): HashMap<String, DropdownModel>? {
//        val map: HashMap<String, DropdownModel> = HashMap<String, DropdownModel>()
//        if (list != null && list.size > 0) {
//            map["0"] = DropdownModel("", "Seçiniz")
//            for (i in list.indices) {
//                val item: UserModel = list[i]
//                map[(i + 1).toString()] = DropdownModel(item, item.surname)
//            }
//        }
//        return map
//    }

//    open fun convertToErrorBody(responseBody: String): ErrorBody? =
//        Gson().fromJson(responseBody, ErrorBody::class.java)
//
//    open fun <T> convertToErrorBody(responseBody: ResponseBody): ErrorBody? {
//        return try {
//            responseBody.source().let {
//                val gson = Gson()
//                val type = object : TypeToken<ErrorBody>() {}.type
//                return gson.fromJson<ErrorBody>(responseBody.charStream(), type)
//            }
//        } catch (e: java.lang.Exception) {
//            null
//        }
//
//
//    }


}