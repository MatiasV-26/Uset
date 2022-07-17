package pe.edu.ulima.pm.uset

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import pe.edu.ulima.pm.uset.Fragments.AddFriends.AddFriendFragment
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatListUsers
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    val fragmentManager = supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Chats"

        val toggle = ActionBarDrawerToggle(this
            ,binding.drawerMenuLayout
            ,R.string.drawer_open
            ,R.string.drawer_close)

        binding.drawerMenuLayout.addDrawerListener(toggle)
        toggle.syncState()

        fragmentTransaction
            .add(R.id.fragmentContainerViewChats,ChatListUsers(),"listUsers")
            .addToBackStack("listUsers")
            .commit()

        binding.navView.setNavigationItemSelectedListener {
            it.setChecked(true)
            when(it.itemId){
                R.id.add_friend ->pressAddFriend()
            }

            binding.drawerMenuLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun pressAddFriend(){
        Log.d("adadasda",supportFragmentManager.primaryNavigationFragment.toString())
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.fragmentContainerViewChats,AddFriendFragment(),"addFriends")
            .addToBackStack("addFriendFragment")
            .commit()
        title = "Agregar amigos"
    }

    override fun onBackPressed() {
        if(binding.drawerMenuLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerMenuLayout.closeDrawer(GravityCompat.START)
        }else{
            var a = binding.navView.checkedItem
            if(a!=null){
                a.setChecked(false)
            }
            super.onBackPressed()
        }
    }
}