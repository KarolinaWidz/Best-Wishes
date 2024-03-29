package edu.karolinawidz.bestwishes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding
import edu.karolinawidz.bestwishes.ui.recyclerView.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

@AndroidEntryPoint
class WishFragment : Fragment(R.layout.fragment_wish) {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private val cardViewModel: CardViewModel by activityViewModels()
    private lateinit var adapter: WishItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            wishFragment = this@WishFragment
        }
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false

        adapter = WishItemAdapter(requireContext(), recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Glide.with(this).load(cardViewModel.pictureUri).into(binding.imagePreviewPicture)
        cardViewModel.wishData.observe(viewLifecycleOwner) { adapter.submitList(it) }
        adapter.itemClickListener = { cardViewModel.wishItemClicked(it) }
        adapter.previousSelected = { cardViewModel.findPreviousWishItemClickedPos() }
    }

    fun goToNextScreen() {
        if (cardViewModel.isWishSelected()) {
            findNavController().navigate(R.id.action_wishFragment_to_finalCardFragment)
        } else {
            ToastUtil.showToast(requireContext(), R.string.no_wish_selected)
        }
    }
}