package more

import com.google.common.io.Resources
import java.io.File

object Input
{
  fun getFor(`package`: String): String
  {
    return Resources.toString(
        Resources.getResource(`package` + File.separator + "input.txt"),
        Charsets.UTF_8
    )
  }
}
