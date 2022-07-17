package pe.edu.ulima.pm.uset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatListUsers
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Chats"

        fragmentTransaction
            .add(R.id.fragmentContainerViewChats,ChatListUsers(),"listUsers")
            .addToBackStack("listUsers")
            .commit()
    }
}