package com.android.githubclient.ui.activity

import android.os.Bundle
import com.android.githubclient.mvp.presenter.MainPresenter
import com.android.githubclient.mvp.view.MainView
import com.android.githubclient.navigation.AndroidScreens
import com.android.githubclient.App
import com.gb.githubclient.R
import com.gb.githubclient.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}