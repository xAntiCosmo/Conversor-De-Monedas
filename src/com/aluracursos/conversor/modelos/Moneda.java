package com.aluracursos.conversor.modelos;

public class Moneda {
    private String Base;
    private String Target;
    private String Conversion;

    public Moneda(Conversor miConversor){
        this.Base = miConversor.base_code();
        this.Target = miConversor.target_code();
        this.Conversion = miConversor.conversion_rate();
    }

    public String getBase(){
        return Base;
    }

    public String getTarget(){
        return Target;
    }

    public String getResultado(){
        return Conversion;
    }

    @Override
    public String toString(){
        return "\nLa conversión de " + Base + " a " + Target + " es: " + Conversion + "\n";
    }

}
