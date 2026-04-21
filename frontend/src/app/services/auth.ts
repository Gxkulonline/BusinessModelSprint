import { Injectable } from '@angular/core';

@Injectable({
 providedIn:'root'
})
export class AuthService {

 users:any = {

   yuvasri:{
     username:'yuvasri',
     password:'1234'
   },

   gokul:{
     username:'gokul',
     password:'1234'
   },

   darshini:{
     username:'darshini',
     password:'1234'
   },

   keerthesha:{
     username:'keerthesha',
     password:'1234'
   }

 };

 login(member:string,user:string,pass:string){

   const data = this.users[member];

   if(data.username === user && data.password === pass){
      localStorage.setItem('login','true');
      return true;
   }

   return false;
 }

 isLoggedIn(){
   return localStorage.getItem('login') === 'true';
 }

 logout(){
   localStorage.removeItem('login');
 }

}