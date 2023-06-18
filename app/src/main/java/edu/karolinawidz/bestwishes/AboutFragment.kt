package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.karolinawidz.bestwishes.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)
    }
}