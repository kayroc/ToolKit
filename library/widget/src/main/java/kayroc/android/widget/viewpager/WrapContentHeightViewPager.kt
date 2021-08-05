package kayroc.android.widget.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

/**
 * 每页高度自适应的 ViewPager
 * @author kayroc
 */
class WrapContentHeightViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : NestedViewPager(context, attributeSet), ViewPager.OnPageChangeListener {

    private var childViewHeight = 0

    init {
        addOnPageChangeListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childView = getChildAt(currentItem)
        var newHeightMeasureSpec = heightMeasureSpec
        // 可能没有子 View
        if (childView != null) {
            childView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            // 得到父元素对自身设置的高
            childViewHeight = childView.measuredHeight
            // UNSPECIFIED(未指定),父元素不对自元素施加任何束缚，子元素可以得到任意想要的大小
            // EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
            // AT_MOST(至多)，子元素至多达到指定大小的值。
            newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childViewHeight, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    private fun resetHeight(position: Int) {
        if (childCount > position) {
            var layoutParams = layoutParams
            if (layoutParams == null) {
                layoutParams = LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, childViewHeight)
            } else {
                layoutParams.height = childViewHeight
            }
            setLayoutParams(layoutParams)
        }
    }

    override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) {
        super.onPageScrolled(position, offset, offsetPixels)
    }

    override fun onPageSelected(position: Int) {
        resetHeight(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

}