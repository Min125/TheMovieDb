package coder.mtk.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coder.mtk.themoviedb.R
import coder.mtk.themoviedb.model.Result
import coder.mtk.themoviedb.model.TopRatedMovies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_topratedmovies.view.*

class TopRatingAdapter(var topRateList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<TopRatingAdapter.TopRatingViewHolder>() {

    var clickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }


    fun updateList(topRateList: List<Result>) {
        this.topRateList = topRateList
    }

    inner class TopRatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result=result
            itemView.topMovieTitle.text = result.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.topMoviePhoto)
        }

        override fun onClick(p0: View?) {
            clickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatingViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_topratedmovies, parent, false)
        return TopRatingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topRateList.size
    }

    override fun onBindViewHolder(holder: TopRatingViewHolder, position: Int) {
        holder.bind(topRateList[position])
    }

    interface ClickListener {
        fun onClick(result: Result)
    }


}