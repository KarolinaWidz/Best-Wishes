package edu.karolinawidz.bestwishes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class WishFragment : Fragment() {

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
        binding.apply {
            lifecycleOwner = lifecycleOwner
            wishFragment = this@WishFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        cardViewModel.setWishData(loadWishesData())
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter =
            WishItemAdapter(cardViewModel, requireContext(), cardViewModel.wishData)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.imagePreviewPicture.setImageURI(cardViewModel.pictureUri)
    }

    private fun loadWishesData(): List<Wish> {
        return cardViewModel.filterWishesData(WishDatasource())
    }

    fun goToNextScreen() {
        if (cardViewModel.selectedWishId != -1) {
            cardViewModel.getWishFromPosition()
            findNavController().navigate(R.id.action_wishFragment_to_finalCardFragment)
        } else {
            ToastUtil.showNoPictureSelectedToast(requireContext(), R.string.no_wish_selected)
        }
    }
}