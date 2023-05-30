package com.android.githubclient.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.githubclient.databinding.ItemRepositoryBinding
import com.android.githubclient.domain.repo.ReposItemView
import com.android.githubclient.ui.presenters.RepositListPresenter

class RepositoriesRVAdapter(val presenter: RepositListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(vb.root), ReposItemView {
        override fun setName(text: String?) = with(vb) {
            tvName.text = text
        }

        override var pos = -1
    }
}