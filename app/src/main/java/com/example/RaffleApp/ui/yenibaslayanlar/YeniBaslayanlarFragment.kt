package com.example.RaffleApp.ui.yenibaslayanlar

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
import com.example.RaffleApp.databinding.FragmentYeniBaslayanlarBinding

class YeniBaslayanlarFragment : Fragment() {

    private var _binding: FragmentYeniBaslayanlarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var yeniBaslayanlarViewModel: YeniBaslayanlarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        yeniBaslayanlarViewModel =
            ViewModelProvider(this).get(YeniBaslayanlarViewModel::class.java)

        _binding = FragmentYeniBaslayanlarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        yeniBaslayanlarViewModel.getYeniBaslayanlarCekilis(requireContext())

        yeniBaslayanlarViewModel.yeniBaslayanlarCekilis.observe(viewLifecycleOwner) {
            binding.yeniBaslayanlarRecyclerView.layoutManager = LinearLayoutManager(context)
            val yeniBaslayanlarAdapter = GeneralAdapter(
                it,
                R.id.action_nav_yeni_baslayanlar_to_nav_detail,
                findNavController()
            )
            binding.yeniBaslayanlarRecyclerView.adapter = yeniBaslayanlarAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}