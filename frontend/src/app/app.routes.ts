import { Routes } from '@angular/router';
import { Landing } from './Layout/landing/landing';
import { LoginComponent } from './shared/login/login';
import { YuvasriComponent } from './Components/yuvasri/yuvasri.component';
import { DarshiniComponent } from './Components/darshini/darshini.component';
import { GokulComponent } from './Components/gokul/gokul.component';
import { KeertheshaComponent } from './Components/keerthesha/keerthesha.component';

export const routes: Routes = [
  { path: '', component: Landing },
  { path: 'login/:id', component: LoginComponent },
  { path: 'yuvasri', component: YuvasriComponent }, 
  { path: 'darshini', component: DarshiniComponent }, 
  { path: 'gokul', component: GokulComponent }, 
  { path: 'keerthesha', component: KeertheshaComponent }, 
  { path: '', redirectTo: 'login/1', pathMatch: 'full' }
];