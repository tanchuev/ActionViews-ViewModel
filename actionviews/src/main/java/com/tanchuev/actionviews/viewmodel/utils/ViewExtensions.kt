package com.tanchuev.actionviews.viewmodel.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.IdRes
import android.support.annotation.IntDef
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.content.res.AppCompatResources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tanchuev.actionviews.viewmodel.R

fun Context.showDialogMessage(message: String, title: String) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setTitle(title)
        .setPositiveButton(R.string.OK) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun Context.getAppCompatDrawable(attributeArray: TypedArray, styleIndex: Int, defaultValue: Int = -1): Drawable? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        attributeArray.getDrawable(styleIndex)
    } else {
        val resId = attributeArray.getResourceId(styleIndex, defaultValue)
        if (resId == -1) return null
        AppCompatResources.getDrawable(this, resId)
    }
}

fun Context.getAppCompatDrawable(drawableId: Int): Drawable? {
    return if (drawableId == -1) null else AppCompatResources.getDrawable(this, drawableId)
}

fun Context.showToastMessage(@StringRes message: Int, duration: Int) {
    showToastMessage(getString(message), duration)
}

fun Context.showToastMessage(message: String, duration: Int) {
    if (message.isEmpty()) {
        return
    }
    Toast.makeText(applicationContext, message, duration).show()
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

fun View.removeOnGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) =
    viewTreeObserver.removeOnGlobalLayoutListener(listener)

fun pxToDp(px: Float): Float {
    val densityDpi = Resources.getSystem()
        .displayMetrics.densityDpi.toFloat()
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Float): Int {
    val density = Resources.getSystem()
        .displayMetrics.density
    return Math.round(dp * density)
}

fun spToPx(sp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().displayMetrics).toInt()
}

val displayWidth: Int
    get() = Resources.getSystem().displayMetrics.widthPixels

val displayHeight: Int
    get() = Resources.getSystem().displayMetrics.heightPixels

@IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
@Retention(AnnotationRetention.SOURCE)
annotation class Visibility

fun setVisibility(@Visibility visibility: Int, vararg views: View) {
    views.filter { it.visibility != visibility }
        .forEach { it.visibility = visibility }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.setImage(url: String?) {
    Glide.with(context)
            .load(url)
            //.error(getDrawable(itemView.context, R.drawable.d_placeholder))
            .into(this)
}

fun ImageView.setImage(@IdRes imageId: Int?) {
    Glide.with(context)
            .load(imageId)
            //.error(getDrawable(itemView.context, R.drawable.d_placeholder))
            .into(this)
}

fun <V : View> Activity.findViewByIdNullable(@IdRes id: Int): V? {
    return findViewById(id)
}

fun <V : View> Fragment.findViewByIdNullable(@IdRes id: Int): V? {
    return requireActivity().findViewById(id)
}

fun <V : View> Fragment.findViewById(@IdRes id: Int): V {
    return requireActivity().findViewById(id)
}

// region FOR CURRENT APP

// endregion FOR CURRENT APP