package com.umg.proyecto.util;

import jakarta.servlet.http.Part;
import java.util.Set;

public class ImageValidator {
    private static final Set<String> ALLOWED = Set.of("image/jpeg","image/png","image/webp");
    private static final long MAX_BYTES = 2L * 1024 * 1024; // 2 MB

    public static String validate(Part part){
        if(part==null || part.getSize()==0) return null; // opcional
        if(part.getSize()>MAX_BYTES) return "La imagen excede 2 MB.";
        String ct = part.getContentType();
        if(!ALLOWED.contains(ct)) return "Formato no permitido (solo JPG/PNG/WEBP).";
        return null;
    }
}