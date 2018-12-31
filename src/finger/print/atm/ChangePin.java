package finger.print.atm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class ChangePin extends javax.swing.JFrame {
    public String oldPass;
    public String ID;
    public ChangePin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        BAck = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Enter Old Pin");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 111, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Enter New Pin");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 178, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Re-Enter New Pin");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 245, -1, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 333, -1, -1));

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 109, 199, -1));
        getContentPane().add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 176, 199, -1));
        getContentPane().add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 245, 199, -1));

        BAck.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BAck.setText("Cancel");
        BAck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAckActionPerformed(evt);
            }
        });
        getContentPane().add(BAck, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 333, -1, -1));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 334, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank.jpeg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ID=LogIn.Card_No;
        char ch[]=jPasswordField1.getPassword();
        oldPass=new String(ch);
        
        char ch1[]=jPasswordField2.getPassword();
        Pattern digitPattern = Pattern.compile("\\d\\d\\d\\d\\d");
        String newPass=new String(ch1);
        if(digitPattern.matcher(newPass).matches()){
            JOptionPane.showMessageDialog(this,"Please enter a 5 digit number");
        }
        char ch2[]=jPasswordField3.getPassword();
        String reNewPass=new String(ch2);
        if(digitPattern.matcher(newPass).matches()){
            JOptionPane.showMessageDialog(this,"Please enter a 5 digit number");
        }
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","root");
            PreparedStatement   st=connection.prepareStatement("select * from account where id=? and password=? ");
            st.setString(1,ID);
            st.setString(2,oldPass);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {

                if(newPass.equals(reNewPass))
                {
                    PreparedStatement   st1=connection.prepareStatement("update account set password=? where id=?");
                    st1.setString(1,newPass);
                    st1.setString(2,ID);
                    st1.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Pin Changed Successfully !!");
                    new LogIn().setVisible(true);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Sorry! Re-Enter the New Password");
                    jPasswordField2.setText("");
                    jPasswordField3.setText("");
                }

            }

        }catch(Exception ex)      {}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void BAckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAckActionPerformed
        // TODO add your handling code here:
        new LogIn().setVisible(true);
        dispose();
    }//GEN-LAST:event_BAckActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jPasswordField3.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
        if(!LogIn.pass.equals(oldPass))
        {
            JOptionPane.showMessageDialog(this,"Invalid Password!! Re-enter");
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangePin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAck;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    // End of variables declaration//GEN-END:variables
}
