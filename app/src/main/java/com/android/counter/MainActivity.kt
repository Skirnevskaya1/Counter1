package com.android.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        val listener1 = View.OnClickListener {
            presenter.counterOneClick()
        }
        val listener2 = View.OnClickListener {
            presenter.counterTwoClick()
        }
        val listener3 = View.OnClickListener {
            presenter.counterThreeClick()
        }
        vb?.btnCounter1?.setOnClickListener(listener1)
        vb?.btnCounter2?.setOnClickListener(listener2)
        vb?.btnCounter3?.setOnClickListener(listener3)
    }

    override fun setButtonOneText(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButtonTwoText(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButtonThreeText(text: String) {
        vb?.btnCounter3?.text = text
    }
}

