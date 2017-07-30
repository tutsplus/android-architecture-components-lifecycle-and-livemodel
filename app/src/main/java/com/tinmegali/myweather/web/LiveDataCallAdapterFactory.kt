package com.tinmegali.myweather.web

import android.arch.lifecycle.LiveData
import com.tinmegali.myweather.R

import com.tinmegali.myweather.models.ApiResponse
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

import retrofit2.CallAdapter
import retrofit2.Retrofit

/**
 * com.tinmegali.myweather.web | MyWeatherApp
 * __________________________________
 * Created by tinmegali
 * 30/07/17

 * @see [tinmegali.com](http://www.tinmegali.com)

 * @see [github](http://github.com/tinmegali)
 * ___________________________________
 */

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<Any, Any>? {

        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
            return LiveDataCallAdapter<Any>(bodyType) as CallAdapter<Any, Any>
    }
}
