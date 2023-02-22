package edu.karolinawidz.bestwishes

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.util.PictureGenerator
import kotlin.system.exitProcess


class FinalCardFragment : Fragment() {

    companion object {
        private const val PICTURE_ID = "pictureId"
        private const val WISH_ID = "wishId"
        private const val CARD_TYPE = "cardType"
    }

    private var pictureId = 0
    private var wishId = 0
    private var _binding: FragmentFinalCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardType: CardType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pictureId = it.getInt(PICTURE_ID)
            wishId = it.getInt(WISH_ID)
            cardType = getArguments(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalCardBinding.inflate(inflater, container, false)
        binding.imageFinalPicture.setImageResource(pictureId)
        requireActivity().title = getString(R.string.final_results)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageFinalPicture.setImageBitmap(
            PictureGenerator.createCardBase(
                requireContext(),
                pictureId,
                wishId,
                cardType.heading
            )
        )
        binding.startOverButton.setOnClickListener {
            view.findNavController()
                .navigate(FinalCardFragmentDirections.actionFinalCardFragmentToMenuFragment())
        }
        binding.exitButton.setOnClickListener { exitProcess(0) }
    }

    private fun getArguments(bundle: Bundle): CardType {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(CARD_TYPE, CardType::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            bundle.getSerializable(CARD_TYPE) as CardType
        }
    }
}