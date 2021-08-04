package kayroc.android.widget.textview

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import kayroc.android.widget.shape.view.ShapeTextView

/**
 * 根据有无内容智能显示的 TextView
 *
 * @author : kayroc
 * @date   : 2021/1/20
 */
@Suppress("unused")
class SmartTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeTextView(context, attributeSet, defStyleAttr) {

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        // 判断当前有没有设置文本达到自动隐藏和显示的效果
        if (TextUtils.isEmpty(text) && !isGone) {
            visibility = View.GONE
            return
        }

        if (!isVisible) {
            visibility = View.VISIBLE
        }
    }
}