package com.munawirfikri.bajp_submission3.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.munawirfikri.bajp_submission3.adapter.FavoriteTvShowAdapter
import com.munawirfikri.bajp_submission3.databinding.FragmentFavoriteTvShowBinding
import com.munawirfikri.bajp_submission3.viewmodel.ViewModelFactory


class FavoriteTvShowFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentFavoriteTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val tvShowAdapter = FavoriteTvShowAdapter()
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {tvShows ->
                fragmentMovieBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShow(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            })


            with(fragmentMovieBinding.rvFavTvShow) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }
}