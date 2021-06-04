
#include <Servo.h>

Servo myservo;  

void setup() {
  myservo.attach(D5); 
  Serial.begin(9600);
}


void loop() {  
 
}






void car(boolean isCar){
  isCar?myservo.write(130):myservo.write(50);
}
