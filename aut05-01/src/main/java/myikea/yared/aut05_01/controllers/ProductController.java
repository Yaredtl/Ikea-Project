package myikea.yared.aut05_01.controllers;

import jakarta.validation.Valid;
import myikea.yared.aut05_01.models.Municipio;
import myikea.yared.aut05_01.models.Product;
import myikea.yared.aut05_01.models.Provincia;
import myikea.yared.aut05_01.services.MunicipioServices;
import myikea.yared.aut05_01.services.ProductServices;
import myikea.yared.aut05_01.services.ProvinciaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.color.ProfileDataException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProvinciaServices provinciaServices;

    @Autowired
    private MunicipioServices municipioServices;

    private String uploadDir  = "src/main/resources/static/Imagenes/";
    //INDEX

    @GetMapping("/Products")
    public String listProducts(Model model){
        model.addAttribute("Productos", productServices.listProduct());
        return "Products/list";
    }

    //CREATE
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/Products/create")
    public String createProduct(Model model)
    {
        model.addAttribute("Producto", new Product());
        return "Products/create";
    }

    @PostMapping("/Products/create")
    public String createProduct(@Valid @ModelAttribute("Producto") Product product, BindingResult bindingResult,@RequestParam("imageFile")MultipartFile imageFile, Model model) {
        if (bindingResult.hasErrors()) {
            return "Product/create";
        } else {
            if (!imageFile.isEmpty()) {
                try {
                    byte[] bytes = imageFile.getBytes();
                    Path path = Paths.get(uploadDir + imageFile.getOriginalFilename());
                    Files.write(path, bytes);
                    product.setImage(imageFile.getOriginalFilename());
                } catch (Exception e) {
                    bindingResult.rejectValue("image", "error.product", "Error al subir la imagen");
                    return "Product/create";
                }
            }
        }
        productServices.createProduct(product);
        return "redirect:/Products";
    }

    //DETAILS

    @GetMapping("/Products/details/{id}")
    public String detailsProduct(@PathVariable int id, Model model){
        Product product = productServices.getProduct(id).get();
        model.addAttribute("Producto", product);
        return "Products/details";
    }

    //DELETE

    @PostMapping("/Products/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productServices.deleteProduct(id);
        return "redirect:/Products";
    }
}
