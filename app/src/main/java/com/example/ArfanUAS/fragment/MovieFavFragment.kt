package com.example.ArfanUAS.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ArfanUAS.adapter.FavMovieAdapter
import com.example.ArfanUAS.databinding.MovieFavFragmentBinding
import com.example.ArfanUAS.source.ViewModelFactory

// fragmen film favorit
class MovieFavFragment : Fragment() {

    private var binding: MovieFavFragmentBinding? = null
    private lateinit var adapterMovies: FavMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFavFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        true.progressBar()
        val viewFactory = ViewModelFactory.getInstance(requireContext())

        val mFavoriteViewModel by lazy {
            ViewModelProvider(this, viewFactory).get(FavoriteViewModel::class.java)
        }
        adapterMovies = FavMovieAdapter(mFavoriteViewModel)
        mFavoriteViewModel.getFavoriteMovies(context).observe(viewLifecycleOwner, {
            false.progressBar()
            adapterMovies.setMovies(it)
            adapterMovies.submitList(it)
            showRV()
        })
    }

    private fun showRV() {
        binding?.rvMoviesFav?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovies
        }
    }

    private fun Boolean.progressBar() {
        binding?.progressBar?.visibility = if (this) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}