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
import edu.karolinawidz.bestwishes.enums.CardType
import edu.karolinawidz.bestwishes.enums.Position
import edu.karolinawidz.bestwishes.network.service.PictureApi
import edu.karolinawidz.bestwishes.ui.recyclerView.model.Picture
import edu.karolinawidz.bestwishes.ui.recyclerView.model.Wish
import kotlinx.coroutines.launch
import java.util.UUID

private const val TAG = "CardViewModel"

enum class PictureApiStatus { LOADING, ERROR, DONE }

class CardViewModel : ViewModel() {

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

    private val _status = MutableLiveData<PictureApiStatus>()
    val status: LiveData<PictureApiStatus> = _status

    private var pageNumber = 1

    fun setCardType(cardType: CardType) {
        _cardType = cardType
    }

    fun pictureItemClicked(picture: Picture) {
        val currentList = _pictureData.value.orEmpty()
        currentList.forEach { it.isSet = false }
        currentList.find { it == picture }?.let { it.isSet = true }
        _pictureData.value = currentList
    }

    fun wishItemClicked(wish: Wish) {
        val currentList = _wishData.value.orEmpty()
        currentList.forEach { it.isSet = false }
        currentList.find { it == wish }?.let { it.isSet = true }
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

    fun getImageFromPosition(): Boolean {
        return try {
            Log.i(TAG, "Picture selected")
            _pictureData.value?.first { it.isSet }
                ?.let { _pictureUri = it.imageUri }
            true
        } catch (e: NoSuchElementException) {
            Log.e(TAG, "No picture selected")
            false
        }
    }

    fun isWishSelected(): Boolean {
        _wishData.value?.let {
            return it.any { wish -> wish.isSet }
        }
        return false
    }

    fun getSelectedWish(): Int {
        return _wishData.value?.first { it.isSet }!!.stringResourceId
    }

    fun clearData() {
        _pictureUri = null
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
                    Log.i(TAG, "More pictures loaded")
                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "Cannot load more pictures: $e")
                _status.value = PictureApiStatus.ERROR
            }
        }
    }
}