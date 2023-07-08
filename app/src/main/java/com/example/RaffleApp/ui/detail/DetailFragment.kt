package com.example.RaffleApp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.RaffleApp.R
import com.example.RaffleApp.configs.Util
import com.example.RaffleApp.databinding.FragmentDetailBinding
import com.example.RaffleApp.models.Cekilis

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel
    private var cekilis: Cekilis = Util.chosen!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewSet()

        binding.followingBtn.setOnClickListener {
            cekilis.following = !cekilis.following!!
            buttonSet()
        }

        return root
    }

    override fun onStop() {
        detailViewModel.getByTitle(requireContext(), cekilis)
        super.onStop()
    }

    private fun viewSet() {
        binding.apply {
            cekilis.apply {
                detailTitle.text = title
                detailContent.text = detail
                startingDateTxt.text = startingDate
                endDateTxt.text = endDate
                drawDateTxt.text = drawDate
                listingDateTxt.text = listingDate
                minCostTxt.text = minCost
                totalGiftValueTxt.text = "Toplam Hediye Değeri : $giftValue"
                totalGiftCountTxt.text = giftCount

                Glide.with(requireContext()).load(img).into(detailImg)

                buttonSet()
            }
        }
    }

    private fun buttonSet() {
        if (cekilis.following!!) {
            binding.followingBtn.setImageResource(R.drawable.baseline_star_24)
        } else {
            binding.followingBtn.setImageResource(R.drawable.baseline_star_border_24)
        }
    }


}