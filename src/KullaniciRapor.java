
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class KullaniciRapor extends javax.swing.JFrame {

    String username;

    public KullaniciRapor(String username) {
        this.username = username;
        initComponents();
        generateReport();
        txtReport.setEditable(false);

        // Ekranı 2'ye bölelim: Sol = jPanel1, Sağ = panelSag
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));
        getContentPane().add(jPanel1);
        getContentPane().add(panelSag);

    }

    private void generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Aylık Finansal Rapor ---\n");
        sb.append("Kullanıcı: ").append(username).append("\n\n");

        double totalIncome = 0, totalExpense = 0;

        try (Connection conn = DBHelper.getConnection()) {
            String query = "SELECT type, category, amount, currency FROM transactions t "
                    + "JOIN users u ON t.user_id = u.id "
                    + "WHERE u.username = ? AND MONTH(date) = MONTH(CURRENT_DATE())";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String currency = rs.getString("currency");

                // Güncel döviz kurları
                double usdToTry = 38.79;
                double eurToTry = 43.39;
                double amountInTRY = amount;  //Dönüşümler yapılır.

                switch (currency) {
                    case "USD":
                        amountInTRY = amount * usdToTry;
                        break;
                    case "EURO":
                        amountInTRY = amount * eurToTry;
                        break;
                    case "TRY":
                    default:
                        // amountInTRY zaten TL 
                        break;
                }

                sb.append(String.format("%s - %s: %.2f %s (%.2f TL)\n", type, category, amount, currency, amountInTRY));

                if (type.equals("Income")) {
                    totalIncome += amountInTRY;
                } else if (type.equals("Expense")) {
                    totalExpense += amountInTRY;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
            ex.printStackTrace();
            return;
        }

        sb.append(String.format("\nToplam Gelir: %.2f TL\n", totalIncome));
        sb.append(String.format("Toplam Gider: %.2f TL\n", totalExpense));
        sb.append(String.format("Net Bakiye: %.2f TL", (totalIncome - totalExpense)));

        txtReport.setText(sb.toString());
        generateChart(totalIncome, totalExpense);

    }

    private void generateChart(double income, double expense) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Gelir", income);
        dataset.setValue("Gider", expense);

        JFreeChart chart = ChartFactory.createPieChart(
                "Gelir vs Gider Oranı",
                dataset, 
                true, 
                true, 
                false 
        );

        chart.setTitle(new org.jfree.chart.title.TextTitle("Gelir vs Gider Oranı",
                new java.awt.Font("Serif", java.awt.Font.PLAIN, 16)));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));

        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);  //pasta dilimlerinin arkasını da beyaz yapalım.
        chart.getLegend().setBackgroundPaint(Color.WHITE);  //açıklama kutusunu da beyaz yapalım.

        panelSag.setLayout(new BorderLayout());
        panelSag.add(chartPanel, BorderLayout.CENTER);

        panelSag.revalidate();  //layout bilgisi güncellenir
        panelSag.repaint();  //panel tekrardan cizilir.grafik anında gözükür.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReport = new javax.swing.JTextArea();
        raporKaydet = new javax.swing.JButton();
        panelSag = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        acikMod = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        koyuMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        varsayilanGorunum = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        islemlereDon = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cikis = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kullanıcı-FinansalRapor");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(29, 157, 157));

        jLabel1.setBackground(new java.awt.Color(59, 59, 236));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KULLANICI FİNANSAL RAPOR");

        txtReport.setColumns(20);
        txtReport.setRows(5);
        jScrollPane1.setViewportView(txtReport);

        raporKaydet.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        raporKaydet.setForeground(new java.awt.Color(51, 51, 255));
        raporKaydet.setText("Raporu Masaüstüne Kaydet");
        raporKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raporKaydetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(raporKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(raporKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelSag.setBackground(new java.awt.Color(231, 249, 255));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/emojione--bar-chart.png"))); // NOI18N

        javax.swing.GroupLayout panelSagLayout = new javax.swing.GroupLayout(panelSag);
        panelSag.setLayout(panelSagLayout);
        panelSagLayout.setHorizontalGroup(
            panelSagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSagLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addContainerGap(716, Short.MAX_VALUE))
        );
        panelSagLayout.setVerticalGroup(
            panelSagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSagLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addContainerGap(545, Short.MAX_VALUE))
        );

        getContentPane().add(panelSag, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 0, 850, 679));

        jMenu2.setText("Görünüm");

        acikMod.setText("Açık Mod");
        acikMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acikModActionPerformed(evt);
            }
        });
        jMenu2.add(acikMod);
        jMenu2.add(jSeparator1);

        koyuMod.setText("Koyu Mod");
        koyuMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koyuModActionPerformed(evt);
            }
        });
        jMenu2.add(koyuMod);
        jMenu2.add(jSeparator2);

        varsayilanGorunum.setText("Varsayılan Görünüm");
        varsayilanGorunum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varsayilanGorunumActionPerformed(evt);
            }
        });
        jMenu2.add(varsayilanGorunum);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Çıkış");

        islemlereDon.setText("İşlemler Sayfasına Dön");
        islemlereDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                islemlereDonActionPerformed(evt);
            }
        });
        jMenu1.add(islemlereDon);
        jMenu1.add(jSeparator3);

        cikis.setText("Çıkış Yap");
        cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisActionPerformed(evt);
            }
        });
        jMenu1.add(cikis);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void raporKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raporKaydetActionPerformed
        // TODO add your handling code here:
        try {
            String userHome = System.getProperty("user.home");
            File file = new File(userHome + "/Desktop/kullanici_raporu_" + username + ".txt");
            FileWriter writer = new FileWriter(file);
            writer.write(txtReport.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "Rapor masaüstüne kaydedildi.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "İşlem gerçekleştirilemedi. Lütfen tekrar deneyin.");
        }
    }//GEN-LAST:event_raporKaydetActionPerformed

    private void islemlereDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_islemlereDonActionPerformed
        // TODO add your handling code here:

        new UserPanel(username).setVisible(true);
        this.dispose();


    }//GEN-LAST:event_islemlereDonActionPerformed

    private void cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cikisActionPerformed

    private void acikModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acikModActionPerformed
        // TODO add your handling code here:

        jPanel1.setBackground(new Color(230, 255, 255));
        txtReport.setBackground(Color.WHITE);
        txtReport.setForeground(Color.BLACK);
        panelSag.setBackground(new Color(231, 249, 255));
        jLabel1.setForeground(new Color(0, 0, 139));

    }//GEN-LAST:event_acikModActionPerformed

    private void koyuModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koyuModActionPerformed
        // TODO add your handling code here:

        jLabel1.setForeground(Color.WHITE);
        txtReport.setBackground(Color.BLACK);
        txtReport.setForeground(Color.WHITE);
        panelSag.setBackground(Color.BLACK);
        jPanel1.setBackground(Color.BLACK);
    }//GEN-LAST:event_koyuModActionPerformed

    private void varsayilanGorunumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varsayilanGorunumActionPerformed
        // TODO add your handling code here:
        new KullaniciRapor(username).setVisible(true);
        dispose();
    }//GEN-LAST:event_varsayilanGorunumActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acikMod;
    private javax.swing.JMenuItem cikis;
    private javax.swing.JMenuItem islemlereDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem koyuMod;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JPanel panelSag;
    private javax.swing.JButton raporKaydet;
    private javax.swing.JTextArea txtReport;
    private javax.swing.JMenuItem varsayilanGorunum;
    // End of variables declaration//GEN-END:variables
}
