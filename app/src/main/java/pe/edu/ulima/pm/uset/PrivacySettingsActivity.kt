package pe.edu.ulima.pm.uset

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.Fragments.Settings.PrivacySettingsFragment
import pe.edu.ulima.pm.uset.databinding.ActivityPrivacySettingsBinding

class PrivacySettingsActivity: AppCompatActivity(){

    private lateinit var binding : ActivityPrivacySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Privacidad"

        supportFragmentManager.beginTransaction()
            .add(R.id.fcwPrivacySettings,PrivacySettingsFragment())
            .addToBackStack("settings1")
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}