package kayroc.android.widget.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * 修复 PhotoView 与 ViewPager 出现的问题
 * 查看大图，缩小时 java.lang.IllegalArgumentException: pointerIndex out of range 的异常
 * @author kayroc
 */
class PhotoViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : NestedViewPager(context, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return try {
            super.onInterceptTouchEvent(ev)
        } catch (e: Exception) {
            false
        }
    }

}