package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.enums.CardType
import edu.karolinawidz.bestwishes.ui.recyclerView.model.Wish
import java.util.UUID

object WishDatasource {
    fun loadWishes(): List<Wish> {
        return listOf(
            Wish(UUID.randomUUID().toString(), R.string.birthday1, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday2, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday3, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday4, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday5, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday6, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday7, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday8, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday9, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday10, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday11, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday12, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.birthday13, CardType.BIRTHDAY, false),
            Wish(UUID.randomUUID().toString(), R.string.anniversary1, CardType.ANNIVERSARY, false),
            Wish(UUID.randomUUID().toString(), R.string.anniversary2, CardType.ANNIVERSARY, false),
            Wish(UUID.randomUUID().toString(), R.string.anniversary3, CardType.ANNIVERSARY, false),
            Wish(UUID.randomUUID().toString(), R.string.anniversary4, CardType.ANNIVERSARY, false),
            Wish(UUID.randomUUID().toString(), R.string.anniversary5, CardType.ANNIVERSARY, false),
        )
    }
}