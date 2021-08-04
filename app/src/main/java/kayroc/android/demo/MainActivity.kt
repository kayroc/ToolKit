package kayroc.android.demo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kayroc.android.utils.LogUtils
import kayroc.android.widget.shape.view.ShapeTextView
import kayroc.android.widget.shape.view.ShapeView

class MainActivity : AppCompatActivity() {

    private val mShapeView: ShapeView by lazy { findViewById(R.id.mShapeView) }
    private val mShapeTextView: ShapeTextView by lazy { findViewById(R.id.mShapeTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mShapeView.apply {
            borderNormalColor = Color.parseColor("#938421")
            borderNormalWidth = 30
            borderPressedColor = Color.parseColor("#FFFFFF")
            borderPressedWidth = 40
            applyCustomAttr()
        }
        mShapeView.setOnClickListener { LogUtils.d("点击了") }

        mShapeTextView.setOnClickListener { LogUtils.d("文本点击了") }
    }
}