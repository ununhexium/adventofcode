package more

import java.lang.String.format

fun Int.format(digits: Int) = format("%${digits}d", this)

fun Int.formattedHexadecimal(
    digits: Int,
    padding: Char = '0'
) = format("%$padding${digits}x", this)