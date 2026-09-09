package com.indecopi.denuncias_web.service;

import com.indecopi.denuncias_web.model.Denuncia;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DenunciaService {

    private final List<Denuncia> listaDenuncias = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

        public DenunciaService() {
        guardar(new Denuncia(null, "ACOSO_ESCOLAR", "Acoso reiterado durante el horario escolar.",
            "María Torres", "Carlos Ramírez", null, null, null));
        guardar(new Denuncia(null, "MALTRATO_PSICOLOGICO", "Insultos y amenazas constantes.",
            "Pedro Flores", "Lucía Mendoza", null, null, null));
        guardar(new Denuncia(null, "RUIDOS_MOLESTOS", "Ruidos excesivos durante la noche.",
            "Andrea Castro", "Vecinos del edificio", null, null, null));
        }

    public List<Denuncia> listarTodas() {
        return listaDenuncias;
    }

    public Optional<Denuncia> obtenerPorId(Long id) {
        return listaDenuncias.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public Denuncia guardar(Denuncia denuncia) {
        denuncia.setId(contadorId.getAndIncrement());
        if (denuncia.getFechaRegistro() == null) {
            denuncia.setFechaRegistro(LocalDateTime.now());
        }
        evaluarProtocolo(denuncia);
        listaDenuncias.add(denuncia);
        return denuncia;
    }

    public Denuncia actualizar(Long id, Denuncia datos) {
        Optional<Denuncia> opcional = obtenerPorId(id);
        if (opcional.isPresent()) {
            Denuncia denuncia = opcional.get();
            denuncia.setTipoDenuncia(datos.getTipoDenuncia());
            denuncia.setDescripcion(datos.getDescripcion());
            denuncia.setNombreDenunciante(datos.getNombreDenunciante());
            denuncia.setNombreAfectado(datos.getNombreAfectado());
            evaluarProtocolo(denuncia);
            return denuncia;
        }
        throw new RuntimeException("Denuncia no encontrada con ID: " + id);
    }

    public boolean eliminar(Long id) {
        return listaDenuncias.removeIf(d -> d.getId().equals(id));
    }

    private void evaluarProtocolo(Denuncia denuncia) {
        if (denuncia.getTipoDenuncia() != null) {
            String tipo = denuncia.getTipoDenuncia().trim().toUpperCase();
            if (tipo.equals("MALTRATO_PSICOLOGICO") || 
                tipo.equals("MALTRATO_FISICO") || 
                tipo.equals("ACOSO_ESCOLAR")) {
                denuncia.setProtocoloActivado(true);
                denuncia.setEstado("PROTOCOLO_ACTIVADO");
            } else {
                denuncia.setProtocoloActivado(false);
                denuncia.setEstado("RECIBIDA");
            }
        } else {
            denuncia.setProtocoloActivado(false);
            denuncia.setEstado("RECIBIDA");
        }
    }
}