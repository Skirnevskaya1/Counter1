package com.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.githubclient.App
import com.android.githubclient.mvp.model.entity.GithubRepository
import com.android.githubclient.mvp.presenter.RepositoryPresenter
import com.android.githubclient.mvp.view.RepositoryView
import com.android.githubclient.ui.activity.BackButtonListener
import com.gb.githubclient.databinding.FragmentRepositoryBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository

        RepositoryPresenter(repository, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {}

    override fun setId(text: String) {
        binding.tvId.text = text
    }

    override fun setTitle(text: String) {
        binding.tvTitle.text = text
    }

    override fun setForksCount(text: String) {
        binding.tvForksCount.text = text
    }

    override fun backPressed() = presenter.backPressed()
}