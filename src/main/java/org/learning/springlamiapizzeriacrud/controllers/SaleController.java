package org.learning.springlamiapizzeriacrud.controllers;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.models.AlertMessage;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@RequestParam(name = "pizzaId")Optional<Integer> id, Model model){

        Sale sale = new Sale();
        sale.setStartDate(LocalDate.now());
        sale.setExpireDate(LocalDate.now().plusMonths(1));

        if(id.isPresent()) {
            try {
                Pizza pizza = pizzaService.getById(id.get());
                sale.setPizza(pizza);
            } catch (PizzaNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        model.addAttribute("sale", sale);
        return "/sales/form";

    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute Sale formSale, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "/sales/form";
        }

        Sale createdSale = saleService.create(formSale);
        redirectAttributes.addFlashAttribute("message",
                new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "Offerta creata con successo"));
        return "redirect:/pizzas/" + Integer.toString(createdSale.getPizza().getId());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Sale sale = saleService.getById(id);
            model.addAttribute("sale", sale);
            return "/sales/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}

