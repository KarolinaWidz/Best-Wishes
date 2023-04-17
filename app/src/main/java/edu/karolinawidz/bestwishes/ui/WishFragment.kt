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
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding
import edu.karolinawidz.bestwishes.ui.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private val cardViewModel: CardViewModel by activityViewModels()
    private lateinit var adapter: WishItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        binding.apply {
            lifecycleOwner = lifecycleOwner
            wishFragment = this@WishFragment
        }
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

        binding.imagePreviewPicture.setImageURI(cardViewModel.pictureUri)
        cardViewModel.wishData.observe(viewLifecycleOwner) { adapter.submitList(it) }
        adapter.itemClickListener = { cardViewModel.wishItemClicked(it) }
        adapter.previousSelected = { cardViewModel.findPreviousWishItemClickedPos() }
    }

    fun goToNextScreen() {
        if (cardViewModel.selectedWishId != null) {
            cardViewModel.getWishFromPosition()
            findNavController().navigate(R.id.action_wishFragment_to_finalCardFragment)
        } else {
            ToastUtil.showNoPictureSelectedToast(requireContext(), R.string.no_wish_selected)
        }
    }
}