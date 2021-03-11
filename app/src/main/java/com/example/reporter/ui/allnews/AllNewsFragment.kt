package com.example.reporter.ui.allnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reporter.R
import com.example.reporter.databinding.FragmentAllNewsBinding
import com.example.reporter.model.retrofit.NewsInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllNewsFragment : Fragment() {

    private lateinit var binding : FragmentAllNewsBinding

    private lateinit var allNewsAdapter : AllNewsAdapter
    private lateinit var categoryAdapter : AllCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.playerFab.setOnClickListener {
            findNavController().navigate(R.id.action_allNewsFragment_to_playerHostFragment)
        }
        setupCategoriesRecyclerView()
        setupAllNewsRecyclerView()
    }

    private fun setupAllNewsRecyclerView() {
        allNewsAdapter = AllNewsAdapter()

        binding.rvAllNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allNewsAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            val news = NewsInterface.getInstance().getNews("top-headlines").body()

            withContext(Dispatchers.Main) {
                allNewsAdapter.submitList(news?.articles)

            }

        }
    }

    private fun setupCategoriesRecyclerView() {
        categoryAdapter = AllCategoriesAdapter()
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        categoryAdapter.submitList(resources.getStringArray(R.array.categories).asList())
    }
}