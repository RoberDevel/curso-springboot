import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductComponent } from './products/component/product.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ProductComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'hola mundo desde angular';
  enabled: boolean = false;

  courses: string[] = ['Angular', 'React', 'Vue'];

  setEnabled(): void {
    this.enabled = !this.enabled;
  }
}
