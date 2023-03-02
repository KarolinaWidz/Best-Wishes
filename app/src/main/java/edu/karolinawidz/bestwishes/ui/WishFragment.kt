package edu.karolinawidz.bestwishes.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding
import edu.karolinawidz.bestwishes.model.Wish
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class WishFragment : Fragment() {
    companion object {
        private const val TOAST_OFFSET_X = 0
        private const val TOAST_OFFSET_Y = 220
    }

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private val cardViewModel: CardViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener { goToNextScreen(binding.recyclerView.adapter as WishItemAdapter) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter = WishItemAdapter(cardViewModel, requireContext(), loadWishesData())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.imagePreviewPicture.setImageResource(cardViewModel.selectedPictureId)
    }

    private fun loadWishesData(): List<Wish> {
        return cardViewModel.filterWishesData(WishDatasource())
    }

    private fun goToNextScreen(adapter: WishItemAdapter) {
        if (cardViewModel.selectedWishId != -1) {
            adapter.setWishFromPosition()
            findNavController().navigate(R.id.action_wishFragment_to_finalCardFragment)
        } else {
            showNoWishSelectedToast()
        }
    }

    private fun showNoWishSelectedToast() {
        val toast =
            Toast.makeText(requireContext(), "No wish selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, TOAST_OFFSET_X, TOAST_OFFSET_Y)
        toast.show()
    }
}