package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.exceptions.SaleNotFoundException;
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

    public Sale getById(Integer id) throws SaleNotFoundException {
        return saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException(Integer.toString(id)));
    }

    public Sale update(Integer id, Sale formSale) throws SaleNotFoundException {
        Sale saleToUpdate = getById(id);
        saleToUpdate.setStartDate(formSale.getStartDate());
        saleToUpdate.setExpireDate(formSale.getExpireDate());
        saleToUpdate.setTitle(formSale.getTitle());
        return saleRepository.save(saleToUpdate);
    }

    public void delete(Integer saleId) throws SaleNotFoundException {
        Sale saleToDelete = getById(saleId);
        saleRepository.delete(saleToDelete);
    }
}
