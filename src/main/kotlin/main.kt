fun main() {
    val accTN = "Maestro"
    val sumMTotal = 75000
    val sumT = 76000
    val transferCharge = feeCalc(accTN, sumMTotal, sumT)
    if (transferCharge == -1) println("Перевод выполнить нельзя!!!") else
        println("Перевод выполнен! Комиссия за перевод составляет $transferCharge руб.")
}

fun feeCalc(accType: String, sumMonthTotal: Int, sumTransfer: Int): Int {
    val limitFeeMasterMonth = 75_000
    var systemFee = 0
    if (isTransferApproved(accType, sumMonthTotal, sumTransfer)) {
        when (accType) {
            "VK Pay" -> systemFee = 0
            "Visa", "MIR" ->
                systemFee = if (sumTransfer * 0.75 / 100 >= 35) (sumTransfer * 0.75 / 100).toInt() else 35
            "Mastercard", "Maestro" ->
                systemFee = if (sumMonthTotal <= limitFeeMasterMonth) 0 else (sumTransfer * 0.6 / 100 + 20).toInt()
            // расчет комбинированной комиссии не сделан, т.е. когда часть перевода попадает в лимит 75000, а другая не попадает
        }
    } else systemFee = -1
    return systemFee
}

fun isTransferApproved(accType: String, sumMonthTotal: Int, sumTransfer: Int): Boolean {
    var limitForDay = 0
    var limitForMonth = 0
    var limitForTransfer = 0

    when (accType) {
        "VK Pay" -> {
            limitForTransfer = 15_000
            limitForDay = 40_000
            limitForMonth = 40_000
        }

        "Mastercard", "Maestro", "Visa", "MIR" -> {
            limitForTransfer = 600_000
            limitForDay = 150_000
            limitForMonth = 600_000
        }

        else -> limitForDay = -1
    }
    return sumMonthTotal < limitForMonth && sumTransfer <= limitForTransfer && sumTransfer <= limitForDay &&
            sumTransfer > 0 && sumMonthTotal >= 0
}
