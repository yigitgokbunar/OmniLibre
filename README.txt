ÖĞRENCİ BİLGİLERİ
-----------------
Ad Soyad: Yiğit Gökbunar
Öğrenci No: 24100001078
Ders: Nesneye Yönelimli Programlama
Konu: Kütüphane Yönetim Sistemi
İsim: OmniLibre

PROJE HAKKINDA
--------------
Bu proje Java Swing ve SQLite kullanılarak geliştirilmiştir. 
OOP prensipleri (Kalıtım, Kapsülleme, Soyutlama, Çok Biçimlilik) ve Interface yapısı kullanılmıştır.

KURULUM VE ÇALIŞTIRMA ADIMLARI
------------------------------
1. Projeyi Eclipse veya IntelliJ ortamına import ediniz.
2. Klasör içerisinde bulunan 'sqlite-jdbc-3.51.1.1.0.jar' dosyasını projenin "Build Path" (Libraries) kısmına ekleyiniz.
3. Veritabanı dosyası (kutuphane.db) proje klasörü içerisindedir, otomatik algılanacaktır.
4. 'AnaEkran.java' dosyasına sağ tıklayıp "Run" diyerek uygulamayı başlatabilirsiniz.

DOSYA İÇERİĞİ
-------------
- src/ : Kaynak kodlar (AnaEkran, Kitap, Materyal, IVeritabani)
- kutuphane.db : SQLite veritabanı dosyası (Örnek veriler içerir)
- Kurulum.sql : Veritabanı şema yapısını gösteren SQL dosyası
- sqlite-jdbc.jar : Gerekli veritabanı sürücüsü

Not: Veritabanı bağlantısı için JDBC kullanılmıştır.