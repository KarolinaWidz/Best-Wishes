package edu.karolinawidz.bestwishes.viewModel

import androidx.lifecycle.ViewModel
import edu.karolinawidz.bestwishes.data.PictureDatasource
import edu.karolinawidz.bestwishes.data.WishDatasource
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture
import edu.karolinawidz.bestwishes.model.Wish

class CardViewModel : ViewModel() {
    private var _selectedPictureId = -1
    val selectedPictureId get() = _selectedPictureId

    private var _selectedWishId = -1
    val selectedWishId get() = _selectedWishId

    private lateinit var _cardType: CardType
    val cardType get() = _cardType

    fun setSelectedPictureId(id: Int) {
        _selectedPictureId = id
    }

    fun setSelectedWishId(id: Int) {
        _selectedWishId = id
    }

    fun setCardType(cardType: CardType) {
        _cardType = cardType
    }

    fun filterPictureData(pictureDatasource: PictureDatasource): List<Picture> {
        return pictureDatasource.loadPictures().filter { it.type == cardType }
    }

    fun filterWishesData(wishDatasource: WishDatasource): List<Wish> {
        return wishDatasource.loadWishes().filter { it.type == cardType }
    }

}