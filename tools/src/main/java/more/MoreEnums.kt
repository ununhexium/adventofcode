package more

/**
 * Generic way to list the values of an Enum
 */
inline fun <reified T> Enum<T>.listValues(): List<T> where T : Enum<T> =
    this.javaClass.enumConstants.map { it as T }

/**
 * Generic way to go to the X next or previous item in an enum.
 * If the index overflows, it loops.
 *
 * @return The enum constant [offset] indexes away.
 */
inline fun <reified T> Enum<T>.offset(offset: Int) where T : Enum<T> =
    listValues()[
        Math.floorMod(
            this.ordinal + offset,
            this.listValues().size
        )
        ]

/**
 * @return The next Enum entry
 */
inline fun <reified T> Enum<T>.previous() where T : Enum<T> =
    this.offset(-1)

/**
 * @return The more.previous Enum entry
 */
inline fun <reified T> Enum<T>.next() where T : Enum<T> =
    this.offset(+1)

