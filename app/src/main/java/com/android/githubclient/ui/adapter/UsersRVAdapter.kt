package com.android.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.android.githubclient.mvp.view.list.IUserItemView
import com.gb.githubclient.databinding.ItemUserBinding

class UsersRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) :
        RecyclerView.ViewHolder(vb.root), IUserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }
    }
}
