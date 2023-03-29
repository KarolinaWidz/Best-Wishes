package edu.karolinawidz.bestwishes.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import edu.karolinawidz.bestwishes.util.PermissionRequest
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class PictureListFragment : Fragment() {

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!
    private lateinit var getContent: ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let { cardViewModel.addNewImage(it, R.string.user_image) }
        }
    }

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
        cardViewModel.pictureData.observe(viewLifecycleOwner) {
            it?.let {
                val adapter = binding.recyclerView.adapter as PictureItemAdapter
                adapter.updateListAfterInsert(it)
            }
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
            PictureItemAdapter(cardViewModel, requireContext(), cardViewModel.pictureData.value!!)
        recyclerView.setHasFixedSize(true)
        val spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
    }

    private fun loadPictureData(): List<Picture> {
        return cardViewModel.filterPictureData(PictureDatasource())
    }

    fun goToNextScreen() {
        if (cardViewModel.selectedPictureId != -1) {
            cardViewModel.getImageFromPosition()
            findNavController().navigate(R.id.action_pictureListFragment_to_wishFragment)
        } else {
            ToastUtil.showNoPictureSelectedToast(requireContext(), R.string.no_picture_selected)
        }
    }

    fun loadUserPhoto() {
        getContent.launch("image/*")
    }

    private fun showReadImagePermission() {
        PermissionRequest.requestReadImagePermission(requireContext(), requireActivity())
    }
}