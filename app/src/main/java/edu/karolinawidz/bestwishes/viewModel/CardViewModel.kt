package edu.karolinawidz.bestwishes.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture
import edu.karolinawidz.bestwishes.model.Wish
import java.util.*

private const val TAG = "CardViewModel"

class CardViewModel : ViewModel() {
    private var _selectedPictureId: String? = null
    val selectedPictureId get() = _selectedPictureId

    private var _selectedWishId: String? = null
    val selectedWishId get() = _selectedWishId

    private var _cardType = CardType.BIRTHDAY
    val cardType get() = _cardType

    private var _pictureData = MutableLiveData(
        PictureDatasource.loadPictures().filter { it.type == _cardType }
    )

    val pictureData get() = _pictureData

    private var _wishData = MutableLiveData(
        WishDatasource.loadWishes().filter { it.type == _cardType }
    )
    val wishData get() = _wishData

    private var _pictureUri: Uri? = null
    val pictureUri get() = _pictureUri!!

    private var _wishResourceId = -1
    val wishResourceId get() = _wishResourceId

    private fun setPictureUri(uri: Uri) {
        _pictureUri = uri
    }

    fun setCardType(cardType: CardType) {
        _cardType = cardType
    }

    fun pictureItemClicked(picture: Picture) {
        val currentList = _pictureData.value.orEmpty()
        currentList.forEach { it.isSet = false }
        currentList.find { it == picture }?.let { it.isSet = true }
        _selectedPictureId = picture.id
        _pictureData.value = currentList
    }

    fun wishItemClicked(wish: Wish) {
        val currentList = _wishData.value.orEmpty()
        currentList.forEach { it.isSet = false }
        currentList.find { it == wish }?.let { it.isSet = true }
        _selectedWishId = wish.id
        _wishData.value = currentList
    }

    fun findPreviousPictureItemClickedPos(): Int {
        return _pictureData.value!!.indexOfFirst { it.isSet }
    }

    fun findPreviousWishItemClickedPos(): Int {
        return _wishData.value!!.indexOfFirst { it.isSet }
    }

    fun addNewImage(uri: Uri, stringResourceId: Int) {
        val newPicture =
            Picture(UUID.randomUUID().toString(), stringResourceId, uri, _cardType, false)
        val currentList = _pictureData.value.orEmpty()
        _pictureData.value = listOf(newPicture) + currentList
    }

    fun getImageFromPosition() {
        try {
            Log.i(TAG, "Picture selected")
            _pictureData.value?.first { it.id == _selectedPictureId }
                ?.let { setPictureUri(it.imageUri) }
        } catch (e: NoSuchElementException) {
            Log.e(TAG, "No picture selected")
        }
    }

    fun getWishFromPosition() {
        try {
            Log.i(TAG, "Wish selected")
            _wishResourceId = _wishData.value?.first { it.id == _selectedWishId }!!.stringResourceId
        } catch (e: NoSuchElementException) {
            Log.e(TAG, "No wish selected")
        }
    }

    fun clearData() {
        _selectedPictureId = null
        _selectedWishId = null
        _pictureUri = null
        _wishResourceId = -1
        _wishData.value = WishDatasource.loadWishes().filter { it.type == cardType }
        _pictureData.value = PictureDatasource.loadPictures().filter { it.type == _cardType }
    }
}