package kayroc.android.widget.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * 禁止滑动的 ViewPager
 * @author kayroc
 */
class NoScrollViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ViewPager(context, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 不拦截这个事件
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // 不处理这个事件
        return false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        // 不响应按键事件
        return false
    }

    override fun setCurrentItem(item: Int) {
        // 只有相邻页才会有动画
        super.setCurrentItem(item, abs(currentItem - item) == 1)
    }
}