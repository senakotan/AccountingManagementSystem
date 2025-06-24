
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class AdminRaporlar extends javax.swing.JFrame {

    private String username;

    public AdminRaporlar(String username) {
        initComponents();
        this.username = username;
        loadUsersWithTransactions();

        jTabbedPane1.setSelectedIndex(2);
        jTextArea1.setEditable(false);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int selectedIndex = jTabbedPane1.getSelectedIndex();
                String selectedTitle = jTabbedPane1.getTitleAt(selectedIndex);

                if (selectedTitle.equals("Kullanıcılar")) {
                    new AdminPanel(username).setVisible(true);
                    dispose();
                } else if (selectedTitle.equals("İslemler")) {
                    new AdminIslemler(username).setVisible(true);
                    dispose();
                } else if (selectedTitle.equals("Raporlar")) {
                    new AdminRaporlar(username).setVisible(true);
                    dispose();
                }
            }
        });

    }

    private void loadUsersWithTransactions() {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = """
            SELECT DISTINCT u.username FROM users u
            WHERE u.id IN (SELECT DISTINCT user_id FROM transactions)
        """;  

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            kullanicilar.removeAllItems();
            while (rs.next()) {
                kullanicilar.addItem(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kullanıcılar yüklenirken hata oluştu.");
        }
    }

    private void generateReportForUser(String selectedUser) {
        double totalIncome = 0, totalExpense = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("--- Aylık Finansal Rapor ---\n");
        sb.append("Kullanıcı: ").append(selectedUser).append("\n\n");

        try (Connection conn = DBHelper.getConnection()) {
            String query = """
        SELECT t.type, t.category, t.amount, t.currency
        FROM transactions t
        JOIN users u ON t.user_id = u.id
        WHERE u.username = ?
          AND MONTH(t.date) = MONTH(CURRENT_DATE())
        """;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, selectedUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String currency = rs.getString("currency");

                double amountInTRY;
                double usdToTry = 38.79;
                double eurToTry = 43.39;

                switch (currency) {
                    case "USD":
                        amountInTRY = amount * usdToTry;
                        break;
                    case "EURO":
                        amountInTRY = amount * eurToTry;
                        break;
                    default:
                        amountInTRY = amount;
                        break;
                }

                sb.append(String.format("%s - %s: %.2f %s (%.2f TL)\n", type, category, amount, currency, amountInTRY));

                if (type.equalsIgnoreCase("Income")) {
                    totalIncome += amountInTRY;
                } else if (type.equalsIgnoreCase("Expense")) {
                    totalExpense += amountInTRY;
                }
            }

            double net = totalIncome - totalExpense;

            sb.append("\n");
            sb.append(String.format("Toplam Gelir: %.2f TL\n", totalIncome));
            sb.append(String.format("Toplam Gider: %.2f TL\n", totalExpense));
            sb.append(String.format("Net Bakiye: %.2f TL\n", net));

            jTextArea1.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            jTextArea1.setText("Rapor oluşturulurken veritabanı hatası oluştu.");
        }

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

        int chartWidth = 420;
        int chartHeight = 420;

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(chartWidth, chartHeight));

        chartPanel.setBackground(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        chart.getLegend().setBackgroundPaint(Color.WHITE);

        grafikPanel.removeAll();

        // Panel boyutları
        int panelWidth = grafikPanel.getWidth();
        int panelHeight = grafikPanel.getHeight();

        int x = (panelWidth - chartWidth) / 2;
        int y = (panelHeight - chartHeight) / 2;

        chartPanel.setBounds(x, y, chartWidth, chartHeight);
        grafikPanel.setLayout(null);
        grafikPanel.add(chartPanel);

        grafikPanel.revalidate();
        grafikPanel.repaint();
    }

    private void exportReportToDesktop() {
        try {
            String userHome = System.getProperty("user.home");
            File file = new File(userHome + "/Desktop/rapor.txt");

            FileWriter writer = new FileWriter(file);
            writer.write(jTextArea1.getText());
            writer.close();

            gosterMesaj("Rapor masaüstüne kaydedildi:\n");

        } catch (IOException e) {
            e.printStackTrace();
            gosterMesaj("\"Rapor dışa aktarılırken hata oluştu.\"");
        }
    }

    // Overloading Method1
    private void gosterMesaj(String mesaj) {
        JOptionPane.showMessageDialog(this, mesaj);
    }

    // Overloading Method2
    private void gosterMesaj(String mesaj, String kullanici) {
        JOptionPane.showMessageDialog(this, kullanici + ": " + mesaj);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ikinciPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        kullanicilar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        raporTxtAktar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        sagPanel = new javax.swing.JPanel();
        grafikPanel = new javax.swing.JPanel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        acikMod = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        koyuMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        varsayilanMod = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        giriseDon = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cikis = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        ikinciPanel.setBackground(new java.awt.Color(238, 220, 220));

        javax.swing.GroupLayout ikinciPanelLayout = new javax.swing.GroupLayout(ikinciPanel);
        ikinciPanel.setLayout(ikinciPanelLayout);
        ikinciPanelLayout.setHorizontalGroup(
            ikinciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 843, Short.MAX_VALUE)
        );
        ikinciPanelLayout.setVerticalGroup(
            ikinciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin-FinansalRaporlar");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(236, 186, 186));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(228, 241, 242));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("FINANSAL RAPORLAR");

        kullanicilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kullanicilarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Kullanıcılar:");

        raporTxtAktar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        raporTxtAktar.setForeground(new java.awt.Color(0, 102, 102));
        raporTxtAktar.setText("Raporu TXT Olarak Aktar");
        raporTxtAktar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raporTxtAktarActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tdesign--chart-combo.png"))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kullanıcılar", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("İslemler", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raporlar", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(48, 48, 48)
                                .addComponent(kullanicilar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))
                        .addComponent(jLabel3)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(raporTxtAktar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(kullanicilar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(raporTxtAktar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 840));

        sagPanel.setBackground(new java.awt.Color(237, 215, 215));
        sagPanel.setForeground(new java.awt.Color(239, 216, 216));
        sagPanel.setLayout(null);
        getContentPane().add(sagPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1564, 0, -1, -1));

        grafikPanel.setBackground(new java.awt.Color(241, 230, 230));

        javax.swing.GroupLayout grafikPanelLayout = new javax.swing.GroupLayout(grafikPanel);
        grafikPanel.setLayout(grafikPanelLayout);
        grafikPanelLayout.setHorizontalGroup(
            grafikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        grafikPanelLayout.setVerticalGroup(
            grafikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );

        getContentPane().add(grafikPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 800, 840));

        jMenu3.setText("Görünüm");

        acikMod.setText("Açık Mod");
        acikMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acikModActionPerformed(evt);
            }
        });
        jMenu3.add(acikMod);
        jMenu3.add(jSeparator1);

        koyuMod.setText("Koyu Mod");
        koyuMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koyuModActionPerformed(evt);
            }
        });
        jMenu3.add(koyuMod);
        jMenu3.add(jSeparator2);

        varsayilanMod.setText("Varsayılan Mod");
        varsayilanMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varsayilanModActionPerformed(evt);
            }
        });
        jMenu3.add(varsayilanMod);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Çıkış");

        giriseDon.setText("Giris Ekranına Dönüş");
        giriseDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giriseDonActionPerformed(evt);
            }
        });
        jMenu4.add(giriseDon);
        jMenu4.add(jSeparator3);

        cikis.setText("Sistemden Çıkış");
        cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisActionPerformed(evt);
            }
        });
        jMenu4.add(cikis);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kullanicilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kullanicilarActionPerformed
        // TODO add your handling code here:
        String selectedUser = (String) kullanicilar.getSelectedItem();
        if (selectedUser != null) {
            generateReportForUser(selectedUser);
        }
    }//GEN-LAST:event_kullanicilarActionPerformed

    private void raporTxtAktarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raporTxtAktarActionPerformed
        // TODO add your handling code here:
        exportReportToDesktop();
    }//GEN-LAST:event_raporTxtAktarActionPerformed

    private void acikModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acikModActionPerformed
        // TODO add your handling code here:
        jPanel2.setBackground(Color.WHITE);
        jTextArea1.setBackground(Color.WHITE);
        jTextArea1.setForeground(Color.BLACK);
        kullanicilar.setBackground(Color.WHITE);
        kullanicilar.setForeground(Color.BLACK);
        grafikPanel.setBackground(Color.WHITE);
    }//GEN-LAST:event_acikModActionPerformed

    private void koyuModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koyuModActionPerformed
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(45, 45, 45));
        jTextArea1.setBackground(new Color(60, 60, 60));
        jTextArea1.setForeground(Color.WHITE);
        kullanicilar.setBackground(new Color(80, 80, 80));
        kullanicilar.setForeground(Color.WHITE);
        grafikPanel.setBackground(new Color(45, 45, 45));
    }//GEN-LAST:event_koyuModActionPerformed

    private void varsayilanModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varsayilanModActionPerformed
        // TODO add your handling code here:
        new AdminRaporlar(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_varsayilanModActionPerformed

    private void giriseDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giriseDonActionPerformed
        // TODO add your handling code here:
        new GirisPanel().setVisible(true);
        dispose();
    }//GEN-LAST:event_giriseDonActionPerformed

    private void cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cikisActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acikMod;
    private javax.swing.JMenuItem cikis;
    private javax.swing.JMenuItem giriseDon;
    private javax.swing.JPanel grafikPanel;
    private javax.swing.JPanel ikinciPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem koyuMod;
    private javax.swing.JComboBox<String> kullanicilar;
    private javax.swing.JButton raporTxtAktar;
    private javax.swing.JPanel sagPanel;
    private javax.swing.JMenuItem varsayilanMod;
    // End of variables declaration//GEN-END:variables
}
