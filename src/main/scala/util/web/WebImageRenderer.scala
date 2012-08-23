package util.web

import org.apache.commons.codec.digest.DigestUtils

object WebImageRenderer{
  val apiKey = "P5035480CECD5B"
  val privateKey = "S51274CFF48B40"
  val bounds = "300x300"


  def signatureFor(url: String) = DigestUtils.md5Hex((privateKey + url).getBytes("UTF-8"))

  def imgageUrl(url: String) = List("http://api.url2png.com/v3", apiKey, signatureFor(url), bounds, url).mkString("/")
}
