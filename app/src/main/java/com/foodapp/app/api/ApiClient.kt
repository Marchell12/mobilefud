package com.foodapp.app.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val BASE_URL="http://request.my.id/api/"
    val PrivicyPolicy="http://request.my.id/privacypolicy"
    val termscondition="http://request.my.id/termscondition"
    val MapKey="AIzaSyD5wHSQxxCTE-BxYjUUr5H2zQ8fddGGjjo"
    val Stripe="pk_test_51JEB35ALIIAWddRVwLNBNIqfs9vS03Ba8JAdQ8FbE94969WvLV7JjaaHqBr1wD7b58bLAKh5l4kTZ9bYvlnuLYh900csNgpyqx"

    var TIMEOUT: Long = 60 * 2 * 1.toLong()
    val getClient: ApiInterface get() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient=OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}