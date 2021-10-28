package com.xmly.learnscreendensity

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","onCreate");
        Log.d("MainActivity", "原始widthPixels=${resources.displayMetrics.widthPixels}")
        Log.d("MainActivity", "原始heightPixels=${resources.displayMetrics.heightPixels}")
        Log.d("MainActivity", "原始density=${resources.displayMetrics.density}")
        Log.d("MainActivity", "原始densityDpi=${resources.displayMetrics.densityDpi}")

        val designPx = 1440 //设计图上的尺寸（如果宽是1440，高是3060，那么取最小值）
        val designDpi = 440 //设计图上的dpi
        val designDensity = 3.75f //设计图上的dpi除以160得出的值

        resources.displayMetrics.density = min(resources.displayMetrics.widthPixels,resources.displayMetrics.heightPixels) /(designPx/designDensity)
        resources.displayMetrics.densityDpi = (resources.displayMetrics.density * 160).toInt()
        resources.displayMetrics.scaledDensity = resources.displayMetrics.density

        application.resources.displayMetrics.density = resources.displayMetrics.density
        application.resources.displayMetrics.densityDpi = resources.displayMetrics.densityDpi
        application.resources.displayMetrics.scaledDensity = resources.displayMetrics.scaledDensity


        Log.d("MainActivity", "改变后widthPixels=${resources.displayMetrics.widthPixels}")
        Log.d("MainActivity", "改变后heightPixels=${resources.displayMetrics.heightPixels}")
        Log.d("MainActivity", "改变后density=${resources.displayMetrics.density}")
        Log.d("MainActivity", "改变后densityDpi=${resources.displayMetrics.densityDpi}")

        /**
         * 结论就是，只需要看设计图上的最小宽度(px)，然后再看dpi(mdpi、hdpi、xhdpi、xxhdpi)的值，算出density，然后就可以根据上面的公示进行适配了。高度适配同理
         */

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.d("MainActivity","onStart");
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity","onResume");
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity","onPause");
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity","onStop");
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity","onDestroy");
        super.onDestroy()
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration?) {
        Log.d("MainActivity", "onMultiWindowModeChanged isInMultiWindowMode->"+isInMultiWindowMode);
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
    }

    private val handler = Handler()
    private val runnable = Runnable { Log.d("MainAtivity", "onConfigurationChanged"); }

    override fun onConfigurationChanged(newConfig: Configuration) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable,100)

        super.onConfigurationChanged(newConfig)
    }
}