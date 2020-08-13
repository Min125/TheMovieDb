package coder.mtk.themoviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coder.mtk.themoviedb.R
import coder.mtk.themoviedb.adapter.TopRatingAdapter
import coder.mtk.themoviedb.model.Result
import coder.mtk.themoviedb.viewmodel.TopRatedMoviesViewModel
import kotlinx.android.synthetic.main.fragment_top_rated_movie.*

class TopRatedMovieFragment : Fragment() , TopRatingAdapter.ClickListener{

    lateinit var topRatedViewModel: TopRatedMoviesViewModel

    lateinit var topRatingAdapter: TopRatingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedViewModel = ViewModelProvider(this).get(TopRatedMoviesViewModel::class.java)

        topRatingAdapter = TopRatingAdapter()
        movieRecyclerView.layoutManager = GridLayoutManager(context, 2)

        topRatedViewModel.getResult().observe(
            viewLifecycleOwner, Observer {
                topRatingAdapter.updateList(it.results)
                movieRecyclerView.adapter = topRatingAdapter
            }
        )

        topRatingAdapter.setOnClickListener(this)



    }

    override fun onResume() {
        super.onResume()
        topRatedViewModel.loadTopRatedMovie()
    }



    override fun onClick(result: Result) {
        var action = TopRatedMovieFragmentDirections.actionTopRatedMovieFragmentToDetailMoviesFragment(result.id)
        findNavController().navigate(action)
    }


}