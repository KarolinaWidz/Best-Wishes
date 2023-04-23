package edu.karolinawidz.bestwishes.viewModel

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.enum.Position
import edu.karolinawidz.bestwishes.model.listItems.Picture
import edu.karolinawidz.bestwishes.model.listItems.Wish
import edu.karolinawidz.bestwishes.services.PictureApi
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "CardViewModel"

enum class PictureApiStatus { LOADING, ERROR, DONE }

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

    val pictureData: LiveData<List<Picture>> = _pictureData

    private var _wishData = MutableLiveData(
        WishDatasource.loadWishes().filter { it.type == _cardType }
    )
    val wishData: LiveData<List<Wish>> = _wishData

    private var _pictureUri: Uri? = null
    val pictureUri get() = _pictureUri!!

    private var _wishResourceId = -1
    val wishResourceId get() = _wishResourceId

    private val _resultList = MutableLiveData<String>()
    val resultList: LiveData<String> = _resultList

    private val _status = MutableLiveData<PictureApiStatus>()
    val status: LiveData<PictureApiStatus> = _status

    private var pageNumber = 1

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

    fun addNewImage(uri: Uri, pictureDescription: String, position: Position) {
        val newPicture =
            Picture(UUID.randomUUID().toString(), pictureDescription, uri, _cardType, false)
        val currentList = _pictureData.value.orEmpty()
        when (position) {
            Position.TOP -> _pictureData.value = listOf(newPicture) + currentList
            Position.BOTTOM -> _pictureData.value = currentList + listOf(newPicture)
        }
    }

    private fun loadNewImagesFromUrl(url: String?, pictureDescription: String) {
        url?.let {
            val imgUri = url.toUri().buildUpon().scheme("https").build()
            addNewImage(imgUri, pictureDescription, Position.BOTTOM)
        }
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
        _wishData.value = WishDatasource.loadWishes().filter { it.type == _cardType }
        _pictureData.value = PictureDatasource.loadPictures().filter { it.type == _cardType }
        pageNumber = 1
    }

    fun loadPicturesFromApi() {
        viewModelScope.launch {
            _status.value = PictureApiStatus.LOADING
            try {
                val result =
                    PictureApi.retrofitService.getPictures(pageNumber, _cardType.name.lowercase())
                for (photo in result.photos) {
                    loadNewImagesFromUrl(photo.src.large, photo.alt)
                    pageNumber++
                    _status.value = PictureApiStatus.DONE
                    Log.i(TAG, "More images loaded")
                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "Cannot load more images: $e")
                _status.value = PictureApiStatus.ERROR
            }
        }
    }

}