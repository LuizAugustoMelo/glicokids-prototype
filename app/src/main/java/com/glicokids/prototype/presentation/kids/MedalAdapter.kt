package com.glicokids.prototype.presentation.kids

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.glicokids.prototype.R

class MedalAdapter(private val context: Context, private val medals: List<Medal>) : BaseAdapter() {

    override fun getCount(): Int = medals.size

    override fun getItem(position: Int): Any = medals[position]

    override fun getItemId(position: Int): Long = medals[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_medal, parent, false)
        val medal = medals[position]

        val ivIcon = view.findViewById<ImageView>(R.id.ivMedalIcon)
        val tvName = view.findViewById<TextView>(R.id.tvMedalName)

        tvName.text = medal.name
        
        if (medal.isLocked) {
            view.alpha = 0.45f
            ivIcon.setImageResource(R.drawable.ic_medal_locked)
            ivIcon.clearColorFilter()
        } else {
            view.alpha = 1.0f
            ivIcon.setImageResource(medal.drawableRes)
            ivIcon.clearColorFilter()
        }

        return view
    }
}
