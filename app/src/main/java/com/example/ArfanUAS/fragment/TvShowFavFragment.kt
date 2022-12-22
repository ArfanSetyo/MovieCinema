package com.example.ArfanUAS.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ArfanUAS.adapter.FavTvAdapter
import com.example.ArfanUAS.databinding.TvShowFavFragmentBinding
import com.example.ArfanUAS.source.ViewModelFactory

// fragmen favorit pada acara tv
class TvShowFavFragment : Fragment() {

    private var binding : TvShowFavFragmentBinding? = null
    private lateinit var adapterTv : FavTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TvShowFavFragmentBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        true.progressBar()
        val viewFactory = ViewModelFactory.getInstance(requireContext())

        val mFavoriteViewModel by lazy {
            ViewModelProvider(this, viewFactory).get(FavoriteViewModel::class.java)
        }

        adapterTv = FavTvAdapter(mFavoriteViewModel)
        mFavoriteViewModel.getFavoriteTV(context).observe(viewLifecycleOwner, {
            false.progressBar()
            adapterTv.setTv(it)
            adapterTv.submitList(it)
            showRV()
        })

    }

    private fun showRV(){
        binding?.rvTvshowFav?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTv
        }
    }

    private fun Boolean.progressBar() {
        binding?.progressBarTvFav?.visibility = if (this) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}