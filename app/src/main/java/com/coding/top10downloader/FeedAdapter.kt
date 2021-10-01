package com.coding.top10downloader

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FeedAdapter(
    context: Context,
    private val resource: Int,
    private val applications: List<FeedEntry>
) : ArrayAdapter<FeedEntry>(context, resource) {
    private val TAG = "FeedAdapter"
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        Log.d(TAG, "getCount(): Called")
        return applications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(TAG, "getView(): Called")
        val view: View
        if (convertView == null) { //If it's null, it means there is no recycled view and we have to create a new one, otherwise we must use it to avoid creating a new one.
            Log.d(TAG, "getView: Called with null convertView")
            view = inflater.inflate(resource, parent, false)
        } else {
            Log.d(TAG, "getView: Provided a convertView")
            view = convertView
        }

        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvArtist: TextView = view.findViewById(R.id.tvArtist)
        val tvSummary: TextView = view.findViewById(R.id.tvSummary)

        val currentApp = applications[position]

        tvName.text = currentApp.name
        tvArtist.text = currentApp.artist
        tvSummary.text = currentApp.summary

        return view
    }
}