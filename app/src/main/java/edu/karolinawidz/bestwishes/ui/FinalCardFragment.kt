package edu.karolinawidz.bestwishes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.util.PictureGenerator
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import kotlin.system.exitProcess

class FinalCardFragment : Fragment() {

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentFinalCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalCardBinding.inflate(inflater, container, false)
        binding.imageFinalPicture.setImageResource(cardViewModel.selectedPictureId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageFinalPicture.setImageBitmap(
            PictureGenerator.createCard(
                requireContext(),
                cardViewModel.selectedPictureId,
                cardViewModel.selectedWishId,
                cardViewModel.cardType.heading
            )
        )
        binding.startOverButton.setOnClickListener {
            view.findNavController()
                .navigate(FinalCardFragmentDirections.actionFinalCardFragmentToMenuFragment())
        }
        binding.exitButton.setOnClickListener { exitProcess(0) }
    }
}