package kayroc.android.widget.titlebar

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import kayroc.android.extension.*
import kayroc.android.widget.R
import kayroc.android.widget.textview.SmartTextView
import kayroc.android.widget.titlebar.initializer.BaseInitializer
import kayroc.android.widget.titlebar.initializer.BlackInitializer
import kayroc.android.widget.titlebar.initializer.DefaultInitializer
import kayroc.android.widget.titlebar.initializer.WhiteInitializer

/**
 * 标题栏组件
 *
 * 容器水平间距 : containerHorizontalPadding = 16dp
 * 标题纵向间距 : titleVerticalPadding = 16.5dp
 * 默认高度 : titleVerticalPadding + mTitleView.height(mLeftView.height) = 147 = 56dp
 * 默认背景 : containerBackground = colorPrimary
 * 默认字体 : fontFamily = "sans-serif-medium"
 * 导航图标与左侧标题间距 : navAndLeftTitleSpacing = 22dp
 * 左侧标题文字大小 : leftTextSize = 20sp
 * 左侧标题文字颜色 : leftTextColor = white
 * 中间标题文字大小 : titleTextSize = 20sp
 * 中间标题文字颜色 : titleTextColor = white
 * 右侧标题文字大小 : rightTextSize = 18sp
 * 右侧标题文字颜色 : rightTextColor = white
 *
 * @author : kayroc
 * @date   : 2021/1/20
 */
class TitleBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    companion object {
        var sInitializer: BaseInitializer = DefaultInitializer()

