package com.arkeobla.service;

import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
public class AiService {

    public String getAnswer(String question) {
        String q = question.toLowerCase(Locale.forLanguageTag("tr"));

        // Genişletilmiş Bilgi Bankası (Demo)
        if (q.contains("arkeoloji")) {
            return "Arkeoloji, insanlık tarihini maddi kalıntılar üzerinden inceleyen bilim dalıdır. Kazılarla geçmişi aydınlatır.";
        } else if (q.contains("göbeklitepe") || q.contains("gobeklitepe")) {
            return "Göbeklitepe, Şanlıurfa'da bulunan ve dünyanın bilinen en eski tapınak merkezi kabul edilen yapıdır (M.Ö. 9600). Tarihin sıfır noktası olarak anılır.";
        } else if (q.contains("piramit") || q.contains("mısır")) {
            return "Mısır Piramitleri, firavunların mezarı olarak inşa edilmiştir. En büyüğü Keops Piramidi'dir ve Dünyanın Yedi Harikası'ndan biridir.";
        } else if (q.contains("roma")) {
            return "Roma İmparatorluğu, Akdeniz havzasına hükmeden antik çağın en büyük medeniyetlerinden biridir.";
        } else if (q.contains("hitit") || q.contains("hattuşa")) {
            return "Hititler, Anadolu'nun ilk büyük imparatorluğudur. Başkentleri Çorum'daki Hattuşa'dır.";
        } else if (q.contains("tarih")) {
            return "Tarih, geçmişteki olayları neden-sonuç ilişkisi içinde inceleyen bilimdir.";
        } else if (q.contains("merhaba") || q.contains("selam")) {
            return "Merhaba! Ben ArkeoBla Asistanı. Tarih, arkeoloji ve sanat hakkında her şeyi sorabilirsin.";
        } else if (q.contains("kimsin")) {
            return "Ben Google teknolojilerinden ilham alan ArkeoBla yapay zeka asistanıyım.";
        } else {
            // Cevap bulunamazsa Google'a yönlendir
            return "Bu konuda henüz detaylı bilgim yok. Ama senin için Google'da arayabilirim: " +
                   "<a href='https://www.google.com/search?q=" + question.replace(" ", "+") + "' target='_blank' style='color:blue; text-decoration:underline;'>'" + question + "' için Google'da Ara</a>";
        }
    }
}
