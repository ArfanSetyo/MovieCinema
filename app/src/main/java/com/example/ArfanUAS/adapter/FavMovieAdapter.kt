package com.example.ArfanUAS.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ArfanUAS.data.Helper
import com.example.ArfanUAS.databinding.ContentFavLayoutBinding
import com.example.ArfanUAS.fragment.FavoriteViewModel
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.ui.detail.DetailActivity

// menampilkan film favorit
class FavMovieAdapter(private val viewModel: FavoriteViewModel) :
    PagedListAdapter<MovieEntity, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

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

    private var movList = ArrayList<MovieEntity?>()

    fun setMovies(list: List<MovieEntity?>?) {
        this.movList.clear()
        list?.let { this.movList.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ContentFavLayoutBinding,
        private val viewModel: FavoriteViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitleFav.text = movie.title
                tvDateFav.text = movie.released

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.apply {
                        putExtra(DetailActivity.EXTRA_DATA, movie.id)
                        putExtra(DetailActivity.EXTRA_TYPE, Helper.TYPE_MOVIE)
                    }
                    it.context.startActivity(intent)
                }
                ivRemove.setOnClickListener {
                    movie.let {
                        viewModel.setFavListMovie(it)
                    }
                }

                Helper.setGlideImage(itemView.context, movie.imgPoster, imgFav)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ContentFavLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        viewModel
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.movList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = this.movList.size
}