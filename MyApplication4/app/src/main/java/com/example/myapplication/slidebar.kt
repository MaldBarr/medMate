package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySlidebarBinding

class slidebar : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySlidebarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySlidebarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarSlidebar.toolbar)

        binding.appBarSlidebar.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_slidebar)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_settings, R.id.nav_share, R.id.nav_aboutUs
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val lunes = findViewById<Button>(R.id.Lunes)
        val martes = findViewById<Button>(R.id.Martes)
        val miercoles = findViewById<Button>(R.id.Miercoles)
        val jueves = findViewById<Button>(R.id.Jueves)
        val viernes = findViewById<Button>(R.id.Viernes)
        val sabado = findViewById<Button>(R.id.Sabado)
        val domingo = findViewById<Button>(R.id.Domingo)

        lunes.setOnClickListener {

        }
        martes.setOnClickListener {

        }
        miercoles.setOnClickListener {

        }
        jueves.setOnClickListener {

        }
        viernes.setOnClickListener {

        }
        sabado.setOnClickListener {

        }
        domingo.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.slidebar, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_slidebar)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}