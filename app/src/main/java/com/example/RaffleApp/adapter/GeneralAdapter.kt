package com.example.RaffleApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RaffleApp.R
import com.example.RaffleApp.configs.SSLPermission
import com.example.RaffleApp.configs.Util
import com.example.RaffleApp.models.Cekilis
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class GeneralAdapter(
    private val list: List<Cekilis>,
    private val action: Int,
    private val findNavController: NavController
):
    RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
            list_title_txt.text = item.title
            list_price_txt.text = item.price
            list_gift_value_txt.text = item.giftValue
            list_time_left_txt.text = item.timeLeft

            try {
                val sslContext = SSLPermission().createTrustAllSSLContext()
                if (sslContext != null) {
                    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
                }

                Glide.with(holder.itemView.context).load(item.img).into(list_image)

            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        holder.itemView.setOnClickListener {
            Util.chosen = list[position]
            findNavController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var list_image: ImageView = itemView.findViewById(R.id.list_image)
        var list_title_txt: TextView = itemView.findViewById(R.id.list_title_txt)
        var list_price_txt: TextView = itemView.findViewById(R.id.list_price_txt)
        var list_gift_value_txt: TextView = itemView.findViewById(R.id.list_gift_value_txt)
        var list_time_left_txt: TextView = itemView.findViewById(R.id.list_time_left_txt)

    }

}