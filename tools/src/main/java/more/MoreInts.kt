package more

fun Int.format(digits: Int) = java.lang.String.format("%${digits}d", this)