package com.example.student.phamvanduy;

public class Book {
    private int Id;
    private String TenSach;
    private String Noidung;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }




    Book(){
        this.Id=0;
        this.TenSach="";
        this.Noidung="";
    }
   public Book(int id, String tenSach, String noidung) {
        Id = id;
        TenSach = tenSach;
        Noidung = noidung;
    }


   /* public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getTenSach() {
        return TenSach;
    }
    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNoidung() {
        return Noidung;
    }
    public void setNoidung(String noidung) {
        Noidung = noidung;
    }*/

}
