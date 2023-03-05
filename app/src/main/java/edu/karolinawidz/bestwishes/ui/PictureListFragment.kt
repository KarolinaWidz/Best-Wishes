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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.adapter.PictureItemAdapter
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentPictureListBinding
import edu.karolinawidz.bestwishes.model.Picture
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class PictureListFragment : Fragment() {
    companion object {
        private const val TOAST_OFFSET_X = 0
        private const val TOAST_OFFSET_Y = 220
    }

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureListBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = lifecycleOwner
            pictureListFragment = this@PictureListFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        cardViewModel.setPictureData(loadPictureData())
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter =
            PictureItemAdapter(cardViewModel, requireContext(), cardViewModel.pictureData)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun loadPictureData(): List<Picture> {
        return cardViewModel.filterPictureData(PictureDatasource())
    }

    fun goToNextScreen() {
        if (cardViewModel.selectedPictureId != -1) {
            cardViewModel.getImageFromPosition()
            findNavController().navigate(R.id.action_pictureListFragment_to_wishFragment)
        } else {
            showNoPictureSelectedToast()
        }
    }

    private fun showNoPictureSelectedToast() {
        val toast = Toast.makeText(requireContext(), "No picture selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, TOAST_OFFSET_X, TOAST_OFFSET_Y)
        toast.show()
    }
}