        fun setDefaultInitializer(initializer: BaseInitializer) {
            sInitializer = initializer
        }
    }

    private var mInitializer: BaseInitializer = sInitializer
    private lateinit var mLayoutContainer: LinearLayout
    private lateinit var mNavView: AppCompatImageView
    private lateinit var mLeftView: SmartTextView
    private lateinit var mTitleView: SmartTextView
    private lateinit var mRightView: SmartTextView

    /** 中间标题文本颜色 */
    var containerBackground = mInitializer.getContainerBackground(context)
    /** 容器横向内间距 */
    var containerHorizontalPadding = mInitializer.getContainerHorizontalPadding(context)
    /** 容器中标题的垂直内间距，用于撑起标题栏 */
    var titleVerticalPadding = mInitializer.getTitleVerticalPadding(context)

    /** 导航图标 */
    var navIcon: Drawable? = mInitializer.getNavIcon(context)
    /** 导航图标与左标题的间距 */
    var navAndLeftTitleSpacing = mInitializer.getNavAndLeftTitleSpacing(context)
    /** 显示导航图标 */
    var showNavIcon: Boolean = navIcon != null

    /** 左侧标题字体 */
    var fontFamily: String = mInitializer.getFontFamily(context)
    /** 左侧标题文本 */
    var leftText: String? = ""
    /** 左侧标题文本颜色 */
    var leftTextColor = mInitializer.getLeftTextColor(context)
    /** 左侧标题文字大小 */
    var leftTextSize = mInitializer.getLeftTextSize(context)

    /** 中间标题文本 */
    var titleText: String? = ""
    /** 中间标题文本颜色 */
    var titleTextColor = mInitializer.getTitleTextColor(context)
    /** 中间标题文字大小 */
    var titleTextSize = mInitializer.getTitleTextSize(context)

    /** 右侧标题文本 */
    var rightText: String? = ""
    /** 右侧标题文本颜色 */
    var rightTextColor = mInitializer.getRightTextColor(context)
    /** 右侧标题文字大小 */
    var rightTextSize = mInitializer.getRightTextSize(context)
    /** 右侧标题图标 */
    var rightIcon: Drawable? = null

    private var mListener: TitleBarListener? = null

    fun setTitleBarListener(
        titleBarListener: TitleBarListener
    ): TitleBar {
        this.mListener = titleBarListener
        return this
    }

    init {
        initView()
        initAttrs(context, attributeSet)
        applyAttrs()
    }

    fun applyAttrs() {
        applyContainerLayout()
        applyLeftView()
        applyTitleView()
        applyRightView()
        applyNavView()
        resetTitlePadding()
    }

    private fun resetTitlePadding() {
        mLayoutContainer.post {
            // 更新中间标题的内边距，避免向左或者向右偏移
            val leftSize =
                if (mLeftView.isVisible) mNavView.width + mLeftView.width + navAndLeftTitleSpacing
                else mNavView.width + mLeftView.width
            val rightSize = mRightView.width
            if (leftSize != rightSize) {
                if (leftSize > rightSize) {
                    mTitleView.padding(rightPadding = leftSize - rightSize)
                } else {
                    mTitleView.padding(leftPadding = rightSize - leftSize)
                }
            }
        }
    }

    private fun applyRightView() {
        mRightView.text = rightText
        mRightView.setTextColor(rightTextColor)
        mRightView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize.toFloat())
        if (rightIcon != null) {
            // 存在图标就别显示文字了
            mRightView.text = ""
            mRightView.setDrawableRight(rightIcon)
        }
        mRightView.typeface = Typeface.create(fontFamily, Typeface.NORMAL)
    }

    private fun applyTitleView() {
        mTitleView.text = titleText
        mTitleView.setTextColor(titleTextColor)
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
        mTitleView.typeface = Typeface.create(fontFamily, Typeface.NORMAL)
    }

    private fun applyLeftView() {
        mLeftView.text = leftText
        mLeftView.setTextColor(leftTextColor)
        mLeftView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize.toFloat())
        mLeftView.typeface = Typeface.create(fontFamily, Typeface.NORMAL)

        if (mNavView.isVisible) {
            mNavView.rightMargin = navAndLeftTitleSpacing
        }
    }

    private fun applyNavView() {
        mNavView.gone()
        if (showNavIcon) {
            mNavView.visible()
            mNavView.setImageDrawable(navIcon)
        }

        if (mLeftView.isVisible) {
            mNavView.rightMargin = navAndLeftTitleSpacing
        }
    }

    private fun applyContainerLayout() {
        mLayoutContainer.setPadding(containerHorizontalPadding, 0, containerHorizontalPadding, 0)
        if (background == null) {
            background = containerBackground
        }
    }

    private fun initAttrs(context: Context, attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBar)

        mInitializer = when (typedArray.getInt(R.styleable.TitleBar_barStyle, 0)) {
            1 -> WhiteInitializer()
            2 -> BlackInitializer()
            else -> DefaultInitializer()
        }

        containerHorizontalPadding = typedArray.getDimensionPixelSize(R.styleable.TitleBar_containerHorizontalPadding, containerHorizontalPadding)
        titleVerticalPadding = typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleVerticalPadding, titleVerticalPadding)

        if (typedArray.hasValue(R.styleable.TitleBar_navIcon)) {
            navIcon = typedArray.getDrawable(R.styleable.TitleBar_navIcon)
        }
        navAndLeftTitleSpacing = typedArray.getDimensionPixelSize(R.styleable.TitleBar_navAndLeftTitleSpacing, navAndLeftTitleSpacing)
        showNavIcon = typedArray.getBoolean(R.styleable.TitleBar_showNavIcon, navIcon != null)

        fontFamily = typedArray.getString(R.styleable.TitleBar_fontFamily) ?: fontFamily

        leftText = typedArray.getString(R.styleable.TitleBar_leftText) ?: ""
        leftTextColor = typedArray.getColor(R.styleable.TitleBar_leftTextColor, leftTextColor)
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_leftTextSize, leftTextSize)

        titleText = typedArray.getString(R.styleable.TitleBar_titleText) ?: ""
        titleTextColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor, titleTextColor)
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleTextSize, titleTextSize)

        rightText = typedArray.getString(R.styleable.TitleBar_rightText) ?: ""
        rightTextColor = typedArray.getColor(R.styleable.TitleBar_rightTextColor, rightTextColor)
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_rightTextSize, rightTextSize)
        rightIcon = typedArray.getDrawable(R.styleable.TitleBar_rightIcon)

        typedArray.recycle()
    }

    private fun initView() {
        mLayoutContainer = LinearLayout(context)
        mLayoutContainer.orientation = LinearLayout.HORIZONTAL
        mLayoutContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )

        mNavView = mInitializer.getNavView(context)
        mLeftView = mInitializer.getLeftView(context)
        mTitleView = mInitializer.getTitleView(context)
        mRightView = mInitializer.getRightView(context)

        mLayoutContainer.addView(mNavView)
        mLayoutContainer.addView(mLeftView)
        mLayoutContainer.addView(mTitleView)
        mLayoutContainer.addView(mRightView)

        addView(mLayoutContainer, 0)

        mNavView.click { mListener?.onNavClick(it) }
        mLeftView.click { mListener?.onLeftClick(it) }
        mTitleView.click { mListener?.onTitleClick(it) }
        mRightView.click { mListener?.onRightClick(it) }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        if (params != null) {
            if (params.width == LayoutParams.WRAP_CONTENT) {
                // 如果当前宽度是自适应则转换成占满父布局
                params.width = LayoutParams.MATCH_PARENT
            }

            var verticalPadding = 0
            // 如果当前高度是自适应则设置默认的内间距
            if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                verticalPadding = titleVerticalPadding
            }

            mTitleView.setPadding(0, verticalPadding, 0, verticalPadding)
            mLeftView.setPadding(0, verticalPadding, 0, verticalPadding)
        }
        super.setLayoutParams(params)
    }


}