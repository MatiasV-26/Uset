package pe.edu.ulima.pm.uset

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.databinding.ActivityCreateProfileBinding
import pe.edu.ulima.pm.uset.databinding.ActivityGuardarFotoBinding

class GuardarFotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuardarFotoBinding

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
    }
    var Foto : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Foto = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Foto)
            binding.ivFoto.setImageBitmap(bitmap)
        }
    }
    private fun SubirFoto(){

    }
}