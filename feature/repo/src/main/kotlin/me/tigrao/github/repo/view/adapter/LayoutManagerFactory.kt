package me.tigrao.github.repo.view.adapter

import android.content.Context
import android.view.Surface
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

private const val SPAN_COUNT = 2

internal class LayoutManagerFactory {

    fun createLayoutManager(context: Context): LinearLayoutManager {
        return if (isDeviceInNormalOrientation(context)) {
            LinearLayoutManager(context)
        } else {
            GridLayoutManager(context, SPAN_COUNT)
        }
    }

    private fun isDeviceInNormalOrientation(context: Context) =
        getDisplayRotation(context) == Surface.ROTATION_0

    private fun getDisplayRotation(context: Context): Int {
        val windowManager =
            context.getSystemService(AppCompatActivity.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay

        return display.rotation
    }
}
