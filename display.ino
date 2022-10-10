#include <LiquidCrystal_I2C.h>
#include <SPI.h>
#include <MFRC522.h>
#include <Servo.h>

#define RST_PIN         9 
#define SS_PIN          10  

LiquidCrystal_I2C lcd(0x27,16,2); 
MFRC522 mfrc522(SS_PIN, RST_PIN);
Servo myservo;

float _balance[2] = {25000, 35000};
int tollprice = 300;
String _name[2] = {"Mr. X", "Mr. Y"};
int ID;


void setup() {

  pinMode(6,INPUT);
  
  lcd.init();
  lcd.clear();         
  lcd.backlight(); 


  myservo.attach(7);
  Serial.begin(9600);
  SPI.begin();
  mfrc522.PCD_Init(); 

  dis("Welcome to","Our Project");
  delay(3000);
  dis("   We are","Group no: 18");
  delay(3000); 
  
}


void loop() {
  dis("Toll Management","    System");
  delay(200);


  while(!digitalRead(6)) {
    dis("Please","Punch your card");
    delay(200);
    myservo.write(90);
    ID = getCardID();
    if (ID!=-1) {
      credit(ID);
      myservo.write(0);
      while (!digitalRead(6)) {
        delay(200);
      }
      delay(2000);
    }
  }

  myservo.write(0);

  delay(300);
}



void dis(String a, String b){

  lcd.setCursor(0,0);  
  lcd.print(a+"               ");
  
  lcd.setCursor(0,1);  
  lcd.print(b+"               ");
  Serial.println(a+" "+b);
}



int getCardID() {
  
  if ( ! mfrc522.PICC_IsNewCardPresent() || ! mfrc522.PICC_ReadCardSerial())
  {
    return -1;
  }
  
  String content = "";
  for (byte i = 0; i < mfrc522.uid.size; i++)
  {
    content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
    content.concat(String(mfrc522.uid.uidByte[i], HEX));
  }

  delay(100);
  content.toUpperCase();

  Serial.println(content.substring(1));

  dis("Card ID",content.substring(1));
  
  if (content.substring(1) == "63 4B F7 1A")  return 0;
  else if (content.substring(1) == "12 90 FE 34") return 1;
  
  

  return -1;
}



boolean credit(int person) {
  if (_balance[person] < tollprice) {
    dis("Insufficient balance","Please recharge");
  } else {
    dis("Credited "+ String(tollprice) + "TK"," from " + _name[person]);
    delay(4000);
    _balance[person] -= tollprice;
    dis("Current balance", String(_balance[person])+" TK");
    delay(4000);
  }

}
