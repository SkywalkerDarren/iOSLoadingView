package io.skywalkerdarren.iosloadingview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import io.skywalkerdarren.library.IosLoadingDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.iv1).setImageDrawable(IosLoadingDrawable(this).apply { start() })
        findViewById<ImageView>(R.id.iv2).setImageDrawable(IosLoadingDrawable(
                this,
                Color.YELLOW,
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        5f,
                        resources.displayMetrics
                ),
                6,
                500
        ).apply { start() })
        findViewById<ImageView>(R.id.iv3).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.01f
        })
        findViewById<ImageView>(R.id.iv4).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.02f
        })
        findViewById<ImageView>(R.id.iv5).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.03f
        })
        findViewById<ImageView>(R.id.iv6).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.04f
        })
        findViewById<ImageView>(R.id.iv7).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.06f
        })
        findViewById<ImageView>(R.id.iv8).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.07f
        })
        findViewById<ImageView>(R.id.iv9).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.08f
        })
        findViewById<ImageView>(R.id.iv10).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.08f
        })
        findViewById<ImageView>(R.id.iv11).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.2f
        })
        findViewById<ImageView>(R.id.iv12).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.4f
        })
        findViewById<ImageView>(R.id.iv13).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.6f
        })
        findViewById<ImageView>(R.id.iv14).setImageDrawable(IosLoadingDrawable(this).apply {
            this.percents = 0.8f
        })
    }
}