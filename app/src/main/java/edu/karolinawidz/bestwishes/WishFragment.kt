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
    private var pictureId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pictureId = it.getInt(PICTURE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            goToNextScreen(it, binding.recyclerView.adapter as WishItemAdapter)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val recyclerView = binding.recyclerView
        val itemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        itemAnimator.supportsChangeAnimations = false
        recyclerView.adapter = WishItemAdapter(requireContext(), WishDatasource().loadWishes())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.imagePreviewPicture.setImageResource(pictureId)
    }

    private fun goToNextScreen(view: View, adapter: WishItemAdapter) {
        if (adapter.selectedItemPosition != -1) {
            view.findNavController()
                .navigate(
                    WishFragmentDirections.actionWishFragmentToFinalCardFragment(
                        pictureId,
                        adapter.getWishFromPosition()!!
                    )
                )
        } else {
            showNoWishSelectedToast()
        }
    }

    private fun showNoWishSelectedToast() {
        val toast =
            Toast.makeText(requireContext(), "No wish selected", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 220)
        toast.show()
    }

}