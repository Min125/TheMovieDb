package coder.mtk.themoviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coder.mtk.themoviedb.R
import coder.mtk.themoviedb.viewmodel.TopRatedMoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movies.*

class DetailMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var messageargg = arguments?.let {
            DetailMoviesFragmentArgs.fromBundle(it)
        }

        var id : Int = messageargg?.id!!

        var viewmodel : TopRatedMoviesViewModel = ViewModelProvider(this).get(TopRatedMoviesViewModel::class.java)

        viewmodel.loadDetai(id)

        viewmodel.getDetaild().observe(
            viewLifecycleOwner, Observer {
                movieDetailTitle.text = it.title
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + it.poster_path)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieDetailImage)
                voteCount.text  = it.vote_count.toString()
                overviewDetail.text = it.overview
            }
        )
    }
}