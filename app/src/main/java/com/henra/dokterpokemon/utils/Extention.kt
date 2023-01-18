package com.henra.dokterpokemon.utils

import android.content.res.Resources
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.henra.dokterpokemon.R
import com.henra.dokterpokemon.data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

//fun StringBuilder.toStringOrNull(fieldName: String): String? {
//    return if (isNotEmpty()) {
//        insert(0, "$fieldName ")
//        toString()
//    } else null
//}
//
//fun MMKV.decodeStr(key: String, default: String = ""): String {
//    return decodeString(key, default) ?: ""
//}

val String.lastPath get() = Uri.parse(this).lastPathSegment
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

inline fun <R> fetchApiFlow(crossinline callApi: suspend () -> Response<R>): Flow<DataState<R>> {
    return flow {
        emit(fetchApi(callApi = callApi))
    }
}

suspend inline fun <T> fetchApi(crossinline callApi: suspend () -> Response<T>): DataState<T> {
    return try {
        val response = callApi()
        response.takeIf { res -> res.isSuccessful }?.body()?.let { data ->
            DataState.Success(data)
        } ?: DataState.Failed(Exception("Not Found"))
    } catch (e: Exception) {
        DataState.Failed(e)
    }
}

inline fun <T : ViewBinding> ViewGroup.viewBinding(
    crossinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false
) =
    bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent)

fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(200)
        .placeholder(R.drawable.placeholder)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)