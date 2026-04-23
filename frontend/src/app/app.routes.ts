import { Routes } from '@angular/router';
import { Landing } from './Layout/landing/landing';
import { LoginComponent } from './shared/login/login';
import { YuvasriComponent } from './Components/yuvasri/yuvasri.component';
import { DarshiniComponent } from './Components/darshini/darshini.component';
import { GokulComponent } from './Components/gokul/gokul.component';
import { KeertheshaComponent } from './Components/keerthesha/keerthesha.component';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: '', component: Landing },
  { path: 'login/:id', component: LoginComponent },
  { path: 'yuvasri', component: YuvasriComponent, canActivate: [authGuard] }, 
  { path: 'darshini', component: DarshiniComponent, canActivate: [authGuard] }, 
  { path: 'gokul', component: GokulComponent, canActivate: [authGuard] }, 
  { path: 'keerthesha', component: KeertheshaComponent, canActivate: [authGuard] }, 
  { path: '', redirectTo: 'login/1', pathMatch: 'full' }
];