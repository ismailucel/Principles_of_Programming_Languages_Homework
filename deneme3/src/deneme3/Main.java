/**
*
* @author İsmail Üçel -ismail.ucel@ogr.sakarya.edu.tr
* @since 05.04.2021 / 11.04.2021
* <p>
Kaynak kodların tamamını (okunacak dosyalar,arraylistler, pattern sınıfları, matcher sınıfları
metotlar ve yazdırma işlemleri) içinde bulunduran kısım
* </p>
*/




package deneme3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// okunulacak programın adresi
        String dosyaadi = "src/Program.cpp";  

       //bulunan sınıfları ekleyeceğimiz arraylist
        ArrayList<String> esleyenClass = new ArrayList<String>(); 		
       //bulunan yıkıcı metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyenDestructor = new ArrayList<String>();	
       //bulunan süper sınıfları ekleyeceğimiz arraylist
        ArrayList<String> esleyenSuper = new ArrayList<String>();
       //bulunan metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyenMetod = new ArrayList<String>();	
       //bulunan metotların parametrelerini ekleyeceğimiz arraylist
        ArrayList<String> esleyenMetodParametreleri = new ArrayList<String>();	
       //bulunan yapıcı metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyenConstructor = new ArrayList<String>();	
       //bulunan metotların sıfır parametreli olanlarını ekleyeceğimiz arraylist
        ArrayList<String> esleyenSıfırParametre = new ArrayList<String>();	
       //bulunan void metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyenvoid = new ArrayList<String>();	
       //bulunan operator<< metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyenoperator = new ArrayList<String>();
       //bulunan double, string, int, bool metotları ekleyeceğimiz arraylist
        ArrayList<String> esleyendt = new ArrayList<String>();					
  
        
        
      //satırları okurken kullanacağımız değişken
        String satirlar = null; 

       //sınıflar için regex
        Pattern yolClass = Pattern.compile("(?<=class)(.*)(?=\\{)");
       //yıkıcı metotlar için regex
        Pattern yolDestructor = Pattern.compile("(?<=~)(.*)(?=\\()");	
       //süper sınıflar için regex
        Pattern yolSuper = Pattern.compile("(?<=:\\spublic)(.*)(?=\\{)");
       //metotlar için regex
        Pattern yolMetod = Pattern.compile("(?<=(string)|(int)|(void)|(double)|(bool)|(ostream&)|(~)|\\})(.*)(?=\\()");
      //metot parametreleri için regex
        Pattern yolMetodParametre = Pattern.compile("(?<=\\()(?<!for\\()(string| |int|double|ostream|bool|K)(.*)(?=\\))");
      //yapıcı metotlar için regex
        Pattern yolConstructor = Pattern.compile("(?<!(new )|(->)|(.))(?!\\b(if|while|for|main|new|switch)\\b)\\b\\w+(?=\\s*\\()"); 
      //sıfır parametresi olan metotlar için regex
        Pattern yolSıfırParametre = Pattern.compile("(?<!main)\\(\\)(?!;|,|<|:)");  
      //dönüş tipi void olan metotlar için regex
        Pattern yolvoid = Pattern.compile("void"); 
      //dönüş tipi ostream olan metototlar için regex
        Pattern yoloperator = Pattern.compile("operator<<");	
      //dönüş tipi geri kalan metotlar için olan regex
        Pattern yoldt = Pattern.compile("(?<!\\(|,|:|:\\s|;\\s)(double|string|int|bool|float|long|short)"); 
     
        		

        try {
        	
        	//dosya okuma işlemleri
         
            FileReader dosyayioku = new FileReader(dosyaadi);
            

            BufferedReader okuyucu = new BufferedReader(dosyayioku);

           //metin sonuna gelene kadar satır satır dosya okuma
            while ((satirlar = okuyucu.readLine()) != null) {

              //sınıf için matcher class'ı
                Matcher esleClass = yolClass.matcher(satirlar);     
              //yıkıcı metot için matcher class'ı
                Matcher esleDestructor = yolDestructor.matcher(satirlar); 
              //süper sınıf için matcher class'ı
                Matcher esleSuper = yolSuper.matcher(satirlar);	
              //metot için matcher class'ı
                Matcher esleMetod = yolMetod.matcher(satirlar);	
              //metot parametreleri için matcher class'ı
                Matcher esleMetodParametre = yolMetodParametre.matcher(satirlar);
              //yapıcı metot için matcher class'ı
                Matcher esleConstructor = yolConstructor.matcher(satirlar);  
              //sıfır parametresi olan metotlar için matcher class'ı
                Matcher esleSıfırParametre = yolSıfırParametre.matcher(satirlar); 
              //dönüş tipi void olan metotlar için matcher class'ı
                Matcher eslevoid = yolvoid.matcher(satirlar);	
              //dönüş tipi ostream olan metotlar için matcher class'ı
                Matcher esleoperator = yoloperator.matcher(satirlar);	
              //dönüş tipi geri kalan tipler olan metotlar için matcher class'ı
                Matcher esledt = yoldt.matcher(satirlar);							 
                
              
                
                //matcher sınıfı ile metotları bulduğu sürece 
                
                while (esleMetod.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleMetod.group().replaceAll("[^A-Za-z0-9]+", ""); 
                    
                  //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa 
                    if (gecici.length() != 0) {
                    	
                    	  // ve eğer gecici daha önce eklenmediyse metodu esleyenMetod
                    	// arraylist'imize ekliyoruz ve yazdırma işlemlerini yapıyoruz
                        if (!esleyenMetod.contains(gecici)) {						   
                            esleyenMetod.add(gecici);
                            System.out.println("\t"+gecici);
                            System.out.println();
                            
                          
                        }
                        
                        
                    }
                    
                }
                
              //matcher sınıfı ile sınıfları bulduğu sürece
                
                while (esleClass.find()) {
                	
                	//sınıf etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleClass.group().replaceAll("[^A-Za-z0-9]+", "");
                    
                  //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa 
                    if (gecici.length() != 0) {									
                    	
                    	// ve eğer gecici daha önce eklenmediyse sınıfı esleyenClass
                    	// arraylist'imize ekliyoruz ve yazdırma işlemlerini yapıyoruz
                        if (!esleyenClass.contains(gecici)) {							
                            esleyenClass.add(gecici);									
                            System.out.println("Sınıf: " +gecici);
                            System.out.println();
                            
                    
                        }
                        
                        
                    }
                    
                                        
                } 
                            
                                        
                
              //matcher sınıfı ile void metotları bulduğu sürece
                while (eslevoid.find()) {						
                	
                	//void metotlar etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = eslevoid.group().replaceAll("[^A-Za-z0-9]+", "");		
                    																		
                    //void metodu esleyenvoid arraylist'imize ekliyoruz ve yazdırma işlemlerini yapıyoruz															
                       	esleyenvoid.add(gecici);										
                        	
                         System.out.println("\t\tDonus Turu:  "+gecici );
                            
                            
                        
                  } 
                
         
               
                
                    
              //matcher sınıfı ile kalan tiplerdeki metotları bulduğu sürece
                while (esledt.find()) {		
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esledt.group().replaceAll("[^A-Za-z0-9]+", "");			
                    
                 // metotları esleyendt arraylist'imize ekliyoruz ve 
                  //yazdırma işlemlerini yapıyoruz
                    
                       	esleyendt.add(gecici);										       
                       																		
                        	
                         System.out.println("\t\tDonus Turu:  "+gecici );
                            
                            
                        
                  } 
           
                
              //matcher sınıfı ile ostream tipindeki metotları bulduğu sürece
                while (esleoperator.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleoperator.group().replaceAll("[^A-Za-z0-9]+", "");
                    
                    // metotları esleyenoperator arraylist'imize ekliyoruz ve 
                    //yazdırma işlemlerini yapıyoruz
                        
                       	esleyenoperator.add(gecici);
                        	
                         System.out.println("\t\tDonus Turu: ostream&" );
                            
                            
                        
                  } 
                
                      
                //matcher sınıfı ile yapıcı metotları bulduğu sürece
                while (esleConstructor.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleConstructor.group().replaceAll("[^A-Za-z0-9]+", " ");
                    
                    //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa                     
                    if (gecici.length() != 0) {
                        if (true) {
                        	
                        	// yapıcı metotları esleyenConstructor arraylist'imize ekliyoruz ve 
                            //yazdırma işlemlerini yapıyoruz
                              
                        	esleyenConstructor.add(gecici);
                        	System.out.println("\t"+gecici);

                            System.out.println("\t\tDönüş Türü: Nesne Adresi");
                            
                            
                        }
                    }
                    
                    
                }
             
                          
              //matcher sınıfı ile yıkıcı metotları bulduğu sürece
                while (esleDestructor.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleDestructor.group().replaceAll("[^A-Za-z0-9]+", "");
                    
                    //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa  
                    if (gecici.length() != 0) {
                    	
                    	// yıkıcı metotları esleyenDestructor arraylist'imize ekliyoruz ve 
                        //yazdırma işlemlerini yapıyoruz
                        if (!esleyenDestructor.contains(gecici)) {
                            esleyenDestructor.add(gecici);
                            System.out.println("\t~" + gecici);
                           // System.out.println("\t\tParametre: 0");
                            System.out.println("\t\tDönüş Türü: Void");
                            System.out.println();
                            }
                    }
                }
                
                
                
               
                //matcher sınıfı ile süper sınıflar bulunduğu sürece
                while (esleSuper.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleSuper.group().replaceAll("[^A-Za-z0-9]+", "");
                    
                  //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa  
                    if (gecici.length() != 0) {
                    	
                    	// süper sınıfları esleyenSuper arraylist'imize ekliyoruz ve 
                        //atama işlemlerini yapıyoruz
                        if (!esleyenSuper.contains(gecici)) {
                        	
                            esleyenSuper.add(gecici);
                            
                        }
                    }
                }
              
                
             
              //matcher sınıfı ile sıfır parametreli metotlar bulunduğu sürece
                while (esleSıfırParametre.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleSıfırParametre.group().replaceAll("[^A-Za-z0-9]+", "");
                    
                 // sıfır parametreli metotları esleyenSıfırParametre arraylist'imize ekliyoruz ve 
                    //yazma işlemlerini yapıyoruz
                        	esleyenSıfırParametre.add(gecici);
                            System.out.println("\t\tParametre: 0 " );
                            System.out.println();
                            
                        
                    }
                
                
               

                //matcher sınıfı ile metotların parametreli bulunduğu sürece
                while (esleMetodParametre.find()) {
                	
                	//metot etrafındaki gereksiz kısımları atıp gecici adlı bir stringe atadık
                    String gecici = esleMetodParametre.group();
                    
                    //parametre sayısı için sayac
                    int sayac =0;
                   
                    //bunlar oldugunda sayac artar
                    
                    if(gecici.contains(","))sayac++;
                    if(gecici.contains("string")) sayac++;
                    if(gecici.contains("int")) sayac++;
                    if(gecici.contains("double")) sayac++;
                    if(gecici.contains("ostream")) sayac++;
                    if(gecici.contains("*")) sayac++;
                   
                  //eğer gecici'nin uzunluğu 0'dan büyükse yani gecici varsa 
                    if (gecici.length() != 0) {
                    	
                    	// metot parametrelerini esleyenMetodParametreleri arraylist'imize ekliyoruz ve 
                        //yazdırma işlemlerini yapıyoruz
                    	
                        if (!esleyenMetodParametreleri.contains(gecici)) {
                        	  
                        	esleyenMetodParametreleri.add(gecici);
                        	 System.out.print("\t\tParametre:" +sayac +"(" +gecici+")");

                        	System.out.println();
                        }
                        
                        
                    }
                    
                    
                }

               

               
                
                
            }

          //süper classları ve sayılarını yazdırıyoruz
                   
            for (int i = 0; i < esleyenSuper.size(); i++) {
                System.out.println("Super Class: "+ esleyenSuper.get(i));
            }
          
            
           
            
            //dosyayı kapatıyoruz
            okuyucu.close();

            //hata çıkışları için catch blokları
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadi.");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
