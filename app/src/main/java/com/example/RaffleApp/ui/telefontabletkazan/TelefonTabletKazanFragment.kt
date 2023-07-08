package com.example.RaffleApp.ui.telefontabletkazan

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
import com.example.RaffleApp.databinding.FragmentTelefonTabletKazanBinding

class TelefonTabletKazanFragment : Fragment() {

    private var _binding: FragmentTelefonTabletKazanBinding? = null
    private val binding get() = _binding!!
    private lateinit var telefonTabletKazanWiewModel: TelefonTabletKazanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        telefonTabletKazanWiewModel =
            ViewModelProvider(this).get(TelefonTabletKazanViewModel::class.java)

        _binding = FragmentTelefonTabletKazanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        telefonTabletKazanWiewModel.getTelefonTabletKazanCekilis(requireContext())

        telefonTabletKazanWiewModel.telefonTabletKazanCekilis.observe(viewLifecycleOwner) {
            binding.telefonTabletKazanRecyclerView.layoutManager = LinearLayoutManager(context)
            val telefonTabletKazanAdapter = GeneralAdapter(
                it,
                R.id.action_nav_telefon_tablet_kazan_to_nav_detail,
                findNavController()
            )
            binding.telefonTabletKazanRecyclerView.adapter = telefonTabletKazanAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}