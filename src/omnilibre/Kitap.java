package omnilibre;

import java.sql.*;
import java.util.ArrayList;

public class Kitap extends Materyal implements IVeritabani {
    
    private String tur;
    private int sayfaSayisi;

    public Kitap() {
        super(0, "", "");
    }

    public Kitap(int id, String ad, String yazar, String tur, int sayfaSayisi) {
        super(id, ad, yazar);
        this.tur = tur;
        this.sayfaSayisi = sayfaSayisi;
    }

    @Override
    public String getOzetBilgi() {
        return getAd() + " - " + getYazar();
    }
    
    public String getTur() { return tur; }
    public int getSayfaSayisi() { return sayfaSayisi; }

    // --- VERİTABANI İŞLEMLERİ ---

    private Connection baglan() {
        try 
        {
            return DriverManager.getConnection("jdbc:sqlite:kutuphane.db");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void ekle() {
        try {
            Connection conn = baglan();
            String sql = "INSERT INTO kitaplar (ad, yazar, tur, sayfa) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, getAd());
            pstmt.setString(2, getYazar());
            pstmt.setString(3, this.tur);
            pstmt.setInt(4, this.sayfaSayisi);
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void sil() {
        try {
            Connection conn = baglan();
            String sql = "DELETE FROM kitaplar WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public ArrayList<Kitap> listele() {
        ArrayList<Kitap> liste = new ArrayList<>();
        try {
            Connection conn = baglan();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kitaplar");
            
            while (rs.next()) {
                Kitap k = new Kitap(
                    rs.getInt("id"),
                    rs.getString("ad"),
                    rs.getString("yazar"),
                    rs.getString("tur"),
                    rs.getInt("sayfa")
                );
                liste.add(k);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return liste;
    }
    
    public void tabloOlustur() {
        try {
            Connection conn = baglan();
            String sql = "CREATE TABLE IF NOT EXISTS kitaplar (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "ad TEXT, yazar TEXT, tur TEXT, sayfa INTEGER)";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
