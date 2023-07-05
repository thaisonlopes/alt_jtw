/*
   Copyright 2009-2022 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.primefaces.apollo.service;

import org.primefaces.apollo.domain.InventoryStatus;
import org.primefaces.apollo.domain.Product;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Named
@ApplicationScoped
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1000, "f230fh0g3", "Moloteom", "Descrição De Produto", "bamboo-watch.jpg", 65, "Moletons", 24, InventoryStatus.emEstoque, 5));
        products.add(new Product(1001, "nvklal433", "Blusa Branca", "Descrição De Produto", "black-watch.jpg", 72, "Camisas", 61, InventoryStatus.foraDeEstoque, 4));
        products.add(new Product(1002, "zz21cz3c1", "Blusa Azul", "Descrição De Produto", "blue-band.jpg", 79, "Camisas", 2, InventoryStatus.baixoEstoque, 3));
        products.add(new Product(1003, "244wgerg2", "Blusa Preta", "Descrição De Produto", "blue-t-shirt.jpg", 29, "Camisas", 25, InventoryStatus.emEstoque, 5));
        products.add(new Product(1004, "h456wer53", "Bermuda Jeans", "Descrição De Produto", "bracelet.jpg", 15, "Bermudas", 73, InventoryStatus.emEstoque, 4));
        products.add(new Product(1005, "av2231fwg", "Bermuda Branca", "Descrição De Produto", "brown-purse.jpg", 120, "Bermudas", 0, InventoryStatus.foraDeEstoque, 4));
        products.add(new Product(1006, "bib36pfvm", "Bermuda Preta", "Descrição De Produto", "chakra-bracelet.jpg", 32, "Bermudas", 5, InventoryStatus.baixoEstoque, 3));
        products.add(new Product(1007, "mbvjkgip5", "Cinto", "Descrição De Produto", "galaxy-earrings.jpg", 34, "Acessórios", 23, InventoryStatus.emEstoque, 5));
        products.add(new Product(1008, "vbb124btr", "Meia", "Descrição De Produto", "game-controller.jpg", 99, "Vestuário", 2, InventoryStatus.baixoEstoque, 4));
        products.add(new Product(1009, "cm230f032", "Cueca", "Descrição De Produto", "gaming-set.jpg", 299, "Vestuário", 63, InventoryStatus.emEstoque, 3));
        products.add(new Product(1010, "plb34234v", "Macacão", "Descrição De Produto", "gold-phone-case.jpg", 24, "Vestuário", 0, InventoryStatus.foraDeEstoque, 4));
        products.add(new Product(1011, "4920nnc2d", "Bota Masculina", "Descrição De Produto", "green-earbuds.jpg", 89, "Calçados", 23, InventoryStatus.emEstoque, 4));
        products.add(new Product(1012, "250vm23cc", "Bota Feminina", "Descrição De Produto", "green-t-shirt.jpg", 49, "Calçados", 74, InventoryStatus.emEstoque, 5));
        products.add(new Product(1013, "fldsmn31b", "Tênis Nike", "Descrição De Produto", "grey-t-shirt.jpg", 48, "Calçados", 0, InventoryStatus.foraDeEstoque, 3));
        products.add(new Product(1014, "waas1x2as", "Tênis Adidas", "Descrição De Produto", "headphones.jpg", 175, "Calçados", 8, InventoryStatus.baixoEstoque, 5));
        products.add(new Product(1015, "vb34btbg5", "Chuteira Nike", "Descrição De Produto", "light-green-t-shirt.jpg", 49, "Calçados", 34, InventoryStatus.emEstoque, 4));
        products.add(new Product(1016, "k8l6j58jl", "Chuteira Adidas", "Descrição De Produto", "lime-band.jpg", 79, "Calçados", 12, InventoryStatus.emEstoque, 3));
        products.add(new Product(1017, "v435nn85n", "Chinelo Havaianas", "Descrição De Produto", "mini-speakers.jpg", 85, "Calçados", 42, InventoryStatus.emEstoque, 4));
        products.add(new Product(1018, "09zx9c0zc", "Boné Sol A Sol", "Descrição De Produto", "painted-phone-case.jpg", 56, "Acessórios", 41, InventoryStatus.emEstoque, 5));
        products.add(new Product(1019, "mnb5mb2m5", "Boné Vida No Campo", "Descrição De Produto", "pink-band.jpg", 79, "Acessórios", 63, InventoryStatus.emEstoque, 4));
        products.add(new Product(1020, "r23fwf2w3", "Creme Avon", "Descrição De Produto", "pink-purse.jpg", 110, "Cosméticos", 0, InventoryStatus.foraDeEstoque, 4));
        products.add(new Product(1021, "pxpzczo23", "Creme Avulso", "Descrição De Produto", "purple-band.jpg", 79, "Cosméticos", 6, InventoryStatus.baixoEstoque, 3));
        products.add(new Product(1022, "2c42cb5cb", "Protetor Solar", "Descrição De Produto", "purple-gemstone-necklace.jpg", 45, "Cosméticos", 62, InventoryStatus.emEstoque, 4));
        products.add(new Product(1023, "5k43kkk23", "Perfume One Million", "Descrição De Produto", "purple-t-shirt.jpg", 49, "Cosméticos", 2, InventoryStatus.baixoEstoque, 5));
        products.add(new Product(1024, "lm2tny2k4", "Perfume Invictus", "Descrição De Produto", "shoes.jpg", 64, "Cosméticos", 0, InventoryStatus.emEstoque, 4));
        products.add(new Product(1025, "nbm5mv45n", "Perfume Kaiak", "Descrição De Produto", "sneakers.jpg", 78, "Cosméticos", 52, InventoryStatus.emEstoque, 4));
        
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProducts(int size) {

        if (size > products.size()) {
            Random rand = new Random();

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(products.subList(0, size));
        }

    }

    public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(original.clone());
        }
        return results;
    }
}
