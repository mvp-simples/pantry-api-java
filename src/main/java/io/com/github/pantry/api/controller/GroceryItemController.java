package io.com.github.pantry.api.controller;

import io.com.github.pantry.api.dto.StatsResponse;
import io.com.github.pantry.api.model.GroceryItem;
import io.com.github.pantry.api.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users/{id}")
@CrossOrigin
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemService service;

    @GetMapping("/items")
    public List<GroceryItem> getItems(@PathVariable Long id,
                                      @RequestParam(defaultValue = "0") int skip,
                                      @RequestParam(defaultValue = "20") int take) {
        return service.getItemsByUser(id, skip, take);
    }

    @GetMapping("/stats")
    public StatsResponse getStats(@PathVariable Long id) {
        return service.getStatsByUser(id);
    }

    @PostMapping("/upload")
    public String uploadPdf(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Arquivo vazio";
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            // Aqui você pode tratar o texto como necessário
            return pdfStripper.getText(document);
        } catch (IOException e) {
            return "Erro ao processar o PDF: " + e.getMessage();
        }
    }
}
