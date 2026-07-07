package omnilibre;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class AnaEkran extends JFrame {

    private JTextField txtAd, txtYazar, txtTur, txtSayfa;
    private JTable table;
    private DefaultTableModel model;
    private Kitap kitapIslemleri = new Kitap();
    private int seciliId = -1;

    public static void main(String[] args) {
        new Kitap().tabloOlustur();
        
        SwingUtilities.invokeLater(() -> {
            try {
                AnaEkran frame = new AnaEkran();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AnaEkran() {
        setTitle("OmniLibre Kütüphane Yönetim Sistemi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        getContentPane().setLayout(null);

        // --- SOL TARAF (TABLO) ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 350, 390);
        getContentPane().add(scrollPane);
        getContentPane().setBackground(new Color(173, 216, 230));


        table = new JTable();
        table.setBackground(new Color(171, 170, 206));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Kitap Adı", "Yazar", "Tür", "Sayfa"});
        table.setModel(model);
        scrollPane.setViewportView(table);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                seciliId = (int) model.getValueAt(row, 0); // ID'yi al
                txtAd.setText(model.getValueAt(row, 1).toString());
                txtYazar.setText(model.getValueAt(row, 2).toString());
                txtTur.setText(model.getValueAt(row, 3).toString());
                txtSayfa.setText(model.getValueAt(row, 4).toString());
            }
        });

        // --- SAĞ TARAF (GİRİŞ KUTULARI) ---
        JLabel lbl1 = new JLabel("Kitap Adı:");
        lbl1.setBounds(370, 20, 100, 14);
        getContentPane().add(lbl1);

        txtAd = new JTextField();
        txtAd.setBounds(370, 40, 200, 25);
        getContentPane().add(txtAd);

        JLabel lbl2 = new JLabel("Yazar:");
        lbl2.setBounds(370, 75, 100, 14);
        getContentPane().add(lbl2);

        txtYazar = new JTextField();
        txtYazar.setBounds(370, 95, 200, 25);
        getContentPane().add(txtYazar);
        
        JLabel lbl3 = new JLabel("Tür:");
        lbl3.setBounds(370, 130, 100, 14);
        getContentPane().add(lbl3);

        txtTur = new JTextField();
        txtTur.setBounds(370, 150, 200, 25);
        getContentPane().add(txtTur);
        
        JLabel lbl4 = new JLabel("Sayfa Sayısı:");
        lbl4.setBounds(370, 185, 100, 14);
        getContentPane().add(lbl4);

        txtSayfa = new JTextField();
        txtSayfa.setBounds(370, 205, 200, 25);
        getContentPane().add(txtSayfa);

        // --- BUTONLAR ---
        JButton btnEkle = new JButton("EKLE");
        btnEkle.setBounds(370, 250, 90, 30);
        getContentPane().add(btnEkle);
        
        JButton btnSil = new JButton("SİL");
        btnSil.setBounds(480, 250, 90, 30);
        getContentPane().add(btnSil);

        // --- BUTON İŞLEVLERİ ---
        
        // EKLE BUTONU
        btnEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ad = txtAd.getText();
                String yazar = txtYazar.getText();
                String tur = txtTur.getText();
                int sayfa = Integer.parseInt(txtSayfa.getText());
                
                Kitap yeniKitap = new Kitap(0, ad, yazar, tur, sayfa);
                yeniKitap.ekle();
                
                listeyiYenile();
                kutulariTemizle();
                JOptionPane.showMessageDialog(null, "Kitap Eklendi!");
            }
        });

        // SİL BUTONU
        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(seciliId == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen tablodan bir kitap seçin!");
                    return;
                }
                
                Kitap silinecekKitap = new Kitap();
                silinecekKitap.setId(seciliId);
                silinecekKitap.sil();
                
                listeyiYenile();
                kutulariTemizle();
                JOptionPane.showMessageDialog(null, "Kitap Silindi!");
            }
        });

        listeyiYenile();
    }
    
    public void listeyiYenile() {
        model.setRowCount(0);
        ArrayList<Kitap> kitaplar = kitapIslemleri.listele();
        
        for(Kitap k : kitaplar) {
            Object[] satir = {k.getId(), k.getAd(), k.getYazar(), k.getTur(), k.getSayfaSayisi()};
            model.addRow(satir);
        }
    }
    
    public void kutulariTemizle() {
        txtAd.setText(""); txtYazar.setText(""); txtTur.setText(""); txtSayfa.setText("");
        seciliId = -1;
    }
}