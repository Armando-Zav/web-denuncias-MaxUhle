package com.indecopi.denuncias_web.controller;

import com.indecopi.denuncias_web.model.Denuncia;
import com.indecopi.denuncias_web.service.DenunciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "*")
public class DenunciaController {

    private final DenunciaService denunciaService;

    DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping
    public ResponseEntity<List<Denuncia>> listarDenuncias() {
        return ResponseEntity.ok(denunciaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerDenuncia(@PathVariable Long id) {
        return denunciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Denuncia> crearDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia nuevaDenuncia = denunciaService.guardar(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDenuncia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia denuncia) {
        try {
            Denuncia actualizada = denunciaService.actualizar(id, denuncia);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDenuncia(@PathVariable Long id) {
        if (denunciaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}