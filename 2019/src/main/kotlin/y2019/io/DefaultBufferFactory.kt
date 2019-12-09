package y2019.io

object DefaultBufferFactory {
  fun create(defaultValue: Int? = null): DefaultBuffer {
    return DefaultBuffer(defaultValue)
  }
}