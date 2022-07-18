package pe.edu.ulima.pm.uset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.Fragments.Settings.privacy.PrivacySettingsFragment
import pe.edu.ulima.pm.uset.databinding.ActivityPrivacySettingsBinding

class PrivacySettingsActivity: AppCompatActivity(){

    private var numTransactionsFragments = 0

    private lateinit var binding : ActivityPrivacySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Privacidad"

        /*numTransactionsFragments += 1
        supportFragmentManager.beginTransaction()
            .add(R.id.fcwPrivacySettings, PrivacySettingsFragment())
            .addToBackStack("settings1")
            .commit()*/
    }

    override fun onBackPressed() {
        if (numTransactionsFragments == 1){
            this.finish()
        }else{
            super.onBackPressed()
            //minNumTransactions()
        }
    }

    /*public fun addNumTransactions(){
        numTransactionsFragments += 1
    }

    protected fun minNumTransactions(){
        numTransactionsFragments -= 1
    }*/
}