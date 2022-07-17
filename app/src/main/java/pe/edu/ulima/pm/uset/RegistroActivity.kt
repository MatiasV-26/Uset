package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.Fragments.Registro.Registro02Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding
import pe.edu.ulima.pm.uset.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()

    private val fragmentLoginList: List<Fragment> = listOf(Registro02Fragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "...USET..."

        SetFragmentTransactionInTop(0)
    }

    private fun SetFragmentTransactionInTop(fragment: Int) {
        fragmentTransaction
            .add(R.id.fragmentContainerViewLogin, fragmentLoginList[fragment],"1")
            .addToBackStack("dasdasd")
            .commit()
    }

}