package edu.karolinawidz.bestwishes

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import edu.karolinawidz.bestwishes.adapter.WishItemAdapter
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.databinding.FragmentWishBinding

private const val PICTURE_ID = "pictureId"
class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private var _pictureId: Int? = null
    private val pictureId get() = _pictureId!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _pictureId = it.getInt(PICTURE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        val data = WishDatasource().loadWishes()
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        val adapter = WishItemAdapter(requireContext(), data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.imagePreviewPicture.setImageResource(pictureId)
        binding.nextButton.setOnClickListener {
            if (adapter.selectedItemPosition != -1) {
                it.findNavController()
                    .navigate(
                        WishFragmentDirections.actionWishFragmentToFinalCardFragment(
                            pictureId,
                            adapter.getWishFromPosition()!!
                        )
                    )
            } else {
                val toast =
                    Toast.makeText(requireContext(), "No wish selected", Toast.LENGTH_LONG)
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