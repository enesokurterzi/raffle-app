package com.example.RaffleApp.services

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.configs.SSLPermission
import com.example.RaffleApp.models.Cekilis
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class WhoWonService(val context: Context, val previousFollowing: List<Cekilis>) {

    private fun getAndSetData(url: String, page: String) {

        try {
            val sslContext = SSLPermission().createTrustAllSSLContext()
            if (sslContext != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
            }

            val doc: Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
            val cekilisElements = doc.getElementsByClass("bg-transparent pb20").select(".col-sm-3.col-lg-3.item")
            for (element in cekilisElements) {

                val link = element.select("a").attr("abs:href")
                val imgUrl = element.getElementsByClass("img").select("img").attr("abs:src")
                val title = element.select("h4").text()
                val timeLeft = element.getElementsByClass("title d-flex").select("div.title > span.date:nth-child(1)").text()
                val giftValue = element.getElementsByClass("title d-flex").select("div.title > span.date:nth-child(2)").text()
                val price = element.getElementsByClass("title d-flex").select("div.title > span.date:nth-child(3)").text()


                val docDetail: Document = Jsoup.connect(link).timeout(15000).ignoreContentType(true).get()

                val detail = docDetail.getElementsByClass("secondGallery")
                        .select(".scrollbar-dynamic").get(0).text()
                val startingDate = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(1).text()
                val endDate = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(2).text()
                val drawDate = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(3).text()
                val listingDate = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(4).text()
                val minCost = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(5).text()
                val detailGiftValue = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(6).text()
                val giftCount = docDetail.getElementsByClass("info mainSocial")
                    .select(".kalanSure").get(7).text()

                val cekilis = Cekilis(
                    null, page, link, imgUrl, title, timeLeft, giftValue, price, false,
                    detail, startingDate, endDate, drawDate, listingDate, minCost, detailGiftValue, giftCount
                )

                for (item in previousFollowing) {
                    if (item.title == cekilis.title) {
                        cekilis.following = true
                    }
                }

                Handler(Looper.getMainLooper()).post {
                    val dbCekilis = DbController(context).db
                    dbCekilis.cekilisDao().insert(cekilis)
                    dbCekilis.close()
                }

            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun getAndSetCekilisler() {
        val url = "https://www.kimkazandi.com/cekilisler"
        val page = "Cekilisler"
        getAndSetData(url, page)
    }

    private fun getAndSetYeniBaslayanlar() {
        val url = "https://www.kimkazandi.com/cekilisler/yeni-baslayanlar"
        val page = "YeniBaslayanlar"
        getAndSetData(url, page)
    }

    private fun getAndSetBedavaKatilim() {
        val url = "https://www.kimkazandi.com/cekilisler/bedava-katilim"
        val page = "BedavaKatilim"
        getAndSetData(url, page)
    }

    private fun getAndSetArabaKazan() {
        val url = "https://www.kimkazandi.com/cekilisler/araba-kazan"
        val page = "ArabaKazan"
        getAndSetData(url, page)
    }

    private fun getAndSetTelefonTabletKazan() {
        val url = "https://www.kimkazandi.com/cekilisler/telefon-tablet-kazan"
        val page = "TelefonTabletKazan"
        getAndSetData(url, page)
    }

    private fun getAndSetTatilKazan() {
        val url = "https://www.kimkazandi.com/cekilisler/tatil-kazan"
        val page = "TatilKazan"
        getAndSetData(url, page)
    }

    fun getAndSetAll() {
        getAndSetCekilisler()
        getAndSetYeniBaslayanlar()
        getAndSetBedavaKatilim()
        getAndSetArabaKazan()
        getAndSetTelefonTabletKazan()
        getAndSetTatilKazan()
    }


}