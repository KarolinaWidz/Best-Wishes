package edu.karolinawidz.bestwishes.ui

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.util.PictureGenerator
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import kotlin.system.exitProcess


class FinalCardFragment : Fragment() {

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentFinalCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = lifecycleOwner
            finalCardFragment = this@FinalCardFragment
        }
        binding.imageFinalPicture.setImageBitmap(
            PictureGenerator.createCard(
                createBitmapFromResources(),
                resources.getText(cardViewModel.wishResourceId),
                cardViewModel.cardType.heading,
                ResourcesCompat.getFont(requireContext(), R.font.card_firstschool),
                ContextCompat.getColor(requireContext(), R.color.final_font_color)
            )
        )
    }

    fun goToMenuScreen() {
        cardViewModel.clearData()
        findNavController().navigate(R.id.action_finalCardFragment_to_menuFragment)
    }

    fun exitApp() {
        exitProcess(0)
    }

    private fun createBitmapFromResources(): Bitmap {
        val src =
            ImageDecoder.createSource(requireContext().contentResolver, cardViewModel.pictureUri)
        val resultImage = ImageDecoder.decodeBitmap(src)
        return resultImage.copy(Bitmap.Config.ARGB_8888, true)
    }
}