package pe.edu.ulima.pm.uset.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R

class ChatUsersAdapter: RecyclerView.Adapter<ChatUsersAdapter.ChatUsersHolder>() {

    class ChatUsersHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    fun setData(list:List<UserChat>){
        listUserChats = list
        notifyDataSetChanged()
    }

    val a = UserChat("sdadasdadad","dadadasda",["Persona1","Persona2"])

    var listUserChats : List<UserChat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatUsersHolder {
        return ChatUsersHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_chat,parent,false))
    }

    override fun onBindViewHolder(holder: ChatUsersHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listUserChats.size
    }

}