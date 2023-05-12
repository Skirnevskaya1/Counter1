package com.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.githubclient.mvp.model.entity.GithubRepository
import com.android.githubclient.mvp.presenter.RepositoryPresenter
import com.android.githubclient.mvp.view.RepositoryView
import com.android.githubclient.ui.activity.BackButtonListener
import com.android.githubclient.databinding.FragmentRepositoryBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private var vb: FragmentRepositoryBinding? = null

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun init() {}

    override fun setId(text: String) {
        vb?.tvId?.text = text
    }

    override fun setTitle(text: String) {
        vb?.tvTitle?.text = text
    }

    override fun setForksCount(text: String) {
        vb?.tvForksCount?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}