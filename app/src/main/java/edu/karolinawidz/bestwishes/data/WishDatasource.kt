package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Wish

class WishDatasource {
    fun loadWishes(): List<Wish> {
        return listOf(
            Wish(R.string.birthday1),
            Wish(R.string.birthday2),
            Wish(R.string.birthday3),
            Wish(R.string.birthday4),
            Wish(R.string.birthday5),
            Wish(R.string.birthday6),
            Wish(R.string.birthday7),
            Wish(R.string.birthday8),
            Wish(R.string.birthday9),
            Wish(R.string.birthday10),
            Wish(R.string.birthday11),
            Wish(R.string.birthday12),
            Wish(R.string.birthday13)
        )
    }
}