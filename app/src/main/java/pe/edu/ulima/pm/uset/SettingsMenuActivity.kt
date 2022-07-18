package pe.edu.ulima.pm.uset

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.databinding.ActivitySettingsBinding

class SettingsMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Configuración"

        binding.conlayOptionEditAccount.setOnClickListener{ }

        binding.conlayOptionEditSecurity.setOnClickListener{ onPrivacySettingPressed() }

        binding.conlayEditNotifications.setOnClickListener{ onNotificationSettingPressed() }
    }

    private fun onNotificationSettingPressed(){
        if(binding.conlayActivateNotification.visibility == View.VISIBLE){
            binding.ivSettingsNotificationArrow.rotation = 0F
            binding.conlayActivateNotification.visibility = View.GONE
        }else{
            binding.ivSettingsNotificationArrow.rotation = 90F
            binding.conlayActivateNotification.visibility = View.VISIBLE
        }
    }

    private fun onPrivacySettingPressed(){
        var intent = Intent(this,PrivacySettingsActivity::class.java)
        startActivity(intent)
    }



}