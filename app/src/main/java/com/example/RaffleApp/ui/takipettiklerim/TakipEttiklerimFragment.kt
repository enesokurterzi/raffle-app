package com.example.RaffleApp.ui.takipettiklerim

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RaffleApp.R
import com.example.RaffleApp.adapter.GeneralAdapter
import com.example.RaffleApp.databinding.FragmentTakipEttiklerimBinding

class TakipEttiklerimFragment : Fragment() {

    private var _binding: FragmentTakipEttiklerimBinding? = null
    private val binding get() = _binding!!
    private lateinit var takipEttiklerimViewModel: TakipEttiklerimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        takipEttiklerimViewModel =
            ViewModelProvider(this).get(TakipEttiklerimViewModel::class.java)

        _binding = FragmentTakipEttiklerimBinding.inflate(inflater, container, false)
        val root: View = binding.root

        takipEttiklerimViewModel.getTakipEttiklerimCekilis(requireContext())

        takipEttiklerimViewModel.takipEttiklerimCekilis.observe(viewLifecycleOwner) {
            binding.takipEttiklerimRecyclerView.layoutManager = LinearLayoutManager(context)
            val takipEttiklerimAdapter = GeneralAdapter(
                it,
                R.id.action_nav_takip_ettiklerim_to_nav_detail,
                findNavController()
            )
            binding.takipEttiklerimRecyclerView.adapter = takipEttiklerimAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}