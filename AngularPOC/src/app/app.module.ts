import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HOMEComponent } from './home/home.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BlogComponent } from './blog/blog.component';
import { MemoriesComponent } from './memories/memories.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { TestComponent } from './test/test.component';

@NgModule({
  declarations: [
    AppComponent,
    HOMEComponent,
    TopBarComponent,
    BlogComponent,
    MemoriesComponent,
    AboutUsComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
