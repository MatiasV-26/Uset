package pe.edu.ulima.pm.uset.toolbar

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ToolbarChat02 {
    fun show(activities: AppCompatActivity, title:String, upButton:Boolean, toolbar: Toolbar){
        activities.setSupportActionBar(toolbar)
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}