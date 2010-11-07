/*
 *  The MIT License
 * 
 *  Copyright 2010 Georgios Migdos <cyberpython@gmail.com>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

/*
 * JProcedureRenderer.java
 *
 * Created on Nov 6, 2010, 5:31:58 PM
 */

package glossa.ui.stackrenderer.components;

import glossa.interpreter.symboltable.ProcedureSymbolTable;
import glossa.interpreter.symboltable.symbols.RuntimeArray;
import glossa.interpreter.symboltable.symbols.RuntimeConstant;
import glossa.interpreter.symboltable.symbols.RuntimeSymbol;
import glossa.interpreter.symboltable.symbols.RuntimeVariable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class JProcedureRenderer extends JProgramPartRenderer {

    private ProcedureSymbolTable symboltable;

    /** Creates new form JProcedureRenderer */
    public JProcedureRenderer(ProcedureSymbolTable symboltable) {
        super(new Color(204, 255, 170), new Color(68, 170, 0));
        this.symboltable = symboltable;
        initComponents();
        this.jLabel1.setText("ΔΙΑΔΙΚΑΣΙΑ "+symboltable.getName());

        List<RuntimeSymbol> vars = new ArrayList<RuntimeSymbol>();
        List<RuntimeConstant> consts = new ArrayList<RuntimeConstant>();

        HashMap<String, RuntimeSymbol> symbols = symboltable.getSymbols();
        for (Iterator<String> it = symbols.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            RuntimeSymbol symbol = symbols.get(key);
            if(symbol instanceof RuntimeConstant){
                consts.add((RuntimeConstant)symbol);
            }else if( (symbol instanceof RuntimeVariable) || (symbol instanceof RuntimeArray) ){
                vars.add(symbol);
            }
        }

        this.jVariablesRenderer1.setVariables(vars);
        this.jConstantsRenderer1.setConstants(consts);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jStackElementUnitPanel1 = new glossa.ui.stackrenderer.components.JProgramPartElement();
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jVariablesRenderer1 = new glossa.ui.stackrenderer.components.JVariablesRenderer();
        jConstantsRenderer1 = new glossa.ui.stackrenderer.components.JConstantsRenderer();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(269, 425));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ΔΙΑΔΙΚΑΣΙΑ");

        javax.swing.GroupLayout jStackElementUnitPanel1Layout = new javax.swing.GroupLayout(jStackElementUnitPanel1);
        jStackElementUnitPanel1.setLayout(jStackElementUnitPanel1Layout);
        jStackElementUnitPanel1Layout.setHorizontalGroup(
            jStackElementUnitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStackElementUnitPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
        jStackElementUnitPanel1Layout.setVerticalGroup(
            jStackElementUnitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jSplitPane1.setDividerLocation(145);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setRightComponent(jVariablesRenderer1);
        jSplitPane1.setLeftComponent(jConstantsRenderer1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jStackElementUnitPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jStackElementUnitPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private glossa.ui.stackrenderer.components.JConstantsRenderer jConstantsRenderer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private glossa.ui.stackrenderer.components.JProgramPartElement jStackElementUnitPanel1;
    private glossa.ui.stackrenderer.components.JVariablesRenderer jVariablesRenderer1;
    // End of variables declaration//GEN-END:variables

}
