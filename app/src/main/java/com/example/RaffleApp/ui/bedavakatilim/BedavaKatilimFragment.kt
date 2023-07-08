package com.example.RaffleApp.ui.bedavakatilim

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
import com.example.RaffleApp.databinding.FragmentBedavaKatilimBinding

class BedavaKatilimFragment : Fragment() {

    private var _binding: FragmentBedavaKatilimBinding? = null

    private val binding get() = _binding!!
    private lateinit var bedavaKatilimViewModel: BedavaKatilimViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bedavaKatilimViewModel =
            ViewModelProvider(this).get(BedavaKatilimViewModel::class.java)

        _binding = FragmentBedavaKatilimBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bedavaKatilimViewModel.getbedavaKatilimCekilis(requireContext())

        bedavaKatilimViewModel.bedavaKatilimCekilis.observe(viewLifecycleOwner) {
            binding.bedavaKatilimRecyclerView.layoutManager = LinearLayoutManager(context)
            val bedavaKatilimAdapter = GeneralAdapter(
                it,
                R.id.action_nav_bedava_katilim_to_nav_detail,
                findNavController()
            )
            binding.bedavaKatilimRecyclerView.adapter = bedavaKatilimAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}