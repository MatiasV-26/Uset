package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.toolbar.ToolbarLogin

class CreateProfileActivity : AppCompatActivity() {
    //VIEW BINDING FOR ACTIVITIESs
    private lateinit var binding : ActivityCreateProfileBinding
    //FRAGMENT MANAGEMENT
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val fragmentLoginList: List<Fragment> = listOf(CreateProfile01Fragment())

    //ACTIVITY LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        //VIEW BINDING FOR ACTIVITIES
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TOOLBAR
        ToolbarLogin().show(this,"USET",false,binding.tbToolbarlogin.mtToolbarlogin)
        //FRAGMENT MANAGEMENT
        SetFragmentTransactionInTop(0)
    }

    //FRAGMENT MANAGEMENT
    private fun SetFragmentTransactionInTop(fragment: Int) {
        fragmentTransaction
            .add(R.id.fragmentContainerViewCreateProfile, fragmentLoginList[fragment])
            .commit()
    }
}
