package kayroc.android.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kayroc.android.kotlin.R

/**
 * Resources 资源相关
 *
 * @author : kayroc
 * @date   : 2021/1/27
 */

val Context.colorPrimary: Int
    get() = TypedValue().apply {
        theme.resolveAttribute(R.attr.colorPrimary, this, true)
    }.data

val Context.colorPrimaryDark: Int
    get() = TypedValue().apply {
        theme.resolveAttribute(R.attr.colorPrimaryDark, this, true)
    }.data

val Context.colorAccent: Int
    get() = TypedValue().apply {
        theme.resolveAttribute(R.attr.colorAccent, this, true)
    }.data



/** 寻找 color 资源 */
fun Context.findColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

/** 寻找 string 资源 */
fun Context.findString(id: Int) = resources.getString(id)

/** 寻找 drawable 资源 */
fun Context.findDrawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

/** 寻找 String[] 资源 */
fun Context.findStringArray(id: Int): Array<String> = resources.getStringArray(id)

/** 寻找 dimens 资源 */
fun Context.findDimens(id: Int) = resources.getDimensionPixelSize(id)



/** 寻找 color 资源 */
fun View.findColor(id: Int) = context.findColor(id)

/** 寻找 string 资源 */
fun View.findString(id: Int) = context.findString(id)

/** 寻找 drawable 资源 */
fun View.findStringArray(id: Int) = context.findStringArray(id)

/** 寻找 String[] 资源 */
fun View.findDrawable(id: Int) = context.findDrawable(id)

/** 寻找 dimens 资源 */
fun View.findDimens(id: Int) = context.findDimens(id)



/** 寻找 color 资源 */
fun Fragment.findColor(id: Int) = context!!.findColor(id)

/** 寻找 string 资源 */
fun Fragment.findString(id: Int) = context!!.findString(id)

/** 寻找 drawable 资源 */
fun Fragment.findStringArray(id: Int) = context!!.findStringArray(id)

/** 寻找 String[] 资源 */
fun Fragment.findDrawable(id: Int) = context!!.findDrawable(id)

/** 寻找 dimens 资源 */
fun Fragment.findDimens(id: Int) = context!!.findDimens(id)



/** 寻找 color 资源 */
fun RecyclerView.ViewHolder.findColor(id: Int) = itemView.findColor(id)

/** 寻找 string 资源 */
fun RecyclerView.ViewHolder.findString(id: Int) = itemView.findString(id)

/** 寻找 drawable 资源 */
fun RecyclerView.ViewHolder.findStringArray(id: Int) = itemView.findStringArray(id)

/** 寻找 String[] 资源 */
fun RecyclerView.ViewHolder.findDrawable(id: Int) = itemView.findDrawable(id)

/** 寻找 dimens 资源 */
fun RecyclerView.ViewHolder.findDimens(id: Int) = itemView.findDimens(id)