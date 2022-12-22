package com.example.ArfanUAS.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ArfanUAS.data.Helper.IMG_URL
import com.example.ArfanUAS.databinding.ContentLayoutBinding
import com.example.ArfanUAS.fragment.MoviesViewModel
import com.example.ArfanUAS.model.MovieEntity

// menampilkan konten film
class ContentMovieAdapter(
    private val movieCallback: ContentMovieCallback,
    private val viewModel: MoviesViewModel
) : PagedListAdapter<MovieEntity, ContentMovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val contentLayoutBinding =
            ContentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(contentLayoutBinding, viewModel)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

//    timpa fun getItemCount(): Int = listData.size

    inner class ListViewHolder(
        private val binding: ContentLayoutBinding,
        private val viewModel: MoviesViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMovie: MovieEntity) {
            with(binding) {
                tvTitle.text = dataMovie.title
                txtRate.text = dataMovie.rating.toString().trim()

                Glide.with(itemView.context)
                    .load(IMG_URL + dataMovie.imgPoster)
                    .into(imgItemPhoto)

                itemCard.setOnClickListener {
                    movieCallback.onItemClicked(dataMovie)
                }
                when (dataMovie.isFav) {
                    true -> {
                        ivAddFav.setColorFilter(Color.parseColor("#03A9F4"))
                    }
                    false -> {
                        ivAddFav.setColorFilter(Color.parseColor("#9A06133E"))
                    }
                    else -> {}
                }

                ivAddFav.setOnClickListener {
                    dataMovie.let {
                        viewModel.setFavListMovie(it)
                    }
                }
            }
        }
    }
}