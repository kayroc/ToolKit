package kayroc.android.widget.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kayroc.android.widget.R
import kayroc.android.widget.shape.view.ShapeEditText

/**
 * 带清除图标的 EditText
 * @author kayroc
 */
class ClearEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : ShapeEditText(context, attributeSet, defStyleAttr),
    View.OnFocusChangeListener, TextWatcher {

    private var clearDrawable: Drawable? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ClearEditText)
        clearDrawable = typedArray.getDrawable(R.styleable.ClearEditText_clearDrawable)
        typedArray.recycle()

        if (clearDrawable == null) {
            clearDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_input_clear)!!)
        }
        clearDrawable!!.setBounds(0, 0, clearDrawable!!.intrinsicWidth, clearDrawable!!.intrinsicHeight)


        setClearDrawableVisible(false)
        onFocusChangeListener = this
        addTextChangedListener(this)
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在
     * EditText的宽度 - 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距
     * 之间我们就算点击了图标，竖直方向就没有考虑
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_UP) {
            if (compoundDrawables[2] != null) {
                val touchable = event.x > width - totalPaddingRight && event.x < width - paddingRight
                if (touchable) {
                    this.setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 设置清除图标的可见性
     * @param visible Boolean
     */
    private fun setClearDrawableVisible(visible: Boolean) {
        if (clearDrawable?.isVisible == visible) {
            return
        }

        clearDrawable?.setVisible(visible, false)
        setCompoundDrawablesRelative(
            compoundDrawablesRelative[0],
            compoundDrawablesRelative[1],
            if (visible) clearDrawable else null,
            compoundDrawablesRelative[3]
        )
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        // 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
        if (hasFocus && text != null) {
            setClearDrawableVisible(text!!.isNotEmpty())
        } else {
            setClearDrawableVisible(false)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    // 当输入框里面内容发生变化的时候回调的方法
    override fun onTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
        if (isFocused) {
            setClearDrawableVisible(text!!.isNotEmpty())
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

}