package com.example.RaffleApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RaffleApp.R
import com.example.RaffleApp.adapter.GeneralAdapter
import com.example.RaffleApp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        homeViewModel.getHomeCekilis(requireContext())

        homeViewModel.homeCekilis.observe(viewLifecycleOwner) {
            binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
            val homeAdapter = GeneralAdapter(
                it,
                R.id.action_nav_home_to_nav_detail,
                findNavController()
            )
            binding.homeRecyclerView.adapter = homeAdapter
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}