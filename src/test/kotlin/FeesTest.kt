import org.junit.Test
import kotlin.test.assertEquals

class FeesTest {

    @Test
    fun cardComission() {
        val type = "Visa"
        val monthTotal = 100000
        val transfer = 100000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(750, result)
    }
    @Test
    fun wrongTypeCard() {
        val type = "Union Pay"
        val monthTotal = 100000
        val transfer = 100000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(-1, result)
    }
    @Test
    fun maestroType() {
        val type = "Maestro"
        val monthTotal = 80_000
        val transfer = 6_000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(56, result)
    }
    @Test
    fun vkpayType() {
        val type = "VK Pay"
        val monthTotal = 30_000
        val transfer = 2000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(0, result)
    }
    @Test
    fun wrongLimits() {
        val type = "MIR"
        val monthTotal = 650_000
        val transfer = 615_000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(-1, result)
    }
    @Test
    fun lowVisaFee() {
        val type = "Visa"
        val monthTotal = 50_000
        val transfer = 1_000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(35, result)
    }
    @Test
    fun mastercardToLimit() {
        val type = "Mastercard"
        val monthTotal = 70_000
        val transfer = 10_000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(0, result)
    }
    @Test
    fun wrongDayLimits() {
        val type = "Maestro"
        val monthTotal = 200_000
        val transfer = 160_000

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(-1, result)
    }
    @Test
    fun positiveTransferSum() {
        val type = "Maestro"
        val monthTotal = -100
        val transfer = 0

        val result = feeCalc(type, monthTotal, transfer)

        assertEquals(-1, result)
    }
}