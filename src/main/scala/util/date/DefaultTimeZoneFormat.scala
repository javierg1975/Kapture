package util.date

import net.liftweb.json.{TypeHints, DateFormat, DefaultFormats}
import java.util.{TimeZone, Date}

trait DefaultTimeZoneFormat extends DefaultFormats {
  import java.text.{ParseException, SimpleDateFormat}

  override val dateFormat = new DateFormat {
    def parse(s: String) = try {
      Some(formatter.parse(s))
    } catch {
      case e: ParseException => None
    }

    def format(d: Date) = formatter.format(d)

    private def formatter = {
      val f = dateFormatter
      f.setTimeZone(TimeZoneFormat.defaultTimeZone)
      f
    }
  }

  protected override def dateFormatter = new SimpleDateFormat(TimeZoneFormat.defaultDateformatPattern)

  override def withHints(hints: TypeHints) = new DefaultFormats {
    override val typeHints = hints
  }
}

