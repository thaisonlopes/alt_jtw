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
package org.primefaces.apollo.view;

import org.primefaces.apollo.domain.Product;
import org.primefaces.apollo.service.ProductService;
import org.primefaces.model.DualListModel;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ListDemoView {

    private DualListModel<String> cities1;

    private List<String> cities2;

    private List<Product> products;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        List<String> citiesSource = new ArrayList<String>();
        List<String> citiesTarget = new ArrayList<String>();
        
        citiesSource.add("Turmalina");
        citiesSource.add("Capelinha");
        citiesSource.add("Veredinha");
        citiesSource.add("Belo Horizonte");
        citiesSource.add("Montes Claros");
        citiesSource.add("Minas Novas");
        citiesSource.add("Água Boa");
        
        cities1 = new DualListModel<String>(citiesSource, citiesTarget);

        cities2 = new ArrayList<String>();
        cities2.add("Turmalina");
        cities2.add("Capelinha");
        cities2.add("Veredinha");
        cities2.add("Belo Horizonte");
        cities2.add("Montes Claros");
        cities2.add("Minas Novas");
        cities2.add("Água Boa");

        this.products = this.service.getProducts();
    }

    public DualListModel<String> getCities1() {
        return cities1;
    }

    public List<String> getCities2() {
        return cities2;
    }

    public List<Product> getProducts() {
        return products;
    }
    
}
