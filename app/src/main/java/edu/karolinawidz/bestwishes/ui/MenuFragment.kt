package edu.karolinawidz.bestwishes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.databinding.FragmentMenuBinding
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val cardViewModel: CardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.birthdayCardButton.setOnClickListener { navigateToNext(CardType.BIRTHDAY) }
        binding.anniversaryCardButton.setOnClickListener { navigateToNext(CardType.ANNIVERSARY) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToNext(type: CardType) {
        cardViewModel.setCardType(type)
        findNavController().navigate(R.id.action_menuFragment_to_pictureListFragment)
    }
}