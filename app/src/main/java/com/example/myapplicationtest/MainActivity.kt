package com.example.myapplicationtest

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.ui.navigateUp
import com.example.myapplicationtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.appBarMain.toolbar.setBackgroundColor(Color.White.toArgb())
//        binding.appBarMain.toolbar.setTitleTextColor(Color.Black.toArgb())


        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            )
            , drawerLayout
        )


        // TODO: 改topbar應該要去看appbarconfiguration 
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(name, context, attrs)
    }

    override fun onResume() {
        findNavController(R.id.nav_host_fragment_content_main).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.nav_home -> {}
                R.id.nav_gallery -> { changeAppBar(title = arguments?.getString("title").orEmpty())}
                R.id.nav_slideshow -> { changeAppBar(title = arguments?.getString("titleCh").orEmpty())}
                else -> {}
            }
        }
        super.onResume()
    }

    fun changeAppBar(title:String, icon: Drawable? = null){
//        binding.appBarMain.toolbar.setBackgroundColor(Color.White.toArgb())
//        binding.appBarMain.toolbar.setTitleTextColor(Color.Black.toArgb())
        binding.appBarMain.toolbar.title = title
        Log.d("96_789", "changeAppBar: $title")
//        binding.appBarMain.toolbar.navigationIcon = getDrawable(R.drawable.ic_menu_gallery)
    }
}
