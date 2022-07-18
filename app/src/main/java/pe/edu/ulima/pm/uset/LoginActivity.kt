package pe.edu.ulima.pm.uset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatListUsers
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Fragments.Login.Login01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val fragmentLoginList: List<Fragment> = listOf(Login01Fragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "...USET..."
        SetFragmentTransactionInTop(0)
    }

    private fun SetFragmentTransactionInTop(fragment: Int) {
        fragmentTransaction
            .add(R.id.fragmentContainerViewLogin, fragmentLoginList[fragment],"1")
            .commit()
    }
}