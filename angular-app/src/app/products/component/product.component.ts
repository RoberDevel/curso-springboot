import { Component, OnInit } from '@angular/core';
import { ProductService } from '../service/product.service';
import { Product } from '../model/product';
import { FormComponent } from "./form.component";

@Component({
  selector: 'app-product',
  standalone: true,
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
  imports: [FormComponent]
})
export class ProductComponent implements OnInit {

  products: Product[] = [];
  productSelected: Product = new Product();

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(products => this.products = products);
  }

  addProduct(product: Product): void {

    if (product.id > 0) {

      this.service.update(product).subscribe(updatedProduct => {
        this.products = this.products.map(p => {
          if (p.id == product.id) {
            return { ...updatedProduct };
          }
          return p;
        });
      });

      /* this.products = this.products.map(p => {
         if (p.id == product.id) {
           return { ...product };
         }
         return p;
       });*/

    } else {
      //product.id = this.products.length + 1;
      // this.products.push(product);
      this.service.create(product).subscribe(newProduct => {
        // this.products.push({ ...newProduct });
        this.products = [...this.products, { ...newProduct }];

      });
      //this.products = [...this.products, { ...product, id: this.products.length + 1 }];
    }
    this.productSelected = new Product();
  }

  onRemoveProduct(id: number): void {
    this.service.remove(id).subscribe(() => {
      this.products = this.products.filter(product => product.id != id);
    });

    // this.products = this.products.filter(product => product.id != id);
  }

  onUpdateProduct(productRow: Product): void {
    this.productSelected = { ...productRow };
  }



}
