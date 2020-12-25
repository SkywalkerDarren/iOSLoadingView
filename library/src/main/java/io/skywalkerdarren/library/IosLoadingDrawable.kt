package io.skywalkerdarren.library

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.withRotation
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * @author darren
 * @date 20-12-7
 */
class IosLoadingDrawable @JvmOverloads constructor(
    private val context: Context,
    @ColorInt private val paintColor: Int = Color.WHITE,
    @Px private val paintWidth: Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        2f,
        context.resources.displayMetrics
    ),
    private val lineCount: Int = 10,
    private var rotateDuration: Long = 1 * 1000L
) : Drawable() {

    /**
     * 线段的相对长度
     */
    private val lineLength = 0.5f

    private val paints = mutableListOf<Paint>()

    var percents = 1f
        set(value) {
            field = value
            resetPaints()
            if (value == 1f) {
                return
            }
            rotateDegree = 180f - 360f / lineCount
            val lineMax = (lineCount * value).toInt()
            Log.d("ASD", "lineMax: $lineMax")

            if (lineMax == 0) {
                paints.forEachIndexed { index, paint ->
                    if (index == paints.lastIndex) {
                        paint.alpha = (255 * value * lineCount).toInt().also {
                            Log.d("ASD", "$it")
                        }
                    } else {
                        paint.alpha = 0
                    }
                }
            } else {
                paints.forEachIndexed { index, paint ->
                    val i = paints.lastIndex - index
                    if (i <= lineMax) {
                        paint.alpha = 255
                    } else {
                        paint.alpha = 0
                    }
                }
            }
            invalidateSelf()
        }

    private val paths = mutableListOf<Path>()

    private var rotateDegree = 180f

    private val rotateAnimation = ObjectAnimator.ofInt(0, lineCount).apply {
        duration = rotateDuration
        repeatCount = INFINITE
        repeatMode = RESTART
        interpolator = LinearInterpolator()
        val baseDegree = 360f / lineCount
        addUpdateListener {
            rotateDegree = baseDegree * it.animatedValue as Int
            invalidateSelf()
        }
    }

    init {
        for (i in 0 until lineCount) {
            paints.add(Paint().apply {
                color = paintColor
                alpha = 255 - 255 / lineCount * i
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeWidth = paintWidth
            })
            paths.add(Path())
        }
    }

    private fun resetPaints() {
        paints.forEachIndexed { index, paint ->
            paint.reset()
            paint.color = paintColor
            paint.isAntiAlias = true
            paint.style = Paint.Style.STROKE
            paint.strokeCap = Paint.Cap.ROUND
            paint.strokeWidth = paintWidth
            paint.alpha = 255 - 255 / lineCount * index
        }
    }


    fun start() {
        percents = 1f
        rotateDegree = 180f
        rotateAnimation.start()
    }

    fun pause() {
        rotateAnimation.pause()
    }

    override fun draw(canvas: Canvas) {
        val w = bounds.width().toFloat()
        val h = bounds.height().toFloat()
        val d = min(w, h)
        val r = d / 2
        val realLength = lineLength * r - paintWidth / 2
        canvas.withRotation(rotateDegree, w / 2, h / 2) {
            val degrees = 2 * PI / lineCount
            val startRelativeLength = r - lineLength * r
            for (i in 0 until lineCount) {
                paths[i].reset()
                // 1.移到中心点
                paths[i].moveTo(w / 2, h / 2)
                // 2.移到起点
                val x = sin(degrees * i)
                val y = cos(degrees * i)
                paths[i].rMoveTo(
                    (x * startRelativeLength).toFloat(),
                    (y * startRelativeLength).toFloat()
                )
                // 3.划线
                paths[i].rLineTo(
                    (x * realLength).toFloat(),
                    (y * realLength).toFloat()
                )

                canvas.drawPath(paths[i], paints[i])
            }
        }
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return if (alpha == 0) PixelFormat.TRANSPARENT else PixelFormat.TRANSLUCENT
    }

}