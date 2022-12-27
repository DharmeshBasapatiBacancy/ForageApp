package com.kudos.forageapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.forageapp.databinding.FragmentForageListBinding
import com.kudos.forageapp.view.adapters.ForageAdapter
import com.kudos.forageapp.viewmodel.ForageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgeListFragment : Fragment() {

    private lateinit var forageAdapter: ForageAdapter
    private lateinit var binding: FragmentForageListBinding

    private val forageViewModel: ForageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForageListBinding.inflate(layoutInflater)
        binding.apply {
            addFloatingActionButton.setOnClickListener {
                findNavController().navigate(
                    ForgeListFragmentDirections.actionForgeListFragmentToAddUpdateForgeFragment(
                        false,
                        0
                    )
                )
            }
        }
        setupForageList()
        observeForages()
        return binding.root
    }

    private fun observeForages() {
        lifecycleScope.launch {
            forageViewModel.allForages.observe(requireActivity()) {
                forageAdapter.submitList(it)
            }
        }
    }

    private fun setupForageList() {
        forageAdapter = ForageAdapter {
            findNavController().navigate(
                ForgeListFragmentDirections.actionForgeListFragmentToForgeDetailsFragment(
                    it.forageId
                )
            )
        }
        binding.forageRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = forageAdapter
        }
    }

}