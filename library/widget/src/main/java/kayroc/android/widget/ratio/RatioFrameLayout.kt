package kayroc.android.widget.ratio

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import kayroc.android.widget.R
import kayroc.android.widget.shape.layout.ShapeFrameLayout

/**
 * 可设置宽高比的 FrameLayout
 * @author : kayroc
 */
open class RatioFrameLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeFrameLayout(context, attributeSet, defStyleAttr) {

    private var mWidthRatio = 0f
    private var mHeightRatio = 0f

    init {
        initAttr(context, attributeSet)
    }

    private fun initAttr(context: Context, attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RatioFrameLayout)
        val ratio = typedArray.getString(R.styleable.RatioFrameLayout_ratio)
        typedArray.recycle()

        // 计算宽高比例
        if (!TextUtils.isEmpty(ratio)) {
            val split: Array<String> = ratio!!.split(":").toTypedArray()
            when (split.size) {
                1 -> {
                    mWidthRatio = split[0].toFloat()
                    mHeightRatio = 1f
                }
                2 -> {
                    mWidthRatio = split[0].toFloat()
                    mHeightRatio = split[1].toFloat()
                }
                else -> throw IllegalArgumentException("are you ok?")
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 新的测量值
        var newWidthMeasureSpec = widthMeasureSpec
        var newHeightMeasureSpec = heightMeasureSpec

        if (mWidthRatio != 0f && mHeightRatio != 0f) {
            val ratio = getRatio()

            // 获取宽度的模式和尺寸
            val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
            val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

            // 获取高度的模式和尺寸
            val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
            val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

            // 一般情况下 LayoutParams.WRAP_CONTENT 对应着 MeasureSpec.AT_MOST（自适应），但是由于我们在代码中强制修改了测量模式为 MeasureSpec.EXACTLY（固定值）
            // 这样会有可能重新触发一次 onMeasure 方法，这个时候传入测量模式的就不是 MeasureSpec.AT_MOST（自适应） 模式，而是 MeasureSpec.EXACTLY（固定值）模式
            // 所以我们要进行双重判断，首先判断 LayoutParams，再判断测量模式，这样就能避免因为修改了测量模式触发对宽高的重新计算，最终导致计算结果和上次计算的不同
            if (layoutParams.width != LayoutParams.WRAP_CONTENT && layoutParams.height != LayoutParams.WRAP_CONTENT
                && widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
                // 如果当前宽度和高度都是写死的
                if (widthSpecSize / ratio <= heightSpecSize) {
                    // 如果宽度经过比例换算不超过原有的高度
                    newHeightMeasureSpec = MeasureSpec.makeMeasureSpec((widthSpecSize / ratio).toInt(), MeasureSpec.EXACTLY)
                } else if (heightSpecSize * ratio <= widthSpecSize) {
                    // 如果高度经过比例换算不超过原有的宽度
                    newWidthMeasureSpec = MeasureSpec.makeMeasureSpec((heightSpecSize * ratio).toInt(), MeasureSpec.EXACTLY)
                }
            } else if (layoutParams.width != LayoutParams.WRAP_CONTENT && widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode != MeasureSpec.EXACTLY) {
                // 如果当前宽度是写死的，但是高度不写死
                newHeightMeasureSpec = MeasureSpec.makeMeasureSpec((widthSpecSize / ratio).toInt(), MeasureSpec.EXACTLY)
            } else if (layoutParams.height != LayoutParams.WRAP_CONTENT && heightSpecMode == MeasureSpec.EXACTLY && widthSpecMode != MeasureSpec.EXACTLY) {
                // 如果当前高度是写死的，但是宽度不写死
                newWidthMeasureSpec = MeasureSpec.makeMeasureSpec((heightSpecSize * ratio).toInt(), MeasureSpec.EXACTLY)
            }
        }
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }

    /**
     * 设置宽高比
     * @param widthRatio Float 宽度
     * @param heightRatio Float 高度
     */
    open fun setRatio(widthRatio: Float, heightRatio: Float) {
        mWidthRatio = widthRatio
        mHeightRatio = heightRatio
        invalidate()
    }

    /**
     * 获取宽高比()
     * @return Float 宽高比
     */
    open fun getRatio(): Float = mWidthRatio / mHeightRatio

}