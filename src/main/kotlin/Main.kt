import kotlin.math.max

enum class CardType {Unknown, Mastercard, Maestro, Visa, Mir, VK_Pay}
//---------------------------------------------------------------------

const val TRANSFER_LIMIT_ERROR : Int = -1
const val MONTH_LIMIT_ERROR : Int = -2
const val UNKNOWN_CARD_ERROR : Int = -3
//---------------------------------------------------------------------

fun calcTransferTax(sum : Int, totalMonthSum : Int = 0, cardTypeFrom : CardType = CardType.VK_Pay) : Int {
    var result : Int = 0;

    val monthLimit_NoTaxMax_Master_Maestro : Int = 75_000
    val monthLimit_NoTaxMin_Master_Maestro : Int = 300

    val tax_percent_Master_Maestro : Double = 0.6
    val tax_fixed_Master_Maestro : Int = 20
    val tax_min_Master_Maestro : Int = 0

    val tax_percent_Visa_Mir : Double = 0.75
    val tax_fixed_Visa_Mir : Int = 0
    val tax_min_Visa_Mir : Int = 35

    val monthLimit_Card : Int = 600_000
    val transferLimit_Card : Int = 150_000

    val monthLimit_VK_Pay : Int = 40_000
    val transferLimit_VK_Pay : Int = 15_000

    var tax_percent : Double = 0.0
    var tax_fixed : Int = 0
    var tax_min : Int = 0
    var monthLimit : Int = 0
    var transferLimit : Int = 0

    val isKnownCard : Boolean = when (cardTypeFrom) {
        CardType.Mastercard, CardType.Maestro, CardType.Visa, CardType.Mir, CardType.VK_Pay -> true
        else -> false
    }
    if (!isKnownCard) {
        return UNKNOWN_CARD_ERROR;
    }

    if (cardTypeFrom == CardType.VK_Pay) {
        monthLimit = monthLimit_VK_Pay;
        transferLimit = transferLimit_VK_Pay;
    }
    else {
        monthLimit = monthLimit_Card;
        transferLimit = transferLimit_Card;
    }

    if (sum > transferLimit) {
        return TRANSFER_LIMIT_ERROR;
    }
    if (sum + totalMonthSum > monthLimit) {
        return MONTH_LIMIT_ERROR;
    }

    if (cardTypeFrom == CardType.Mastercard || cardTypeFrom == CardType.Maestro) {
        if ((sum < monthLimit_NoTaxMin_Master_Maestro) || (sum + totalMonthSum > monthLimit_NoTaxMax_Master_Maestro)) {
            tax_percent  = tax_percent_Master_Maestro
            tax_fixed = tax_fixed_Master_Maestro
        }
        tax_min = tax_min_Master_Maestro
    }
    else if (cardTypeFrom == CardType.Visa || cardTypeFrom == CardType.Mir) {
        tax_percent  = tax_percent_Visa_Mir
        tax_fixed = tax_fixed_Visa_Mir
        tax_min = tax_min_Visa_Mir
    }

    result = max((sum * tax_percent/100.0).toInt() + tax_fixed, tax_min)

    return result
}
//---------------------------------------------------------------------

fun main() {
    val tax : Int = calcTransferTax(500, 0, CardType.Maestro)
    println(tax)
}