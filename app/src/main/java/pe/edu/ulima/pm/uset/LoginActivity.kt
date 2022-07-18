package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //VIEW BINDING FOR ACTIVITIES
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
        //TITLE
        title = "...USET..."
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