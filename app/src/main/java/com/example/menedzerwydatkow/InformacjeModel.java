package com.example.menedzerwydatkow;

public class InformacjeModel {


    //INFORMACJE O WYDATKU/PRZYCHODZIE ZAPISYWANE JAKO KLASA
    private String idInformacji;
    private String kwota;

    private String rodzaj;
    private String informacja;
    private String kategoria;
    private String userID;


    //BEZPIECZNY KONSTRUKTOR
    public InformacjeModel() {
    }

    //PEŁNY KONSTRUKTOR - UTWORZY POPRAWNĄ INFORMACJE

    public InformacjeModel(String idInformacji, String kwota, String rodzaj, String informacja, String kategoria, String userID) {
        this.idInformacji = idInformacji;
        this.kwota = kwota;
        this.rodzaj = rodzaj;
        this.informacja = informacja;
        this.kategoria = kategoria;
        this.userID = userID;
    }


    //GETY SETY


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getIdInformacji() {
        return idInformacji;
    }

    public void setIdInformacji(String idInformacji) {
        this.idInformacji = idInformacji;
    }

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }


}
