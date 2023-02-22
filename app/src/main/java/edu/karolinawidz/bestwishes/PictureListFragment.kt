package edu.karolinawidz.bestwishes

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import edu.karolinawidz.bestwishes.adapter.PictureItemAdapter
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentPictureListBinding
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture


class PictureListFragment : Fragment() {
    companion object {
        private const val CARD_TYPE = "cardType"
    }

    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardType: CardType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardType = getArguments(it)
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
        binding.nextButton.setOnClickListener {
            goToNextScreen(it, binding.recyclerView.adapter as PictureItemAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getArguments(bundle: Bundle): CardType {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(CARD_TYPE, CardType::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            bundle.getSerializable(CARD_TYPE) as CardType
        }
    }

    private fun initData() {
        requireActivity().title = getString(R.string.select_picture)
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter =
            PictureItemAdapter(requireContext(), loadPictureData(cardType))
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun loadPictureData(type: CardType): List<Picture> {
        return PictureDatasource().loadPictures().filter { it.type == type }
    }

    private fun goToNextScreen(view: View, adapter: PictureItemAdapter) {
        if (adapter.selectedItemPosition != -1) {
            view.findNavController()
                .navigate(
                    PictureListFragmentDirections.actionPictureListFragmentToWishFragment(
                        pictureId = adapter.getImageFromPosition()!!, cardType = cardType
                    )
                )
        } else {
            showNoPictureSelectedToast()
        }
    }

    private fun showNoPictureSelectedToast() {
        val toast = Toast.makeText(requireContext(), "No picture selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 220)
        toast.show()
    }


}