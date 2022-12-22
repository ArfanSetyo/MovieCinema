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
import com.example.ArfanUAS.model.TvShowEntity
import com.example.ArfanUAS.ui.detail.DetailActivity

// adaptor tv favorit
class FavTvAdapter(private val viewModel: FavoriteViewModel) :
    PagedListAdapter<TvShowEntity, FavTvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var tvList = ArrayList<TvShowEntity?>()

    fun setTv(list: List<TvShowEntity?>?) {
        this.tvList.clear()
        list?.let { this.tvList.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ContentFavLayoutBinding,
        private val viewModel: FavoriteViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShowEntity) {
            with(binding) {
                tvTitleFav.text = tv.title
                tvDateFav.text = tv.released

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.apply {
                        putExtra(DetailActivity.EXTRA_DATA, tv.id)
                        putExtra(DetailActivity.EXTRA_TYPE, Helper.TYPE_TVSHOW)
                    }
                    it.context.startActivity(intent)
                }
                ivRemove.setOnClickListener {
                    tv.let {
                        viewModel.setFavListTVShow(it)
                    }
                }

                Helper.setGlideImage(itemView.context, tv.imgPoster, imgFav)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ContentFavLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        viewModel
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.tvList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = this.tvList.size
}