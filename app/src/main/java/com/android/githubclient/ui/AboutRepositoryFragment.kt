package com.android.githubclient.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.githubclient.databinding.FragmentRepositoryBinding
import com.android.githubclient.entity.GithubRepository

const val REPOSITORY = "repository"

@Suppress("DEPRECATION")
class AboutRepositoryFragment : Fragment() {
    private var binding: FragmentRepositoryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = arguments?.getParcelable(REPOSITORY) as GithubRepository?

        repository?.apply {
            binding?.apply {

                tvId.text = id
                tvTitle.text = name
                tvForksCount.text = forksCount.toString()
            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): AboutRepositoryFragment {
            val fragment = AboutRepositoryFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}