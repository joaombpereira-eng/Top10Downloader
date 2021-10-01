package com.coding.top10downloader

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

private const val TAG = "ParseApplications"

class ParseApplications {
    val applications = ArrayList<FeedEntry>()

    fun parse(xmlData: String): Boolean {
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) { //Verify if it's the end of the document
                val tagName = xpp.name?.toLowerCase()
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (tagName == "entry") {
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        if (inEntry) {
                            when (tagName) {
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry() //Create new object
                                }

                                "name" -> currentRecord.name = textValue
                                "artist" -> currentRecord.artist = textValue
                                "releasedate" -> currentRecord.releaseDate = textValue
                                "summary" -> currentRecord.summary = textValue
                                "image" -> currentRecord.imageURL = textValue
                            }
                        }
                    }
                }

                //Nothing else to do.
                eventType = xpp.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            status = false //If has an error
        }

        return status
    }
}