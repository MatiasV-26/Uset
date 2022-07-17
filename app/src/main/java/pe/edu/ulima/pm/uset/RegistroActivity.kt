package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.Fragments.Registro.Registro01Fragment
import pe.edu.ulima.pm.uset.Fragments.Registro.Registro02Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding
import pe.edu.ulima.pm.uset.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()

    private val fragmentLoginList: List<Fragment> = listOf(Registro01Fragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "...REGISTRO..."

        SetFragmentTransactionInConstant()
    }

    private fun SetFragmentTransactionInConstant() {
        fragmentTransaction
            .add(R.id.fragmentContainerViewRegistro, fragmentLoginList[0],"1")
            .commit()
    }
}