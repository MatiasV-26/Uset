package pe.edu.ulima.pm.uset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatListUsers
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "LOGIN ACTIVITY"

        fragmentTransaction
            .add(R.id.fragmentContainerViewChats, ChatListUsers(),"1")
            .addToBackStack("dasdasd")
            .commit()
    }


}