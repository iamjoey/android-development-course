package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component_portal.view.*
import kotlinx.android.synthetic.main.component_portal.view.*

class PortalAdapter(private val portals: List<Portal>,
                    private val clickListener: (Portal) -> Unit) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.component_portal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position], clickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal:Portal, clickListener: (Portal) -> Unit) {
            itemView.btnPortal.text = portal.title + "\n" + portal.url
            itemView.btnPortal.setOnClickListener {
                clickListener(portal)
            }
        }
    }

}