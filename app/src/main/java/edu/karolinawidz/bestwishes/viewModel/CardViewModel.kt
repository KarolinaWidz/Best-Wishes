package edu.karolinawidz.bestwishes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture
import edu.karolinawidz.bestwishes.model.Wish

class CardViewModel : ViewModel() {
    private val _selectedPictureId = MutableLiveData(-1)
    val selectedPictureId: LiveData<Int> get() = _selectedPictureId

    private val _selectedWishId = MutableLiveData(-1)
    val selectedWishId: LiveData<Int> get() = _selectedWishId

    private val _cardType = MutableLiveData<CardType>()
    val cardType: LiveData<CardType> get() = _cardType

    fun setSelectedPictureId(id: Int) {
        _selectedPictureId.value = id
    }

    fun setSelectedWishId(id: Int) {
        _selectedWishId.value = id
    }

    fun setCardType(cardType: CardType) {
        _cardType.value = cardType
    }

    fun filterPictureData(pictureDatasource: PictureDatasource): List<Picture> {
        return pictureDatasource.loadPictures().filter { it.type == cardType.value }
    }

    fun filterWishesData(wishDatasource: WishDatasource): List<Wish> {
        return wishDatasource.loadWishes().filter { it.type == cardType.value }
    }

}