package pe.edu.ulima.pm.uset

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding

class CreateProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateProfileBinding
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val fragmentLoginList: List<Fragment> = listOf(CreateProfile01Fragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "...USET..."
        SetFragmentTransactionInTop(0)
    }

    private fun SetFragmentTransactionInTop(fragment: Int) {
        fragmentTransaction
            .add(R.id.fragmentContainerViewCreateProfile, fragmentLoginList[fragment],"1")
            .commit()
    }


}
