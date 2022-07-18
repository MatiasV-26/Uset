package pe.edu.ulima.pm.uset

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.databinding.ActivityGuardarFotoBinding
import java.util.*

class GuardarFotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuardarFotoBinding
    private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuardarFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "...USET..."
        binding.btnFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        binding.btnRegresar.setOnClickListener {
            guardarFoto()
            goToChatActivity()
        }

    }
    var Foto : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Foto = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Foto)
            binding.ivFoto.setImageBitmap(bitmap)
        }
        SubirFoto()
    }
    private fun SubirFoto(){
        if (Foto == null) return
        val nombre = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference("/images/${nombre}")
        referencia.putFile(Foto!!).addOnSuccessListener {
            Log.i("Hola", it.metadata?.path.toString())
            referencia.downloadUrl.addOnSuccessListener {
                Log.i("Hola2", it.toString())
                url = it.toString()
            }
        }
    }
    private fun guardarFoto(){
        FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
            .update("uri", url)
    }
    private fun goToChatActivity() {
        //This' parent activity must be deleted after going to Chat Activity
        this.finish()
        startActivity(Intent(this, ChatActivity::class.java))
    }
}