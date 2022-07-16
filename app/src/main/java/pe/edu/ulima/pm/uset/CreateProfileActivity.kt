package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding

class CreateProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}