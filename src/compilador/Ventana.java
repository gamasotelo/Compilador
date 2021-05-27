
package compilador;

import java.util.Vector;

public class Ventana extends javax.swing.JFrame {
     
    public Ventana() {
        initComponents();
        setTitle("Compilador");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_texto = new javax.swing.JTextArea();
        panel_botones = new javax.swing.JPanel();
        boton_limpiar = new javax.swing.JButton();
        boton_compilar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        resultado = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area_texto.setColumns(20);
        area_texto.setRows(5);
        jScrollPane1.setViewportView(area_texto);

        boton_limpiar.setText("Limpiar");
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });
        panel_botones.add(boton_limpiar);

        boton_compilar.setText("Compilar");
        boton_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_compilarActionPerformed(evt);
            }
        });
        panel_botones.add(boton_compilar);

        jPanel2.setLayout(new java.awt.GridLayout());

        resultado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        resultado.setText("     ");
        jPanel2.add(resultado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_botones, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        area_texto.setText("");
        resultado.setText("");
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void muestraErrores(Vector<String> error){
        Vector mostrar = new Vector();
        for (int i = 0; i < error.size(); i++) {
            String[] aux = (error.get(i)).split(",");
            if(mostrar.isEmpty()){
                mostrar.add(aux[0]);
            }else{
                for (int j = 0; j < aux.length; j++) {
                    boolean existe = false;
                    for (int k = 0; k < mostrar.size(); k++) {
                        if(aux[j].equals(mostrar.get(k))){
                            existe = true;
                        }
                    }
                     if(!existe){
                            mostrar.add(aux[j]);
                        }
                }
            }
        }
        if(mostrar.size()>1){
            mostrar.removeElement("Compilación correcta");
        }
        
        String imprimir = "";
        for (int i = 0; i < mostrar.size(); i++) {
            if(imprimir.equals("")){
                imprimir = mostrar.get(i).toString();
            }else{
                imprimir = imprimir + ", " + mostrar.get(i);
            }
        }
        resultado.setText("");
        resultado.setText(imprimir);
    }
    
    private void boton_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_compilarActionPerformed

        String cadena = area_texto.getText();
        String[] lineas = cadena.split("\n");
        Comprobaciones accion = new Comprobaciones();
        Vector<String> errores = new Vector<String>();
        
        //Se clasifican las lineas de codigo
        for (int i = 0; i < lineas.length; i++) {
            if(lineas[i].equals("")){
            
            }else if(lineas[i].contains("=") && (lineas[i].contains("+") || lineas[i].contains("*") || lineas[i].contains("-") || lineas[i].contains("/"))){
                errores.add( accion.ValidarOperaciones(lineas[i].toString()));
            }else if(lineas[i].contains("=")){
                errores.add(accion.validarAsignarValor(lineas[i].toString()));
            }else{
                errores.add(accion.validarDeclaracion(lineas[i].toString()));
            }
            
        }
        muestraErrores(errores);
    }//GEN-LAST:event_boton_compilarActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_texto;
    private javax.swing.JButton boton_compilar;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JLabel resultado;
    // End of variables declaration//GEN-END:variables
}
