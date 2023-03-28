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

private const val TAG = "CardViewModel"

class CardViewModel : ViewModel() {
    private var _selectedPictureId = -1
    val selectedPictureId get() = _selectedPictureId

    private var _selectedWishId = -1
    val selectedWishId get() = _selectedWishId

    private var _cardType = CardType.BIRTHDAY
    val cardType get() = _cardType

    private var _pictureData = MutableLiveData<List<Picture>>()
    val pictureData get() = _pictureData

    private lateinit var _wishData: List<Wish>
    val wishData get() = _wishData

    private var _pictureUri: Uri? = null
    val pictureUri get() = _pictureUri!!

    private var _wishResourceId = -1
    val wishResourceId get() = _wishResourceId

    fun setSelectedPictureId(id: Int) {
        _selectedPictureId = id
    }

    fun setSelectedWishId(id: Int) {
        _selectedWishId = id
    }

    private fun setPictureUri(uri: Uri) {
        _pictureUri = uri
    }

    private fun setWishResourceId(id: Int) {
        _wishResourceId = id
    }

    fun setCardType(cardType: CardType) {
        _cardType = cardType
    }

    fun addNewImage(uri: Uri, stringResourceId: Int) {
        val newPicture = Picture(stringResourceId, uri, _cardType)
        val currentList = _pictureData.value?.toMutableList()
        if (currentList == null) {
            _pictureData.value = (listOf(newPicture))
        } else {
            currentList.add(newPicture)
            _pictureData.value = (currentList)
        }

    }

    fun filterPictureData(pictureDatasource: PictureDatasource): List<Picture> {
        return pictureDatasource.loadPictures().value
            ?.filter { it.type == _cardType } as List<Picture>
    }

    fun filterWishesData(wishDatasource: WishDatasource): List<Wish> {
        return wishDatasource.loadWishes().filter { it.type == _cardType }
    }

    fun setPictureData(pictureList: List<Picture>) {
        _pictureData.value = pictureList
    }


    fun setWishData(wishList: List<Wish>) {
        _wishData = wishList
    }

    fun getImageFromPosition() {
        try {
            Log.i(TAG, "Picture selected")
            _pictureData.value?.get(_selectedPictureId)?.let { setPictureUri(it.imageUri) }
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "No picture selected")
        }
    }

    fun getWishFromPosition() {
        try {
            Log.i(TAG, "Wish selected")
            setWishResourceId(_wishData[_selectedWishId].stringResourceId)
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "No wish selected")
        }
    }

    fun clearData() {
        _selectedPictureId = -1
        _selectedWishId = -1
        _pictureUri = null
        _wishResourceId = -1
    }
}