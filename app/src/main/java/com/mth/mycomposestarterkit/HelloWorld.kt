package com.mth.mycomposestarterkit

/**
 * @Author myothiha
 * Created 12/01/2024 at 12:37 PM.
 **/
fun main() {
    val users = mutableListOf(
        User(
            id = 11,
            name = "Myo Thiha"
        ),
        User(
            id = 12,
            name = "Phyo Thiha Win"
        ),
        User(
            id = 13,
            name = "Zin Min Tun"
        )
    )

    val ownedByPeople = calculateExpense()

    ownedByPeople.keys.toMutableList()
        .groupBy {
            it.expenseId
        }.values
        .forEach { users ->
            users.forEach {
                print("${it.name} \n")
            }
        }

    /*ownedByPeople.forEach { (user, amount) ->
        //print("${user.name} ${if (user.paidAmt != 0) "owe" else "owes"} $amount \n")
    }*/
}

fun getExpenseList(): MutableList<Expense> {
    return mutableListOf(
        Expense(
            id = 1,
            description = "Toilet Cleaner",
            amount = 2000,
            paidBy = User(
                id = 11,
                name = "Myo Thiha",
                paidAmt = 2000,
                expenseId = 1
            ),
            borrowBy = mutableListOf(
                User(
                    id = 12,
                    name = "Phyo Thiha Win",
                    expenseId = 1
                ),
                User(
                    id = 13,
                    name = "Zin Min Tun",
                    expenseId = 1
                )
            )
        ),
        Expense(
            id = 2,
            description = "Dish Cleaner",
            amount = 5000,
            paidBy = User(
                id = 12,
                name = "Phyo Thiha Win",
                paidAmt = 5000,
                expenseId = 2
            ),
            borrowBy = mutableListOf(
                User(
                    id = 11,
                    name = "Myo Thiha",
                    expenseId = 2
                ),
                User(
                    id = 13,
                    name = "Zin Min Tun",
                    expenseId = 2
                )
            )
        )
    )
}

fun calculateExpense(): Map<User, Int> {
    val expenses = getExpenseList()

    val shareExpensePerPerson = mutableMapOf<User, Int>()

    expenses.forEachIndexed { index, expense ->
        //Total Group Members
        val totalParticipants = expense.borrowBy.size + 1
        // Expense Cost per one
        val shareAmount = expense.amount / totalParticipants
        // expense cost who paid
        shareExpensePerPerson[expense.paidBy] = shareAmount
        //expense cost who need to pay
        expense.borrowBy.forEach {
            shareExpensePerPerson[it] = shareAmount
        }
    }

    return shareExpensePerPerson
}

fun calculateOtherExpense() {
    val expenses = getExpenseList()

    expenses
        .forEachIndexed { index, expense ->
            //Total Group Members
            val totalParticipants = expense.borrowBy.size + 1
            // Expense Cost per one
            val shareAmount = expense.amount / totalParticipants
            val paidBy = expense.paidBy.id
            if (paidBy == 11) {
                expenses[index] =
                    expense.copy(paidBy = expense.paidBy.copy(shareAmt = expense.amount - shareAmount))
            } else {
                val borrowBy = expense.borrowBy.forEach {

                }
                //expenses[index] = expense.copy(borrowBy =)
            }

        }
}

data class User(
    val id: Int,
    val name: String,
    val paidAmt: Int = 0,
    val shareAmt: Int = 0,
    val expenseId: Int = 0
)

data class Expense(
    val id: Int,
    val description: String,
    val amount: Int,
    val paidBy: User,
    val borrowBy: List<User>
)

