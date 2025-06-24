
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class KayitOlPanel extends javax.swing.JFrame {

    public KayitOlPanel() {
        initComponents();

        kayitOl.setEnabled(false);

        chkKvkkOnay.addActionListener(e -> {
            if (chkKvkkOnay.isSelected()) {
                String kvkkText = """
                KİŞİSEL VERİLERİN KORUNMASI KANUNU (KVKK)

                Bu sistemde verdiğiniz bilgiler yalnızca kullanıcı kimliğinizin doğrulanması,
                veri güvenliğinin sağlanması ve yasal yükümlülüklerin yerine getirilmesi amacıyla kullanılacaktır.

                Verileriniz hiçbir üçüncü tarafla paylaşılmayacak ve KVKK kapsamında güvenle saklanacaktır.
                """;

                JOptionPane.showMessageDialog(null, kvkkText, "KVKK Bilgilendirmesi", JOptionPane.INFORMATION_MESSAGE);

            }

            kayitOl.setEnabled(chkKvkkOnay.isSelected());
        });

        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);  
            } else {
                txtPassword.setEchoChar('*');  //eğer showPassword seçili değilse parolanın her birisi * olarak gözükür
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() { //Olayların algınlanması için listener ekledik.
            @Override
            public void keyReleased(KeyEvent evt) { //tus BIRAKILDIGINDA tetiklenir.
                String password = new String(txtPassword.getPassword());

                if (password.length() < 6) { //kullanıcının girdiği passwordun uzunlugu kontrol edilir.
                    jLabelStrength.setText("Şifre Güvenliği: ZAYIF"); //eğer şifrenin uzunluğu 6dan küçükse labelin rengi KIRMIZI olarak kalır.
                    jLabelStrength.setForeground(Color.RED);
                } else if (password.matches(".*[A-Z].*") && password.matches(".*[!?.:_;\\-+*%$#@].*")) { 
                    jLabelStrength.setText("Şifre Güvenliği: GÜÇLÜ"); //eğer şifrede herhangi bir özel karakter varsa ve en az bir büyük harf varsa labelin rengi yesil olur.
                    jLabelStrength.setForeground(Color.GREEN);
                } else {
                    jLabelStrength.setText("Şifre Güvenliği: ORTA"); //şifrenin uzunlugu eğer 6 dan büyük ama herhangi bir büyük harf veya özel karakter içermiyorsa labelin rengi TURUNCU olur
                    jLabelStrength.setForeground(Color.ORANGE);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chkKvkkOnay = new javax.swing.JCheckBox();
        kayitOl = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabelStrength = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        giriseDon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Muhasebe Yönetim Sistemi-KayitOl");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 660, -1, -1));

        jPanel1.setBackground(new java.awt.Color(132, 193, 193));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KULLANICI KAYIT EKRANI");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(205, 88, 379, 29);
        jPanel1.add(txtUsername);
        txtUsername.setBounds(396, 174, 179, 36);

        jLabel2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kullanıcı adı:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(222, 186, 93, 24);

        jLabel3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sifre:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(222, 278, 38, 24);

        chkKvkkOnay.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        chkKvkkOnay.setForeground(new java.awt.Color(255, 255, 255));
        chkKvkkOnay.setText(" Kişisel Verilerin Koruma Kanunu(KVKK) onaylıyorum.");
        jPanel1.add(chkKvkkOnay);
        chkKvkkOnay.setBounds(236, 417, 339, 21);

        kayitOl.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        kayitOl.setText("Kayıt Ol");
        kayitOl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kayitOlActionPerformed(evt);
            }
        });
        jPanel1.add(kayitOl);
        kayitOl.setBounds(421, 466, 154, 46);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(798, 526, 0, 0);

        jLabelStrength.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabelStrength.setText("   ");
        jPanel1.add(jLabelStrength);
        jLabelStrength.setBounds(580, 480, 172, 19);
        jPanel1.add(txtPassword);
        txtPassword.setBounds(396, 274, 179, 36);

        chkShowPassword.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        chkShowPassword.setForeground(new java.awt.Color(255, 255, 255));
        chkShowPassword.setText("Sifremi göster");
        jPanel1.add(chkShowPassword);
        chkShowPassword.setBounds(463, 370, 112, 29);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mingcute--user-add-line (2).png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(15, 107, 128, 128);

        giriseDon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        giriseDon.setText("Giris Ekranına Dön");
        giriseDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giriseDonActionPerformed(evt);
            }
        });
        jPanel1.add(giriseDon);
        giriseDon.setBounds(421, 538, 154, 47);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 849, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kayitOlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kayitOlActionPerformed
        // TODO add your handling code here:

        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) { //trim methoduyla boslukları sildikten sonra isEmpty methoduyla kullanıcının bosluk girip girmedigi kontrol edilir.
            JOptionPane.showMessageDialog(this, "Tüm alanlar doldurulmalı!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return; //kullanıcıya mesaj bastırıldıktan sonra return ile methoddan çıkılıyor.
        }

        String regex = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{6,}$"; //şifre güçlülüğü için regex tanımlanır.
        if (!password.matches(regex)) {//matches methodu ile password un uygunlugu kontrol edilir.eğer uyuyorsa if den true döner.Eğer uymuyorsa ! dan dolayı orası true döner ve içeri girip WARNING MESSAGE yazılır.
            JOptionPane.showMessageDialog(this,
                    "Şifre en az 6 karakterli olmalı, en az 1 büyük harf ve 1 sembol içermelidir.",
                    "Şifre Uyarısı", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!username.matches("^[a-zA-Z0-9]{3,15}$")) { //Aynısı username için yapılır.
            JOptionPane.showMessageDialog(this,
                    "Kullanıcı adı sadece harf ve rakam içerebilir (3-15 karakter arası).",
                    "Kullanıcı Adı Hatası", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBHelper.getConnection()) { //Database Connection bağlantısı yapılır
            PreparedStatement check = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            check.setString(1, username);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Bu kullanıcı adı zaten kayıtlı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'user')"; //SQL sorgusu yazılır.
            PreparedStatement stmt = conn.prepareStatement(sql); //SQL Injection ı engellemek için PreparedStatement objesi yazılır. 
            //PreparedStatement sorgulara parametre geçişi sağlar.
            stmt.setString(1, username); //1. soru işaretinin oldugu yere kullanıcının girdiği username atanır.
            stmt.setString(2, password); //2.soru isaretinin oldugu yere kullanıcının girdiği password atanır.

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı! Giriş ekranına yönlendiriliyorsunuz...");
                this.dispose();
                new GirisPanel().setVisible(true);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }//GEN-LAST:event_kayitOlActionPerformed

    private void giriseDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giriseDonActionPerformed
        // TODO add your handling code here:
        new GirisPanel().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_giriseDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkKvkkOnay;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JButton giriseDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelStrength;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JButton kayitOl;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
