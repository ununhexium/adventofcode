package more

class CircularList<T>(c: Collection<T>) : ArrayList<T>(c)
{
  override fun get(index: Int) =
      super.get(index % this.size)

  override fun set(index: Int, element: T) =
      super.set(index % size, element)

  override fun subList(fromIndex: Int, toIndex: Int) =
      (fromIndex until toIndex).map { get(it) }.toMutableList()

  fun swap(start: Int, length: Int) =
      subList(start, start + length).reversed().forEachIndexed { index, t ->
        set(start + index, t)
      }
}