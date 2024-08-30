# Sosyal Medya Platformu

Bu proje, Java dilinde çeşitli veri yapıları ve interfaceleri kullanarak basit bir Sosyal Medya Platformu geliştirmeyi amaçlamaktadır. Bu proje ile kullanıcı yönetimi, gönderi yönetimi, takip sistemi ve favori yönetimi gibi temel sosyal medya işlevselliklerini simüle edebilirsiniz.

## Proje Özeti

Bu sosyal medya platformu, kullanıcıların birbirlerini takip edebildiği, gönderiler oluşturabildiği, gönderilere yorum yapabildiği ve beğendikleri gönderileri favorilere ekleyebildiği bir simülasyon sunar. Her kullanıcı, takip ettiği kişilerin gönderilerini bir "akış" (feed) üzerinde görebilir.

## Teknik Gereksinimler

- **Java 8+**: Projeyi derleyip çalıştırmak için Java 8 veya daha üstü bir sürüm gereklidir.
- **Kullanılan Veri Yapıları**:
  - `ArrayList`
  - `LinkedList`
  - `HashSet`
  - `TreeSet`
  - `HashMap`
  - `LinkedHashMap`
- **Iterator ve Iterable Kullanımı**: Koleksiyonlar üzerinde dolaşmak için `Iterator` arayüzü kullanılmıştır.

## Kullanım Talimatları

### Projeyi Çalıştırma

1. **Java Kurulumu**: Bilgisayarınızda Java kurulu değilse, [Java'nın resmi web sitesinden](https://www.java.com/) Java'yı indirip yükleyin.
   
2. **Proje Dosyalarını İndirme**: Proje dosyalarını bilgisayarınıza indirin ve uygun bir klasöre çıkartın.

3. **Kodu Derleme**:
   - Komut satırını açın.
   - Projenin bulunduğu dizine gidin.
   - Aşağıdaki komutu çalıştırarak kodu derleyin:
     ```bash
     javac socialMediaPlatform.java
     ```

4. **Kodu Çalıştırma**:
   - Derleme başarılı olduktan sonra aşağıdaki komutu çalıştırarak uygulamayı başlatın:
     ```bash
     java socialMediaPlatform
     ```

### Özellikler

- **Kullanıcı Yönetimi**:
  - Yeni kullanıcılar oluşturulabilir.
  - Kullanıcılar birbirlerini takip edebilir.

- **Gönderi Yönetimi**:
  - Kullanıcılar gönderi oluşturabilir.
  - Kullanıcılar diğer kullanıcıların gönderilerine yorum yapabilir.

- **Takip Sistemi**:
  - Kullanıcılar, takip ettikleri kişilerin gönderilerini akışlarında (feed) görebilirler.

- **Favoriler**:
  - Kullanıcılar beğendikleri gönderileri favorilere ekleyebilir.
  - Favori gönderiler listelenebilir.

### Kod Yapısı

- **User**: Kullanıcı sınıfı, her bir kullanıcının gönderilerini, takip ettiklerini ve favorilerini yönetir.
- **Post**: Her bir gönderiyi temsil eder ve yorumları yönetir.
- **Comment**: Her bir yorumu temsil eder.

