package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Registro.Registro01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityRegistroBinding
import pe.edu.ulima.pm.uset.interfaces.IOnBackPressed

class RegistroActivity : AppCompatActivity() {
    //VIEW BINDING FOR ACTIVITIES
    private lateinit var binding : ActivityRegistroBinding
    //FRAGMENT MANAGEMENT
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val fragmentLoginList: List<Fragment> = listOf(Registro01Fragment())
    //REGISTER PERMANENT OBJECTS
    companion object{
        var correo : String? = null
        var password : String? = null
    }

    //ACTIVITY LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        //VIEW BINDING FOR ACTIVITIES
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TITLE
        title = "...REGISTRO..."
        //FRAGMENT MANAGEMENT
        SetFragmentTransactionInConstant()
    }

    //FRAGMENT MANAGEMENT
    private fun SetFragmentTransactionInConstant() {
        fragmentTransaction
            .add(R.id.fragmentContainerViewRegistro, fragmentLoginList[0])
            .commit()
    }
}