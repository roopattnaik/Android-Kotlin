package com.example.loginNav.view.ui.gamelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginNav.R
import com.example.loginNav.model.GameList


class GamesAdapter(private var moviesList: List<GameList>, var listener: Onitemclicklistener, var longclicklistener: OnItemLongClicklistener) :
    RecyclerView.Adapter<GamesAdapter.MyViewHolder>() {
    public class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var genre: TextView
        var year: TextView
        var image: ImageView

        init {
            title = view.findViewById(R.id.title)
            genre = view.findViewById(R.id.genre)
            year = view.findViewById(R.id.year)
            image = view.findViewById(R.id.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_list_row, parent, false)

        return MyViewHolder(
            itemView
        )
        //  TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        return moviesList.size
        // TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val movie: GameList = moviesList[position]
        holder.title.text = movie.title
        holder.genre.text = movie.genre
        holder.year.text = movie.year
        Glide.with(holder.itemView).load(movie.image).placeholder(R.drawable.movie2)
            .into(holder.image)
        holder.itemView.setOnLongClickListener(object :View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                longclicklistener.onLongClick(moviesList[position])
            return true
            }
        })

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onClick(movie)
            }
        })

    }

    interface Onitemclicklistener {
        fun onClick(gameList: GameList)
    }

    interface OnItemLongClicklistener {
        fun onLongClick(gameList: GameList) {
        }
    }
}

