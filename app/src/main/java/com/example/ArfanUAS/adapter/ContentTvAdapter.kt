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
import com.example.ArfanUAS.fragment.TvShowViewModel
import com.example.ArfanUAS.model.TvShowEntity

// adaptor tv konten
class ContentTvAdapter(
    private val tvCallback: ContentTvCallback,
    private val viewModel: TvShowViewModel
) :
    PagedListAdapter<TvShowEntity, ContentTvAdapter.ListViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val contentLayoutBinding =
            ContentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(contentLayoutBinding, viewModel)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class ListViewHolder(
        private val binding: ContentLayoutBinding,
        private val viewModel: TvShowViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataTv: TvShowEntity) {
            with(binding) {
                tvTitle.text = dataTv.title
                txtRate.text = dataTv.rating.toString().trim()

                Glide.with(itemView.context)
                    .load(IMG_URL + dataTv.imgPoster)
                    .into(imgItemPhoto)

                itemCard.setOnClickListener {
                    tvCallback.onItemClicked(dataTv)
                }
                when (dataTv.isFav) {
                    true -> {
                        ivAddFav.setColorFilter(Color.parseColor("#03A9F4"))
                    }
                    false -> {
                        ivAddFav.setColorFilter(Color.parseColor("#9A06133E"))
                    }
                    else -> {}
                }
                ivAddFav.setOnClickListener {
                    dataTv.let {
                        viewModel.setFavListTVShow(it)
                    }
                }
            }
        }
    }
}