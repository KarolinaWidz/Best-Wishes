package edu.karolinawidz.bestwishes.ui

import android.content.res.Configuration
import android.net.Uri
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
import edu.karolinawidz.bestwishes.databinding.FragmentPictureListBinding
import edu.karolinawidz.bestwishes.enum.Position
import edu.karolinawidz.bestwishes.ui.adapter.PictureItemAdapter
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import edu.karolinawidz.bestwishes.viewModel.PictureApiStatus

const val PORTRAIT_COLUMNS = 2
const val LANDSCAPE_COLUMNS = 3

class PictureListFragment : Fragment() {

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!
    private lateinit var getContent: ActivityResultLauncher<String>
    private lateinit var adapter: PictureItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { addImageToList(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            pictureListFragment = this@PictureListFragment
            viewModel = cardViewModel
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
        adapter = PictureItemAdapter(requireContext(), recyclerView)
        recyclerView.adapter = adapter

        val spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) PORTRAIT_COLUMNS
            else LANDSCAPE_COLUMNS

        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)

        adapter.itemClickListener = { picture -> cardViewModel.pictureItemClicked(picture) }
        adapter.previousSelected = { cardViewModel.findPreviousPictureItemClickedPos() }
        adapter.loadMore = { cardViewModel.loadPicturesFromApi() }

        cardViewModel.pictureData.observe(viewLifecycleOwner) { adapter.submitList(it) }
        cardViewModel.status.observe(viewLifecycleOwner) {
            if (cardViewModel.status.value == PictureApiStatus.ERROR) ToastUtil.showToast(
                requireContext(),
                R.string.cannot_load_more_pictures
            )
        }
    }

    fun goToNextScreen() {
        if (cardViewModel.selectedPictureId != null) {
            cardViewModel.getImageFromPosition()
            findNavController().navigate(R.id.action_pictureListFragment_to_wishFragment)
        } else {
            ToastUtil.showToast(requireContext(), R.string.no_picture_selected)
        }
    }

    fun loadUserPhoto() {
        getContent.launch("image/*")
    }

    private fun addImageToList(uri: Uri?) {
        uri?.let {
            cardViewModel.addNewImage(it, resources.getString(R.string.user_image), Position.TOP)
            binding.recyclerView.postDelayed(
                { binding.recyclerView.smoothScrollToPosition(0) },
                1
            )
        }
    }
}