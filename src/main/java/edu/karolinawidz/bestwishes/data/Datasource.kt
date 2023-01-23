package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Wish

class Datasource {
    fun loadWishes(): List<Wish> {
        return listOf(
            Wish(R.string.birthday1,R.drawable.birthday1),
            Wish(R.string.birthday2,R.drawable.birthday2),
            Wish(R.string.birthday3,R.drawable.birthday3),
            Wish(R.string.birthday4,R.drawable.birthday4),
            Wish(R.string.birthday5,R.drawable.birthday5),
            Wish(R.string.birthday6,R.drawable.birthday6),
            Wish(R.string.birthday7,R.drawable.birthday7),
            Wish(R.string.birthday8,R.drawable.birthday8),
            Wish(R.string.birthday9,R.drawable.birthday9),
            Wish(R.string.birthday10,R.drawable.birthday10),
            Wish(R.string.birthday11,R.drawable.birthday11),
            Wish(R.string.birthday12,R.drawable.birthday12),
            Wish(R.string.birthday13,R.drawable.birthday13),
            )
    }
}