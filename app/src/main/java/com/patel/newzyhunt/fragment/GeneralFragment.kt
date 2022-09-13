package com.patel.newzyhunt.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patel.newzyhunt.MainActivity
import com.patel.newzyhunt.NewsModel
import com.patel.newzyhunt.R
import com.patel.newzyhunt.ReadNewsActivity
import com.patel.newzyhunt.adapters.CustomAdapter

class GeneralFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var newsDataForDown: List<NewsModel>
    var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_general, container, false)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        // Setting recyclerViews adapter
       newsDataForDown =
            MainActivity.generalNews.slice(MainActivity.TOP_HEADLINES_COUNT until MainActivity.generalNews.size - MainActivity.TOP_HEADLINES_COUNT)
        adapter = CustomAdapter(newsDataForDown)
        recyclerView.adapter = adapter

        // listitem onClick
        adapter.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context, ReadNewsActivity::class.java)
                intent.putExtra(MainActivity.NEWS_URL, newsDataForDown[position].url)
                intent.putExtra(MainActivity.NEWS_TITLE, newsDataForDown[position].headLine)
                intent.putExtra(MainActivity.NEWS_IMAGE_URL, newsDataForDown[position].image)
                intent.putExtra(
                    MainActivity.NEWS_DESCRIPTION,
                    newsDataForDown[position].description
                )
                intent.putExtra(MainActivity.NEWS_SOURCE, newsDataForDown[position].source)
                intent.putExtra(MainActivity.NEWS_PUBLICATION_TIME, newsDataForDown[position].time)
                intent.putExtra(MainActivity.NEWS_CONTENT, newsDataForDown[position].content)
                startActivity(intent)
            }
        })


        //ignore
        adapter.setOnItemLongClickListener(object : CustomAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
            }
        })

        return view
    }

}