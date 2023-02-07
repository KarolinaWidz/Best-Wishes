package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import edu.karolinawidz.bestwishes.adapter.PictureItemAdapter
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentPictureListBinding


class PictureListFragment : Fragment() {
    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureListBinding.inflate(inflater, container, false)

        val dataset = PictureDatasource().loadPictures()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = PictureItemAdapter(requireContext(), dataset)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.setHasFixedSize(true)
        binding.nextButton.setOnClickListener {
            it.findNavController()
                .navigate(PictureListFragmentDirections.actionPictureListFragmentToWishFragment())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}