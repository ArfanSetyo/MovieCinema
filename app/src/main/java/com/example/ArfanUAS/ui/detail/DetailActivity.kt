package com.example.ArfanUAS.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ArfanUAS.data.Helper.TYPE_MOVIE
import com.example.ArfanUAS.data.Helper.TYPE_TVSHOW
import com.example.ArfanUAS.data.Helper.setGlideImage
import com.example.ArfanUAS.databinding.ActivityDetailBinding
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.model.TvShowEntity
import com.example.ArfanUAS.source.ViewModelFactory

// menampilkan user interface (UI) dari aplikasi yang akan dibuat
class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        val idData = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        Log.e("pass ID", "id data " + idData.toString())

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            idData.let {
                viewModel.setSelectedMovie(idData)
                viewModel.getMovie().observe(this, { detail ->
                    populateDataMovie(detail.body, viewModel)
                    Log.e("data", detail.body?.title.toString())
                })
            }
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            viewModel.setSelectedTV(idData)
            idData.let {
                viewModel.getTV().observe(this, { detail ->
                    populateDataTv(detail.body, viewModel)
                    Log.e("data", detail.body?.title.toString())
                })
            }
        }

    }

    fun populateDataMovie(movie: MovieEntity?, viewModel: DetailViewModel) {
        binding.tvTitle.text = movie?.title.toString()
        binding.tvDesc.text = movie?.overview.toString()
        binding.tvRealaseDate.text = movie?.released.toString()
        binding.txtRating.text = movie?.rating.toString().trim()
        binding.tvGenre.text = movie?.genre.toString()
        when (movie?.isFav) {
            true -> {
                binding.buttonFav.setColorFilter(Color.parseColor("#03A9F4"))
            }
            false -> {
                binding.buttonFav.setColorFilter(Color.parseColor("#9A06133E"))
            }
            else -> {}
        }

        binding.buttonFav.setOnClickListener {
            movie.let {
                viewModel.setFavMovie(it!!)
                when (it.isFav) {
                    true -> {
                        Toast.makeText(this, "Favorit Dibatalkan", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        Toast.makeText(this, "Ditambahkan ke Favorit", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
        setGlideImage(this@DetailActivity, movie?.imgPoster.toString(), binding.imgItemPhoto)
        setGlideImage(this@DetailActivity, movie?.imgBackground.toString(), binding.imgItemPreview)
    }

    fun populateDataTv(tv: TvShowEntity?, viewModel: DetailViewModel) {
        binding.tvTitle.text = tv?.title.toString()
        binding.tvDesc.text = tv?.overview.toString()
        binding.tvRealaseDate.text = tv?.firstAir.toString()
        binding.txtRating.text = tv?.rating.toString().trim()
        binding.tvGenre.text = tv?.genre.toString()

        when (tv?.isFav) {
            true -> {
                binding.buttonFav.setColorFilter(Color.parseColor("#03A9F4"))
            }
            false -> {
                binding.buttonFav.setColorFilter(Color.parseColor("#9A06133E"))
            }
            else -> {}
        }

        binding.buttonFav.setOnClickListener {
            tv.let {
                viewModel.setFavTVShow(it!!)
                when (it.isFav) {
                    true -> {
                        Toast.makeText(this, "Ditambahkan Ke Favorit", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        Toast.makeText(this, "Favorit Dibatalkan", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
        setGlideImage(this@DetailActivity, tv?.imgPoster.toString(), binding.imgItemPhoto)
        setGlideImage(this@DetailActivity, tv?.imgBackground.toString(), binding.imgItemPreview)
    }
}