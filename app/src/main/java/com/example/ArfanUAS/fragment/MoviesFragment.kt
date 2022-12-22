package com.example.ArfanUAS.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ArfanUAS.adapter.ContentMovieAdapter
import com.example.ArfanUAS.adapter.ContentMovieCallback
import com.example.ArfanUAS.data.Helper.TYPE_MOVIE
import com.example.ArfanUAS.databinding.MoviesFragmentBinding
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.source.ViewModelFactory
import com.example.ArfanUAS.ui.detail.DetailActivity
import com.example.ArfanUAS.vo.Status

// fragmen film
class MoviesFragment : Fragment(), ContentMovieCallback {

    private var binding: MoviesFragmentBinding? = null
    private lateinit var adapterMov : ContentMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel by lazy {
            ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        }
        adapterMov = ContentMovieAdapter(this@MoviesFragment,viewModel)
        viewModel.getMoviesData().observe(viewLifecycleOwner) { list ->
            if (list != null) {
                when (list.status) {
                    Status.LOADING -> true.progressBar()
                    Status.SUCCESS -> {
                        false.progressBar()
                        adapterMov.submitList(list.body)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        setRecycler()
    }

    private fun setRecycler() {
        binding?.rvMovie?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterMov
        }
    }

    override fun onItemClicked(dataMovie: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, dataMovie.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

    private fun Boolean.progressBar() {
        binding?.progressBar?.visibility = if (this) View.VISIBLE else View.GONE
    }

}