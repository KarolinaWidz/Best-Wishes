package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.utils.PictureGenerator

private const val PICTURE_ID = "pictureId"
private const val WISH_ID = "wishId"

class FinalCardFragment : Fragment() {

    private var _pictureId: Int? = null
    private var _wishId: Int? = null
    private var _binding: FragmentFinalCardBinding? = null
    private val pictureId get() = _pictureId!!
    private val wishId get() = _wishId!!
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _pictureId = it.getInt(PICTURE_ID)
            _wishId = it.getInt(WISH_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalCardBinding.inflate(inflater, container, false)
        binding.imageFinalPicture.setImageResource(pictureId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageFinalPicture.setImageBitmap(
            PictureGenerator.createCardBase(
                requireContext(),
                pictureId,
                wishId
            )
        )
    }
}