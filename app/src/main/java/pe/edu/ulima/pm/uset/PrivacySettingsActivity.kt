package pe.edu.ulima.pm.uset

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Fragments.Settings.privacy.PrivacySettingsFragment
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.databinding.ActivityPrivacySettingsBinding

class PrivacySettingsActivity: AppCompatActivity(){

    private lateinit var binding : ActivityPrivacySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Privacidad"

        updateViews()
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


    private fun updateViews(){
        FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
            .get().addOnSuccessListener {
                var usuario = it.toObject(Usuario::class.java)
                updateActivitySwitch(usuario!!.settingsBasicos.get("estadoActividad")!!)
            }
    }

    private fun updateActivitySwitch(cambio : Boolean){
        findViewById<Switch>(R.id.swShowActivityState).isChecked = cambio
    }
}