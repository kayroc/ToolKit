package kayroc.android.widget.titlebar.initializer

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import kayroc.android.extension.drawable.ripple
import kayroc.android.extension.findDrawable
import kayroc.android.widget.R

/**
 * 标题栏 黑色初始化
 *
 * @author : kayroc
 * @date   : 2021/1/24
 */
class DarkInitializer : BaseInitializer() {

    override fun getContainerBackground(context: Context): Drawable = ColorDrawable(Color.BLACK)

    override fun getNavView(context: Context): AppCompatImageView {
        val navView = super.getNavView(context)
        navView.ripple()
        return navView
    }

    override fun getNavIcon(context: Context): Drawable? =
        context.findDrawable(R.drawable.ic_nav_back_white)

    override fun getLeftTextColor(context: Context) = Color.WHITE

    override fun getTitleTextColor(context: Context) = Color.WHITE

    override fun getRightTextColor(context: Context): Int = Color.WHITE

}