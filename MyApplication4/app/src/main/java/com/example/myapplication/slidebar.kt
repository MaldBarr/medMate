package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivitySlidebarBinding

class slidebar : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySlidebarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mostrarAlertDialog("Bienvenido", "Bienvenido a la aplicación de Medi-Mate")

        binding = ActivitySlidebarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarSlidebar.toolbar)

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

        val name = intent.getStringExtra("USER_NAME")
        val email = intent.getStringExtra("USER_EMAIL")

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val headerTitle = headerView.findViewById<TextView>(R.id.correo_nav_header)
        val headerSubtitle = headerView.findViewById<TextView>(R.id.nombre_nav_header)

        // Establecer el texto en los TextViews del encabezado
        headerTitle.text = name
        headerSubtitle.text = email


        //poner items en el sidelist en Calenadario
        val home = findViewById<FrameLayout>(R.id.home)
        val listadoMedicamentos = home.findViewById<RecyclerView>(R.id.listadoMedicamentos)
        val myDataset = listOf("Medicamento 1", "Medicamento 2", "Medicamento 3")
        listadoMedicamentos.layoutManager = LinearLayoutManager(this)
        listadoMedicamentos.adapter = MyAdapter(myDataset)





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


    class MyAdapter(private val myDataset: List<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val textView = holder.view.findViewById<TextView>(R.id.nameTextView)
            textView.text = myDataset[position]
        }

        override fun getItemCount() = myDataset.size
    }
    private fun mostrarAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, id ->
                // Acción al hacer clic en Aceptar en el AlertDialog
            }
            .show()
    }

}