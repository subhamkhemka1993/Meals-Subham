package com.sk.applicationmeals.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sk.applicationmeals.R
import com.sk.applicationmeals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    companion object {
        const val ToolBarTypeCategories = 1
        const val ToolBarTypeMeals = 2
    }

    private var mainActivityBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding?.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
        mainActivityBinding?.toolbar?.mainToolbar?.setupWithNavController(navHostFragment.navController)
    }

//    fun setUpToolBar(toolBarType: Int) {
//        mainActivityBinding?.toolbar?.ivBack?.isVisible = false
//        when (toolBarType) {
//             -> {
//
//            }
//        }
//    }

}
