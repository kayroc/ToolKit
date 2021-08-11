package kayroc.android.widget.titlebar

import android.view.View

/**
 * TitleBar 点击事件监听
 *
 * @author : kayroc
 * @date   : 2021/1/24
 */
interface TitleBarListener {

    /** 导航图标点击 */
    fun onNavClick(view: View) { }

    /** 左侧标题点击 */
    fun onLeftClick(view: View) { }

    /** 中间标题点击 */
    fun onTitleClick(view: View) { }

    /** 右侧标题点击 */
    fun onRightClick(view: View) { }

}