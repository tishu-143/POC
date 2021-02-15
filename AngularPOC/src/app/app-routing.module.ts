import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { BlogComponent } from './blog/blog.component';
import { HOMEComponent } from './home/home.component';
import { MemoriesComponent } from './memories/memories.component';
import { TestComponent } from './test/test.component';

const routes: Routes = [
  {path: 'home', component: HOMEComponent},
  {path: '', component:HOMEComponent},
  {path:'blog', component:BlogComponent},
  {path:'memories', component: MemoriesComponent},
  {path: 'about', component:AboutUsComponent},
  {path:'test', component:TestComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
