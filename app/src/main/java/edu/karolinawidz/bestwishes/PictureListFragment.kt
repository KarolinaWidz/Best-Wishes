package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
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
        val adapter = PictureItemAdapter(requireContext(), dataset)
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.nextButton.setOnClickListener {
            if (adapter.selectedItemPosition != -1) {
                it.findNavController()
                    .navigate(
                        PictureListFragmentDirections.actionPictureListFragmentToWishFragment(
                            pictureId = adapter.getImageFromPosition()!!
                        )
                    )
            } else {
                val toast =
                    Toast.makeText(requireContext(), "No picture selected", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.BOTTOM, 0, 220)
                toast.show()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}