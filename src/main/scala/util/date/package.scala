package util

import java.util.{TimeZone, Date}
import java.sql.Timestamp

package object date{
  implicit def dateTime2JavaUtilDate(dt: DateTime): Date = new Date(dt.timeInstant(TimeZoneFormat.defaultTimeZone))
  implicit def int2TimeUnit(i: Int): DateTimeUnit = DateTimeUnit(i)

  object TimeZoneFormat extends DefaultTimeZoneFormat  {
    val defaultTimeZone = TimeZone.getDefault
    val defaultDateformatPattern = "yyyy-MM-dd"
  }
}