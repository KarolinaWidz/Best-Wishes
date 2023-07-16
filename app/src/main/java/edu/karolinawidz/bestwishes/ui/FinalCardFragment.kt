package edu.karolinawidz.bestwishes.ui

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.databinding.FragmentFinalCardBinding
import edu.karolinawidz.bestwishes.util.PictureGenerator
import edu.karolinawidz.bestwishes.util.ToastUtil
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class FinalCardFragment :
    Fragment(R.layout.fragment_final_card) {

    @Inject
    lateinit var ioDispatcher: CoroutineDispatcher
    private val cardViewModel: CardViewModel by activityViewModels()
    private var _binding: FragmentFinalCardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFinalCardBinding.bind(view)
        val card = createCard()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            finalCardFragment = this@FinalCardFragment
            imageFinalPicture.setImageBitmap(card)
            shareButton.setOnClickListener { shareCard(card) }
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
            bitmap = withContext(ioDispatcher) {
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
            resources.getText(cardViewModel.getSelectedWish()),
            cardViewModel.cardType.heading,
            ResourcesCompat.getFont(requireContext(), R.font.card_firstschool),
            ContextCompat.getColor(requireContext(), R.color.final_font_color)
        )
    }

    private fun shareCard(card: Bitmap?) {
        card?.let { bitmap -> getBitmapUri(bitmap)?.let { uri -> shareImage(uri) } }
    }

    private fun getBitmapUri(bitmap: Bitmap): Uri? {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, System.currentTimeMillis())
            put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis())
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        }

        val uri = activity?.contentResolver
            ?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        uri?.let { imageUri ->
            activity?.contentResolver?.openOutputStream(imageUri)?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                it.close()
                ToastUtil.showToast(requireContext(), R.string.picture_saved_in_gallery)
            }
        }
        return uri
    }

    private fun shareImage(uri: Uri) {
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