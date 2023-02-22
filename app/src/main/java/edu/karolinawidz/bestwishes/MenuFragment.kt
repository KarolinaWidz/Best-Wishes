package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import edu.karolinawidz.bestwishes.databinding.FragmentMenuBinding
import edu.karolinawidz.bestwishes.enum.CardType

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

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
        binding.birthdayCardButton.setOnClickListener {
            navigateToNext(it, CardType.BIRTHDAY)
        }
        binding.anniversaryCardButton.setOnClickListener {
            navigateToNext(it, CardType.ANNIVERSARY)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToNext(view: View, type: CardType) {
        view.findNavController()
            .navigate(MenuFragmentDirections.actionMenuFragmentToPictureListFragment(cardType = type))
    }
}