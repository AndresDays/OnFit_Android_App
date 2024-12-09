package com.example.equis

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.equis.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var usuarios = ArrayList<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableArrayListExtra<Cliente>("list")?.let {
            usuarios.addAll(it)
        }

        setSupportActionBar(binding.appBarMain.toolbar)

        setupDrawer()

        binding.appBarMain.fab.setOnClickListener {
            Toast.makeText(this, "Replace with your own action", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupDrawer() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_accesorio,
                R.id.nav_perfil, R.id.nav_logout, R.id.nav_entrenadores
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { item ->
            // Cerrar el Drawer antes de realizar la navegación
            binding.drawerLayout.closeDrawer(GravityCompat.START)

            when (item.itemId) {
                R.id.nav_logout -> {
                    logout()
                    true
                }
                else -> {
                    // Verifica si el ID del item corresponde a un destino válido en el navGraph
                    when (item.itemId) {
                        R.id.nav_home -> navController.navigate(R.id.nav_home)
                        R.id.nav_gallery -> navController.navigate(R.id.nav_gallery)
                        R.id.nav_slideshow -> navController.navigate(R.id.nav_slideshow)
                        R.id.nav_accesorio -> navController.navigate(R.id.nav_accesorio)
                        R.id.nav_perfil -> navController.navigate(R.id.nav_perfil)
                        R.id.nav_entrenadores -> navController.navigate(R.id.nav_entrenadores)
                        else -> false
                    }
                    true
                }
            }
        }
    }

    private fun logout() {
        getSharedPreferences("MyAppPrefs", MODE_PRIVATE).edit().clear().apply()
        val resultIntent = Intent(this, LoginActivity::class.java)
        resultIntent.putParcelableArrayListExtra("list", usuarios)
        startActivity(resultIntent)
        //finish() // Opcional: Esto cierra la actividad actual
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}