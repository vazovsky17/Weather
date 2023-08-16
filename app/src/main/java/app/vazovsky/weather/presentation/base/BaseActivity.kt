package app.vazovsky.weather.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope

abstract class BaseActivity(@LayoutRes val layout: Int) : AppCompatActivity(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        lifecycleScope.launchWhenStarted {
            callOperations()
        }
        onSetupLayout(savedInstanceState)
        onBindViewModel()
    }

    abstract fun callOperations()
    abstract fun onSetupLayout(savedInstanceState: Bundle?)
    abstract fun onBindViewModel()

    protected infix fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(this@BaseActivity) { t -> block.invoke(t) }
    }
}