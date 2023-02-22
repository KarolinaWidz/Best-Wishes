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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import edu.karolinawidz.bestwishes.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Wish

class WishFragment : Fragment() {
    companion object {
        private const val PICTURE_ID = "pictureId"
        private const val CARD_TYPE = "cardType"
    }

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private var pictureId = 0
    private lateinit var cardType: CardType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pictureId = it.getInt(PICTURE_ID)
            cardType = getArguments(it)
        }
    }

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
        binding.nextButton.setOnClickListener {
            goToNextScreen(it, binding.recyclerView.adapter as WishItemAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        requireActivity().title = getString(R.string.adjust_wishes)
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter = WishItemAdapter(requireContext(), loadWishesData(cardType))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.imagePreviewPicture.setImageResource(pictureId)
    }

    private fun getArguments(bundle: Bundle): CardType {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(CARD_TYPE, CardType::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            bundle.getSerializable(CARD_TYPE) as CardType
        }
    }

    private fun loadWishesData(type: CardType): List<Wish> {
        return WishDatasource().loadWishes().filter { it.type == type }
    }

    private fun goToNextScreen(view: View, adapter: WishItemAdapter) {
        if (adapter.selectedItemPosition != -1) {
            view.findNavController()
                .navigate(
                    WishFragmentDirections.actionWishFragmentToFinalCardFragment(
                        pictureId,
                        adapter.getWishFromPosition()!!, cardType = cardType
                    )
                )
        } else {
            showNoWishSelectedToast()
        }
    }

    private fun showNoWishSelectedToast() {
        val toast =
            Toast.makeText(requireContext(), "No wish selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 220)
        toast.show()
    }

}