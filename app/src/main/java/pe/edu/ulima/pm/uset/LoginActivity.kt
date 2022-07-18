package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding
import pe.edu.ulima.pm.uset.toolbar.ToolbarChat01
import pe.edu.ulima.pm.uset.toolbar.ToolbarLogin

class LoginActivity : AppCompatActivity() {
    //VIEW BINDING FOR ACTIVITIESs
    private lateinit var binding : ActivityLoginBinding
    //FRAGMENT MANAGEMENT
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val fragmentLoginList: List<Fragment> = listOf(Login01Fragment())


    //ACTIVITY LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        //VIEW BINDING FOR ACTIVITIES
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TOOLBAR
        ToolbarLogin().show(this,"LOGIN",false,binding.tbToolbarlogin.mtToolbarlogin)
        //FRAGMENT MANAGEMENT
        SetFragmentTransactionInConstant(0)
    }

    //FRAGMENT MANAGEMENT
    private fun SetFragmentTransactionInConstant(fragment: Int) {
        fragmentTransaction
            .add(R.id.fragmentContainerViewLogin, fragmentLoginList[fragment])
            .commit()
    }
}