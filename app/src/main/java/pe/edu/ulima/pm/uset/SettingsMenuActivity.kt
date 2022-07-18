package pe.edu.ulima.pm.uset

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.Fragments.Settings.menu.SettingsMenuFragment
import pe.edu.ulima.pm.uset.databinding.ActivitySettingsBinding

class SettingsMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Configuración"

        /*supportFragmentManager.beginTransaction()
            .add(R.id.fcwSettingsMenu,SettingsMenuFragment())
            .addToBackStack(null)
            .commit()*/
    }





}