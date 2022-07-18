package pe.edu.ulima.pm.uset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.Fragments.AddFriends.AddFriendFragment
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatListUsers
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val db = Firebase.firestore
    private val docRef = db.collection("users").document(FirebaseClass.updateUI()!!)
    private val fragmentManager = supportFragmentManager
    private var fragmentTransaction = fragmentManager.beginTransaction()


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

        binding.navView.setNavigationItemSelectedListener {
            it.setChecked(true)
            when(it.itemId){
                R.id.add_friend ->pressAddFriend()
                R.id.settings -> pressSettings()
                R.id.logout -> pressLogout()
            }

            binding.drawerMenuLayout.closeDrawer(GravityCompat.START)
            true
        }



        actualizarUsername()



    }

    private fun actualizarUsername(){
        docRef.get().addOnSuccessListener {
            var nombreUser = it.data!!.get("nombres")
            findViewById<TextView>(R.id.tvUsernameMenu).text = nombreUser.toString()
        }
    }

    private fun pressLogout() {
        FirebaseClass.auth.signOut()
        this.finish()
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun pressSettings() {
        var intent = Intent(this,SettingsMenuActivity::class.java)
        startActivity(intent)
    }

    private fun pressAddFriend(){
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.fragmentContainerViewChats,AddFriendFragment(),"addFriends")
            .addToBackStack("addFriendFragment")
            .commit()
        title = "Agregar amigos"
    }

    override fun onResume() {
        super.onResume()
        var a = binding.navView.checkedItem
        if(a!=null){
            a.setChecked(false)
        }
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