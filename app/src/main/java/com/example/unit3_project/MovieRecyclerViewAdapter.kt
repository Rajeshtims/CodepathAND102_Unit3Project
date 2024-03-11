package com.example.unit3_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter(private val movies: List<Movie>, movieFragment: MovieFragment) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val movieDesc: TextView = itemView.findViewById(R.id.movie_description)
        val movieCover: ImageView = itemView.findViewById(R.id.movie_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val movie = movies[position]
        val movieNameTextView = holder.movieName
        val movieDescTextView = holder.movieDesc
//        val movieCoverImageView = holder.movieCover
        
        movieNameTextView.text = movie.title
        movieDescTextView.text = movie.desc

        val imageBaseUrl = "https://image.tmdb.org/t/p/original"
        // load image using Glide here
        Glide.with(holder.itemView)
            .load(imageBaseUrl + movie.imageUrl)
            .placeholder(R.drawable.ic_action_name)
            .centerInside()
//            .override(200, 400)
            .into(holder.movieCover)

//        TODO("Implement onclick listener to the item in the list")
    }


}