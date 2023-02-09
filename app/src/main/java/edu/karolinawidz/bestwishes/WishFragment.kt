package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.karolinawidz.bestwishes.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding

const val PICTURE_ID = "pictureId"

class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private var pictureId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        val data = WishDatasource().loadWishes()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = WishItemAdapter(requireContext(), data)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setPreviewPicture()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setPreviewPicture() {
        arguments?.let {
            pictureId = it.getInt(PICTURE_ID)
        }
        binding.imagePreviewPicture.setImageResource(pictureId)
    }
}