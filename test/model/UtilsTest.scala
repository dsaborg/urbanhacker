package model

import java.time.ZonedDateTime

import model.Utils.parseInternetDateTime
import org.scalatest._

class UtilsTest extends FlatSpec with Matchers {
  "Permalink.parseUrlTimestamp" should "parse URL date time" in {
    Permalink.parseUrlTimestamp("20160705T201649.882Z") shouldBe Some(ZonedDateTime.parse("2016-07-05T20:16:49.882Z"))
  }

  "Permalink.urlTimestamp" should "format URL date time" in {
    Permalink(ZonedDateTime.parse("2016-07-05T20:16:49.882Z"), 1).urlTimestamp shouldBe "20160705T201649.882Z"
  }

  "Utils.parseInternetDateTime" should "parse incorrect weekdays" in {
    parseInternetDateTime("Thu, 06 Apr 2016 15:00:00 +0300") shouldBe Right(ZonedDateTime.parse("2016-04-06T12:00:00Z"))
  }

  it should "parse RFC 1123 with incorrect weekdays" in {
    parseInternetDateTime("Thu, 06 Apr 2016 15:00:00 +0300") shouldBe Right(ZonedDateTime.parse("2016-04-06T12:00:00Z"))
  }

  it should "parse RFC 1123 with inset offset" in {
    parseInternetDateTime("Tue, 4 Feb 2009 15:50:00+0300") shouldBe Right(ZonedDateTime.parse("2009-02-04T12:50:00Z"))
  }

  it should "parse ISO-8601" in {
    parseInternetDateTime("2016-04-08T00:00:00-07:00") shouldBe Right(ZonedDateTime.parse("2016-04-08T07:00:00Z"))
  }
}

