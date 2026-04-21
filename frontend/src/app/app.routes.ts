import { Routes } from '@angular/router';

import { Landing } from './features/landing/landing';
import { LoginComponent } from './features/login/login';

import { Yuvasri } from './shared/yuvasri/yuvasri';
import { Gokul } from './shared/gokul/gokul';
import { Darshini } from './shared/darshini/darshini';
import { Keerthesha } from './shared/keerthesha/keerthesha';

import { authGuard } from './guards/auth-guard';

export const routes: Routes = [

 { path: '', component: Landing },

 { path: 'login/:member', component: LoginComponent},

 { path: 'shared/yuvasri', component: Yuvasri, canActivate:[authGuard] },

 { path: 'shared/gokul', component: Gokul, canActivate:[authGuard] },

 { path: 'shared/darshini', component: Darshini, canActivate:[authGuard] },

 { path: 'shared/keerthesha', component: Keerthesha, canActivate:[authGuard] }

];