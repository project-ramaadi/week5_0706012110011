package com.pakevankeren.coldstar.utils

import com.pakevankeren.coldstar.config.RetrofitConfig
import java.net.URLEncoder

object GlideUtils {

    fun companyImage(img: String?, name: String): String {
        img?.let {
            return RetrofitConfig.IMG_BASE_URL + img
        } ?: run {
            return "https://ui-avatars.com/api/?length=3&size=1080&background=1e293b&color=f3f4f6&name=${URLEncoder.encode(name, "utf-8")}"
        }
    }
}