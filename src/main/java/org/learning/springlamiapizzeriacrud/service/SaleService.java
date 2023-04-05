package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.models.Sale;
import org.learning.springlamiapizzeriacrud.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Sale create(Sale formSale){
        return saleRepository.save(formSale);
    }
}
