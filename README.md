# BKM EXPRESS FLOW ANDROID SDK

## NE İŞE YARAR?

> Hizmetinize sunulan BKM Express Flow Android SDK paketi ile son kullanıcının Android cihazında BKMExpress uygulaması kurulu olmasa dahi, "Ödeme Yapma" özelliğini, uygulamanızdan çıkış yapma gereksinimi olmadan halletmenize olanak sunar.

## SİSTEM GEREKSİNİMLERİ NELERDİR?

 *  BKM Express Flow Android SDK paketi, Android Studio ile geliştirilen uygulamalar baz alınarak tasarlanmıştır.
 *  Min SDK Version 15 desteklenmektedir.

## NASIL ÇALIŞIR?

Işyerleri BKM Express entegrasyonlarını tamamlayarak gerekli **kullanıcı adı** ve **şifrelerini** almalıdırlar. Bu kullanıcı adı ve şifre 
BKM Express Android SDK paketinin entegre edeceğiniz uygulamaya görünür olması için gerekmektedir. 
İşyeri servis uygulamaları BKM sunucularında oturum açtıktan sonra, yarattıkları ödeme işlerinden kendilerine iletilen **token** ve **path** parametreleri ile çalışmak istedikleri ortamı seçerek BKMExpress Flow SDK akışı başlatabilirler. 
BKM sunucularında oturum açma ile detayli bilgiye [buradan](https://test-api.bkmexpress.com.tr/docs) ulaşabilirsiniz.

## GRADLE ENTEGRASYONU

* Entegrasyona başlarken lütfen size sunduğumuz kullanıcı adı ve şifreyi, local.properties dosyasına aşağıdaki gibi ekleyiniz. 

                bkm_username=<<YOUR_USERNAME>>
                bkm_password=<<YOUR_PASSWORD>>
                bkm_maven_url = http://54.174.1.67/artifactory/bexflowsdk-android-release

* SDK paketini gradle dependency ile eklemek için, <u>projenizin</u> build.gradle dosyasındaki repositories kısmına aşağıdaki kod bloğunu ekleyiniz.

                Properties properties = new Properties()
                properties.load(project.rootProject.file('local.properties').newDataInputStream())
                
                
                allprojects {
                    repositories {
                        jcenter()
                        maven {
                            url = properties.getProperty("bkm_maven_url")
                            credentials {
                                username = properties.getProperty("bkm_username")
                                password = properties.getProperty("bkm_password")
                            }
                        }
                    }
                }
                
* Daha sonra yine <u>uygulamanızın</u> build.gradle dosyasındaki dependencies kısmına aşağıdaki gradle bağlantılarını ekleyiniz.
        
        compile 'com.android.support:appcompat-v7:26.0.1'
      
                
* Test, Preprod veya Prod ortamda çalışacak paket için
                 
        compile 'com.bkm:bexflowsdk:1.0.1'


* Yukarıdaki eklemeleri yapıp, projenizi gradle ile sync ettikten sonra BEX SDK nın,  BEXStarter sınıfına erişebilirsiniz. **BEXStarter** sınıfı, sunulan servis paketlerinin çalışmalarını sağlamakta, ve parametrik olarak verilen **BEXSubmitConsumerListener** && **BEXPaymentListener** interfaceleri ile de asynchrone olarak sonucu işyerine iletmektedir. (Ayrıntılı bilgi için lütfen Örnek Projeye Bakınız!)


### BEXStarter

                public static void startBexFlow(Context context, Environment environment,String paymentIssueId, String paymentIssuePath, String paymentIssueToken, BEXPaymentListener listener);
               

### BEXPaymentListener

                 public void onSuccess(); //BAŞARILI ÖDEME İŞLEMİ 
                 public void onCancelled(); //KULLANICI ÖDEME İŞLEMİNİ İPTAL ETTİ veya İŞYERİ ÖDEMEYİ REDDETTİ
                 public void onFailure(int errorId,String errorMsg); //ÖDEME İŞLEMİ VERİLEN HATA YÜZÜNDEN İPTAL EDİLDİ


### ÖRNEK KULLANIM - PAYMENT (ÖDEME)


    RestManagerApp.getInstance().requestCreatePaymentIssue("merchant_token", "merchant_path", new CreateIssueReq("amount", "installment_url", "nonce_url")).enqueue(new RetrofitCallback<MerchantCreateIssueResponse>(getActivity()) {
    
        @Override
        public void onSuccess(MerchantCreateIssueResponse successResponse) {
            BEXStarter.startBexFlow(getActivity(),Environment.TEST, successResponse.getCreateIssueObject().getId(), successResponse.getCreateIssueObject().getPath(), successResponse.getCreateIssueObject().getToken(), new BEXOTPlistener() {
                
                @Override
                public void onSuccess(){
                    Toast.makeText(getActivity(),"Ödeme Başarılı!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCanceled() {
                    Toast.makeText(getActivity(),"Ödeme İptal edildi!",Toast.LENGTH_SHORT).show();
                }
                
                @Override
                public void onFailure(String errorId, String error) {
                    Toast.makeText(getActivity(),"Ödeme Hatası :: "+error,Toast.LENGTH_SHORT).show();
                }       
            });
        }
        @Override
        public void onFailure(String errorCode, String error) {
            Toast.makeText(getActivity(), "Payment Ticket Yaratma Problemi :: errMsg -> " + error, Toast.LENGTH_LONG).show();
        }
    });

                 
## ORTAMLAR

BKM Express Android SDK paketi iki farklı ortamda çalışmaktadır. (Ortam değişikliği => Environment parametresi ile gerçekleşmektedir. Lütfen örnek koda bakınız.)

* TEST
* PROD

**Her ortam için kullanıcı adı ve şifre aynıdır.**

**NOT:** Entegrasyon sırasında işyerlerine verilen kullanıcı adı ve şifrenin sorumluluğu, **işyerine** aittir.




