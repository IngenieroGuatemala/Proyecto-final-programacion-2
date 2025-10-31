package com.umg.app.service;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NIT = Pattern.compile("^[0-9]{7,12}$");
    private static final Pattern TEL = Pattern.compile("^(?:\\+502)?[0-9]{8}$");
    private static final Pattern EMAIL = Pattern.compile("^[^@\n\r ]+@[^@\n\r ]+\\.[^@\n\r ]+$");

    public static void require(boolean cond, String msg){ if(!cond) throw new ValidationException(msg); }
    public static void nit(String nit){ require(nit!=null && NIT.matcher(nit).matches(), "NIT inválido (7–12 dígitos)."); }
    public static void telefono(String tel){ require(tel!=null && TEL.matcher(tel).matches(), "Teléfono inválido (+502 o 8 dígitos)."); }
    public static void email(String mail){ require(mail!=null && EMAIL.matcher(mail).matches(), "Correo inválido."); }
    public static void required(String v, String campo){ require(v!=null && !v.isBlank(), campo+" es requerido."); }
}
