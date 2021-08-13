package kayroc.android.widget.titlebar.initializer

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import kayroc.android.extension.colorPrimary
import kayroc.android.extension.dp
import kayroc.android.extension.sp
import kayroc.android.widget.textview.SmartTextView


/**
 * 标题栏基类初始化器
 *
 * @author : kayroc
 * @date   : 2021/1/20
 */
abstract class BaseInitializer {


    // =====================================================================
    // =                           标题容器相关                              =
    // =====================================================================

    /** 获取标题栏容器背景 */
    open fun getContainerBackground(context: Context): Drawable =
        ColorDrawable(context.colorPrimary)

    /** 获取标题容器水平内间距 */
    fun getContainerHorizontalPadding(context: Context): Int = 16.dp.toInt()

    /** 获取容器中标题的垂直内间距，用于撑起标题栏 */
    fun getTitleVerticalPadding(context: Context): Int = 16.5f.dp.toInt()

    /** 获取标题字体 */
    fun getFontFamily(context: Context): String = "sans-serif-medium"

    // =====================================================================
    // =                           导航图标相关                              =
    // =====================================================================

    /** 获取导航视图 */
    open fun getNavView(context: Context): AppCompatImageView {
        val navView = AppCompatImageView(context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        navView.layoutParams = layoutParams
        return navView
    }

    /** 获取导航图标 */
    abstract fun getNavIcon(context: Context): Drawable?

    /** 获取标题导航图标与左标题的间距 */
    fun getNavAndLeftTitleSpacing(context: Context): Int = 10.dp.toInt()

    // =====================================================================
    // =                           左侧标题相关                              =
    // =====================================================================

    /** 获取左侧标题 View */
    open fun getLeftView(context: Context): SmartTextView {
        val textView = SmartTextView(context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        textView.layoutParams = layoutParams
        textView.isFocusable = true
        textView.isSelected = true
        textView.setSingleLine()
        textView.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView.marqueeRepeatLimit = -1
        textView.includeFontPadding = false
        return textView
    }

    /** 获取左侧标题颜色 */
    abstract fun getLeftTextColor(context: Context): Int

    /** 获取左侧标题颜色 */
    open fun getLeftTextSize(context: Context): Int = 20.sp.toInt()

    // =====================================================================
    // =                           中间标题相关                              =
    // =====================================================================

    /** 获取中间标题 View */
    open fun getTitleView(context: Context): SmartTextView {
        val textView = SmartTextView(context)
        val layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        textView.layoutParams = layoutParams
        textView.gravity = Gravity.CENTER
        textView.isFocusable = true
        textView.isSelected = true
        textView.setSingleLine()
        textView.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView.marqueeRepeatLimit = -1
        textView.includeFontPadding = false
        return textView
    }

    /** 获取中间标题颜色 */
    abstract fun getTitleTextColor(context: Context): Int

    /** 获取中间标题颜色 */
    open fun getTitleTextSize(context: Context): Int = 20.sp.toInt()

    // =====================================================================
    // =                           右侧标题相关                              =
    // =====================================================================

    /** 获取右侧标题 View */
    open fun getRightView(context: Context): SmartTextView {
        val textView = SmartTextView(context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        textView.layoutParams = layoutParams
        textView.setSingleLine()
        return textView
    }

    /** 获取右侧标题颜色 */
    abstract fun getRightTextColor(context: Context): Int

    /** 获取右侧标题颜色 */
    open fun getRightTextSize(context: Context): Int = 18.sp.toInt()
}