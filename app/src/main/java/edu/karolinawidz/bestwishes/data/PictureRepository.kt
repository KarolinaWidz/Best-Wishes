package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.enums.CardType
import edu.karolinawidz.bestwishes.network.service.PictureApiService
import javax.inject.Inject

class PictureRepository @Inject constructor(private val pictureApi: PictureApiService) {
    suspend fun getPicturesWithType(page: Int, cardType: CardType) =
        pictureApi.getPictures(page, cardType.name.lowercase()).photos

    fun getInitialPicturesWithType(cardType: CardType) =
        PictureDatasource.loadPictures().filter { it.type == cardType }
}