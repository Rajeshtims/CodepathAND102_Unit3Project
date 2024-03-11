package com.example.unit3_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.unit3_project.R.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers


class MovieFragment : Fragment() {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
//        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
//        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        updateAdapter(recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(recyclerView: RecyclerView) {
//        progressBar.show()
        // Create and set up an AsyncHTTPClient() here
        Log.d("Rajesh", "inside updateAdapter function")
        val client = AsyncHttpClient()
        val params = RequestParams()
//        params["api-key"] = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        val endpoint = "https://api.themoviedb.org/3/movie/now_playing?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
        // Using the client, perform the HTTP request
        client[
            endpoint,
            params,
            object : JsonHttpResponseHandler() {
                /*
                 * The onSuccess function gets called when
                 * HTTP response status is "200 OK"
                 */
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JSON
                ) {
                    // The wait for a response is over
                    Log.d("Rajesh", "Inside on success function")
                    Log.d("Rajesh", json.toString())
                    //TODO - Parse JSON into Models
//                    val results = json.jsonObject.get("results") as JSONObject
                    val moviesRawJSON = json.jsonObject.get("results").toString()
                    Log.d("Rajesh", moviesRawJSON)
                    // converting json to kotlin data class
                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<Movie>>() {}.type

                    val models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType) // Fix me!

                    recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)
                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("Rajesh", errorResponse)
                    }
                }
            }]


    }
}