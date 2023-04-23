package edu.karolinawidz.bestwishes.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.util.PictureGenerator
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess

class FinalCardFragment : Fragment() {

    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentFinalCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card = createCard()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            finalCardFragment = this@FinalCardFragment
            imageFinalPicture.setImageBitmap(card)
        }
    }

    fun goToMenuScreen() {
        cardViewModel.clearData()
        findNavController().navigate(R.id.action_finalCardFragment_to_menuFragment)

    }

    fun exitApp() {
        exitProcess(0)
    }

    private fun loadBitmapFromUri(): Bitmap {
        var bitmap: Bitmap
        runBlocking {
            bitmap = withContext(Dispatchers.IO) {
                Glide.with(this@FinalCardFragment)
                    .asBitmap()
                    .load(cardViewModel.pictureUri)
                    .submit()
                    .get()
            }
        }
        return bitmap
    }

    private fun createCard(): Bitmap? {
        return PictureGenerator.createCard(
            loadBitmapFromUri(),
            resources.getText(cardViewModel.wishResourceId),
            cardViewModel.cardType.heading,
            ResourcesCompat.getFont(requireContext(), R.font.card_firstschool),
            ContextCompat.getColor(requireContext(), R.color.final_font_color)
        )
    }

    private fun shareCard(uri: Uri) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpeg"
        }
        val title = resources.getString(R.string.share_title)
        val chooser = Intent.createChooser(intent, title)
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            ToastUtil.showToast(requireContext(), R.string.cannot_share)
        }
    }
}