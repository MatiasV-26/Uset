package pe.edu.ulima.pm.uset

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
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
            .add(R.id.fragmentContainerViewChats,ChatListUsers(),"1")
            .addToBackStack("dasdasd")
            .commit()
    }
}