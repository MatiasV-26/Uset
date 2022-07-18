package pe.edu.ulima.pm.uset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.errorprone.annotations.Var
import com.squareup.picasso.Picasso
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Fragments.Settings.menu.SettingsMenuFragment
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.databinding.ActivitySettingsBinding

class SettingsMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding
    private val userID = FirebaseClass.updateUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Configuración"


        updateViews()

        binding.btnChangePictureSettings.setOnClickListener {
            startActivity(Intent(this,GuardarFotoActivity::class.java))
        }
    }


    fun updateViews(){
        FirebaseClass.db.collection("users").document(userID!!)
            .get().addOnSuccessListener {
                var usuario = it.toObject(Usuario::class.java)
                binding.tvUsernameSettings.text = usuario!!.nombres
                Picasso.get().load(usuario!!.uri).into(binding.imgVwProfilePicSettings)
                updateNotificationsSwitch(usuario!!.settingsBasicos.get("notifications")!!)
            }
    }

    private fun updateNotificationsSwitch(cambio : Boolean){
        findViewById<Switch>(R.id.swActivateNotifications).isChecked = cambio
    }

}