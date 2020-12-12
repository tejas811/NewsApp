package `in`.tejas.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClick): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleview.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updateNews:ArrayList<News>){
        items.clear()
        items.addAll(updateNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val titleview: TextView = itemView.findViewById(R.id.title)
    val imageview:ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)

}

interface NewsItemClick{
    fun onItemClick(item:News)
}