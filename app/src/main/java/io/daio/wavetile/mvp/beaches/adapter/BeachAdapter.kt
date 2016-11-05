package io.daio.wavetile.mvp.beaches.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.daio.wavetile.R
import io.daio.wavetile.api.model.Beach
import kotlinx.android.synthetic.main.beach_list_item.view.*


class BeachAdapter() : RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {

    private var beaches: List<Beach>? = null
    var selectListener: ((Beach?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeachViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.beach_list_item, parent, false)
        return BeachViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeachViewHolder?, position: Int) {
        val beach = beaches?.get(position)
        holder?.bindBeach(beach)
    }

    override fun getItemCount(): Int {
        return beaches?.count() ?: 0
    }

    fun setBeaches(beaches: List<Beach>?) {
        this.beaches = beaches
        notifyDataSetChanged()
    }

    fun savedBeachUpdated() {
        notifyDataSetChanged()
    }

    inner class BeachViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var beach: Beach? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bindBeach(beach: Beach?) {
            this.beach = beach
            itemView.beach_list_name.text = this.beach?.name
            itemView.beach_list_region.text = this.beach?.region
            itemView.beach_list_selected.isChecked = this.beach?.selected ?: false
        }

        override fun onClick(v: View?) {
            selectListener?.invoke(beach)
        }

    }
}