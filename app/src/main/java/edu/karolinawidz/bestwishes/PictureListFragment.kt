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
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            goToNextScreen(it, binding.recyclerView.adapter as PictureItemAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter =
            PictureItemAdapter(requireContext(), PictureDatasource().loadPictures())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun goToNextScreen(view: View, adapter: PictureItemAdapter) {
        if (adapter.selectedItemPosition != -1) {
            view.findNavController()
                .navigate(
                    PictureListFragmentDirections.actionPictureListFragmentToWishFragment(
                        pictureId = adapter.getImageFromPosition()!!
                    )
                )
        } else {
            showNoPictureSelectedToast()
        }
    }

    private fun showNoPictureSelectedToast() {
        val toast = Toast.makeText(requireContext(), "No picture selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 220)
        toast.show()
    }


}