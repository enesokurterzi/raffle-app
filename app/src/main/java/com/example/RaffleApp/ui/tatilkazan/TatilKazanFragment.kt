package com.example.RaffleApp.ui.tatilkazan

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
import com.example.RaffleApp.databinding.FragmentTatilKazanBinding

class TatilKazanFragment : Fragment() {

    private var _binding: FragmentTatilKazanBinding? = null
    private val binding get() = _binding!!
    private lateinit var tatilKazanViewModel: TatilKazanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tatilKazanViewModel =
            ViewModelProvider(this).get(TatilKazanViewModel::class.java)

        _binding = FragmentTatilKazanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tatilKazanViewModel.getTatilKazanCekilis(requireContext())

        tatilKazanViewModel.tatilKazanCekilis.observe(viewLifecycleOwner) {
            binding.tatilKazanRecyclerView.layoutManager = LinearLayoutManager(context)
            val tatilKazanAdapter = GeneralAdapter(
                it,
                R.id.action_nav_araba_kazan_to_nav_detail,
                findNavController()
            )
            binding.tatilKazanRecyclerView.adapter = tatilKazanAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}