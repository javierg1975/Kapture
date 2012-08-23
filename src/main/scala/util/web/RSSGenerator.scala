package util.web

import com.sun.syndication.feed.synd._
import core.{Kapture, User}
import util.date._
import dal.UserDAO
import scalaj.collection.Imports._
import java.io.StringWriter
import com.sun.syndication.io.SyndFeedOutput
import java.util.Date

object RSSGenerator {

  def kaptureRssEntry(kapture: Kapture): SyndEntryImpl = {
    val entry = new SyndEntryImpl()
    entry.setTitle(kapture.title)
    entry.setLink(kapture.contentUri)
    entry.setPublishedDate(new Date())

    val description = new SyndContentImpl()
    description.setType("text/html")
    description.setValue("<p>" + kapture.text + "</p>")
    entry.setDescription(description)

    entry
  }

  def feedForUser(user: User) = {
    val feed = new SyndFeedImpl()
    feed.setFeedType("atom_0.3")

    feed.setTitle("Kapture RSS feed for Lauren")
    feed.setLink("/rss")
    feed.setDescription("Sample Kapture feed")

    val entryList = new UserDAO(user).kaptures.map(kaptureRssEntry(_))

    feed.setEntries(entryList.asJava)

    val output = new SyndFeedOutput()
    output.outputString(feed)
  }

}
