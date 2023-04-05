package org.learning.springlamiapizzeriacrud.controllers;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.models.Pizza;
import org.learning.springlamiapizzeriacrud.models.Sale;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.learning.springlamiapizzeriacrud.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Optional<Integer> id, Model model){
        Sale sale = new Sale();
        sale.setStartDate(LocalDate.now());
        sale.setExpireDate(LocalDate.now().plusMonths(1));
        if (id.isPresent()) {
            try {
                Pizza book = pizzaService.getById(id.get());
                sale.setPizza();
            } catch (PizzaNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        model.addAttribute("sale", sale);
        return "/sales/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute Sale formSale,
                           BindingResult bindingResult) {
        // Validazione
        if (bindingResult.hasErrors()) {
            return "/sales/create";
        }
        Sale createdSale = saleService.create(formSale);
        return "redirect:/pizzas/" + Integer.toString(createdSale.getPizza().getId());
    }
}

