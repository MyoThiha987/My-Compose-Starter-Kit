package com.mth.mycomposestarterkit.data

import com.mth.mycomposestarterkit.R

/**
 * @Author myothiha
 * Created 22/02/2024 at 11:15 AM.
 **/

fun getMovieList(): List<MovieVO> {
    return mutableListOf(
        MovieVO(
            id = 1,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 2,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 3,
            title = "No Time to die",
            imageUrl = "https://image.tmdb.org/t/p/original/eqWaeh21e4ZgHjwpULZVHCGIq9X.jpg",
            date = "November 2021"
        ),
        MovieVO(
            id = 4,
            title = "Shang Chi",
            imageUrl = "https://image.tmdb.org/t/p/original/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
            date = "November 2021"
        )
    )
}

fun getMenu(): List<MenuVO> {
    return mutableListOf(
        MenuVO(
            id = 1,
            name = "My Ticket",
            icon = R.drawable.ic_ticket,
        ),
        MenuVO(
            id = 2,
            name = "Payment history",
            icon = R.drawable.ic_shopping_cart,
        ),
        MenuVO(
            id = 3,
            name = "Change language",
            icon = R.drawable.ic_language,
        ),
        MenuVO(
            id = 4,
            name = "Change password",
            icon = R.drawable.ic_lock,
        ),
        MenuVO(
            id = 5,
            name = "Face ID/Touch ID",
            icon = R.drawable.ic_security,
        )
    )
}

fun getServices(): List<ServiceVO> {
    return mutableListOf(
        ServiceVO(
            id = 1,
            name = "Retal",
            image = R.drawable.retal_service,
        ),
        ServiceVO(
            id = 2,
            name = "Imax",
            image = R.drawable.imax_service,
        ),
        ServiceVO(
            id = 3,
            name = "4DX",
            image = R.drawable.dx_service,
        ),
        ServiceVO(
            id = 4,
            name = "Sweetbox",
            image = R.drawable.sweetbox_service,
        )
    )
}

fun getPersons(): List<PersonVO> {
    return mutableListOf(
        PersonVO(
            id = 1,
            name = "Anthony Russo",
            image = R.drawable.retal_service,
        ),
        PersonVO(
            id = 2,
            name = "Joe Russo",
            image = R.drawable.imax_service,
        ),
    )
}


fun getCinemas(): List<CinemaVO> {
    return mutableListOf(
        CinemaVO(
            id = 1,
            name = "Vincom Ocean Park CGV",
            image = R.drawable.cgv,
            distance = 14.3,
            address = "Da Ton, Gia Lam, Ha Noi",
            isClicked = true
        ),
        CinemaVO(
            id = 2,
            name = "Aeon Mall CGV",
            image = R.drawable.cgv,
            distance = 9.32,
            address = "27 Co Linh, Long Bien, Ha Noi"
        ),
        CinemaVO(
            id = 3,
            name = "Lotte Cinema Long Bien",
            image = R.drawable.lotte,
            distance = 14.3,
            address = "7-9 Nguyen Van Linh, Long Bien, Ha Noi"
        ),
    )
}

fun getSeats(): List<SeatVO> {
    val seats = mutableListOf<SeatVO>()

    val rows = 2..13
    val columns = 'A'..'J'

    for (column in columns) {
        for (row in rows) {
            seats.add(
                SeatVO(
                    id = (row - 2) * 10 + (column.code - 'A'.code) + 1,
                    no = "$column$row",
                    status = if("$column$row" == "A3") 2 else if("$column$row" == "J10") 1 else 0
                )
            )
        }
    }

    return seats
}

fun getAvailableDateTimeList(): List<DateTimeVO> {
    return mutableListOf(
        DateTimeVO(
            id = 0,
            month = "Dec",
            day = "01",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        ),
        DateTimeVO(
            id = 1,
            month = "Dec",
            day = "02",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        ),
        DateTimeVO(
            id = 2,
            month = "Dec",
            day = "03",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        ),
        DateTimeVO(
            id = 3,
            month = "Dec",
            day = "04",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        ),
        DateTimeVO(
            id = 4,
            month = "Dec",
            day = "05",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30"),
            isSelected = true
        ),
        DateTimeVO(
            id = 5,
            month = "Dec",
            day = "06",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        ),
        DateTimeVO(
            id = 6,
            month = "Dec",
            day = "07",
            time = listOf("09:30", "11:05", "14:15", "16:30", "20:05", "23:15", "01:30")
        )

    )
}

data class DateTimeVO(
    val id: Int,
    val month: String,
    val day: String,
    val time: List<String> = listOf(),
    var isSelected: Boolean = false
)

data class MenuVO(
    val id : Int,
    val name : String,
    val icon : Int
)

data class SeatVO(
    val id: Int,
    val no: String,
    val status : Int =0
)

data class CinemaVO(
    val id: Int,
    val name: String,
    val image: Int,
    val distance: Double,
    val address: String,
    var isClicked: Boolean = false
)

data class PersonVO(
    val id: Int,
    val name: String,
    val image: Int
)

data class ServiceVO(
    val id: Int,
    val name: String,
    val image: Int
)

data class MovieVO(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val date: String
)