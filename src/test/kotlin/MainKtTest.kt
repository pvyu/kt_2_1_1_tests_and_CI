import org.junit.Test

import org.junit.Assert.*

class MainKtTest {
    @Test
    fun testUnknownCard() {
        val cardType : CardType = CardType.Unknown
        val sum : Int = 1000
        val totalMonthSum : Int = 0

        val tax : Int = calcTransferTax( sum, totalMonthSum, cardType)

        assertEquals(UNKNOWN_CARD_ERROR, tax)
    }
    //--------------------------------------------------------------------

    @Test
    fun testTransferLimitError() {
        var cardType : CardType = CardType.Unknown
        var sum : Int = 0
        var totalMonthSum : Int = 0
        var tax : Int = 0

        cardType = CardType.Mastercard
        sum = 150_001
        totalMonthSum = 0
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(TRANSFER_LIMIT_ERROR, tax)

        cardType = CardType.Maestro
        sum = 150_001
        totalMonthSum = 0
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(TRANSFER_LIMIT_ERROR, tax)

        cardType = CardType.Visa
        sum = 150_001
        totalMonthSum = 0
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(TRANSFER_LIMIT_ERROR, tax)

        cardType = CardType.Mir
        sum = 150_001
        totalMonthSum = 0
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(TRANSFER_LIMIT_ERROR, tax)

        cardType = CardType.VK_Pay
        sum = 15_001
        totalMonthSum = 0
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(TRANSFER_LIMIT_ERROR, tax)
    }
    //--------------------------------------------------------------------

    @Test
    fun testMonthLimitError() {
        var cardType : CardType = CardType.Unknown
        var sum : Int = 0
        var totalMonthSum : Int = 0
        var tax : Int = 0

        cardType = CardType.Mastercard
        sum = 2_000
        totalMonthSum = 599_000
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(MONTH_LIMIT_ERROR, tax)

        cardType = CardType.Maestro
        sum = 2_000
        totalMonthSum = 599_000
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(MONTH_LIMIT_ERROR, tax)

        cardType = CardType.Visa
        sum = 2_000
        totalMonthSum = 599_000
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(MONTH_LIMIT_ERROR, tax)

        cardType = CardType.Mir
        sum = 2_000
        totalMonthSum = 599_000
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(MONTH_LIMIT_ERROR, tax)

        cardType = CardType.VK_Pay
        sum = 2_000
        totalMonthSum = 39_000
        tax = calcTransferTax( sum, totalMonthSum, cardType)
        assertEquals(MONTH_LIMIT_ERROR, tax)
    }
    //--------------------------------------------------------------------

    @Test
    fun testNoComission() {
        var cardType: CardType = CardType.Unknown
        var sum: Int = 0
        var totalMonthSum: Int = 0
        var tax: Int = 0

        cardType = CardType.Mastercard
        sum = 301
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(0, tax)

        cardType = CardType.Mastercard
        sum = 301
        totalMonthSum = 74_699
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(0, tax)


        cardType = CardType.Maestro
        sum = 301
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(0, tax)

        cardType = CardType.Maestro
        sum = 301
        totalMonthSum = 74_699
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(0, tax)

        cardType = CardType.VK_Pay
        sum = 1_000
        totalMonthSum = 1_000
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(0, tax)
    }
    //--------------------------------------------------------------------

    fun testComissionMasterMaes() {
        var cardType: CardType = CardType.Unknown
        var sum: Int = 0
        var totalMonthSum: Int = 0
        var tax: Int = 0

        cardType = CardType.Mastercard
        sum = 300
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(21, tax)

        cardType = CardType.Maestro
        sum = 300
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(21, tax)

        cardType = CardType.Mastercard
        sum = 100
        totalMonthSum = 75_000
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(470, tax)

        cardType = CardType.Maestro
        sum = 300
        totalMonthSum = 75_000
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(470, tax)

        cardType = CardType.Mastercard
        sum = 300
        totalMonthSum = 75_000
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(470, tax)

        cardType = CardType.Maestro
        sum = 300
        totalMonthSum = 75_000
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(470, tax)
    }
    //--------------------------------------------------------------------

    fun testComissionVisaMir() {
        var cardType: CardType = CardType.Unknown
        var sum: Int = 0
        var totalMonthSum: Int = 0
        var tax: Int = 0

        cardType = CardType.Visa
        sum = 100
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(35, tax)

        cardType = CardType.Visa
        sum = 10000
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(75, tax)

        cardType = CardType.Mir
        sum = 100
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(35, tax)

        cardType = CardType.Mir
        sum = 10000
        totalMonthSum = 0
        tax = calcTransferTax(sum, totalMonthSum, cardType)
        assertEquals(75, tax)
    }
}