package com.example.RaffleApp.ui.arabakazan

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
import com.example.RaffleApp.databinding.FragmentArabaKazanBinding

class ArabaKazanFragment : Fragment() {

    private var _binding: FragmentArabaKazanBinding? = null
    private val binding get() = _binding!!
    private lateinit var arabaKazanViewModel: ArabaKazanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arabaKazanViewModel =
            ViewModelProvider(this).get(ArabaKazanViewModel::class.java)

        _binding = FragmentArabaKazanBinding.inflate(inflater, container,false)
        val root: View = binding.root

        arabaKazanViewModel.getArabaKazanCekilis(requireContext())

        arabaKazanViewModel.arabaKazanCekilis.observe(viewLifecycleOwner) {
            binding.arabaKazanRecyclerView.layoutManager = LinearLayoutManager(context)
            val arabaKazanAdapter = GeneralAdapter(
                it,
                R.id.action_nav_araba_kazan_to_nav_detail,
                findNavController()
            )
            binding.arabaKazanRecyclerView.adapter = arabaKazanAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}