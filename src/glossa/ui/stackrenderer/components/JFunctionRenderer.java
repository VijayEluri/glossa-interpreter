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

import glossa.interpreter.core.InterpreterUtils;
import glossa.interpreter.symboltable.FunctionSymbolTable;
import glossa.interpreter.symboltable.symbols.RuntimeArray;
import glossa.interpreter.symboltable.symbols.RuntimeConstant;
import glossa.interpreter.symboltable.symbols.RuntimeSymbol;
import glossa.interpreter.symboltable.symbols.RuntimeVariable;
import glossa.messages.Messages;
import glossa.statictypeanalysis.scopetable.scopes.FunctionScope;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class JFunctionRenderer extends JProgramPartRenderer {

    private FunctionSymbolTable symboltable;

    /** Creates new form JProcedureRenderer */
    public JFunctionRenderer(FunctionSymbolTable symboltable) {
        super(new Color(255, 230, 128), new Color(255, 204, 0));
        this.symboltable = symboltable;
        initComponents();
        this.jLabel1.setText("ΣΥΝΑΡΤΗΣΗ "+symboltable.getName());
        this.jLabel6.setText(Messages.typeToString(((FunctionScope)symboltable.getSubprogramScope()).getReturnType()));
        this.jLabel5.setText(InterpreterUtils.toPrintableString(symboltable.getReturnValue()));

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

    public void updateReturnValue(){
        this.jLabel5.setText(InterpreterUtils.toPrintableString(symboltable.getReturnValue()));
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
        jStackElementUnitPanel2 = new glossa.ui.stackrenderer.components.JProgramPartElement();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jVariablesRenderer1 = new glossa.ui.stackrenderer.components.JVariablesRenderer();
        jConstantsRenderer1 = new glossa.ui.stackrenderer.components.JConstantsRenderer();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(269, 425));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ΣΥΝΑΡΤΗΣΗ");

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

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ΤΙΜΗ ΕΠΙΣΤΡΟΦΗΣ");

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel3.setText("Τιμή:");

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel4.setText("Τύπος:");

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() & ~java.awt.Font.BOLD, 10));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() & ~java.awt.Font.BOLD, 10));

        javax.swing.GroupLayout jStackElementUnitPanel2Layout = new javax.swing.GroupLayout(jStackElementUnitPanel2);
        jStackElementUnitPanel2.setLayout(jStackElementUnitPanel2Layout);
        jStackElementUnitPanel2Layout.setHorizontalGroup(
            jStackElementUnitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStackElementUnitPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jStackElementUnitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jStackElementUnitPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGroup(jStackElementUnitPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jStackElementUnitPanel2Layout.setVerticalGroup(
            jStackElementUnitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStackElementUnitPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jStackElementUnitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jStackElementUnitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setDividerLocation(145);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setRightComponent(jVariablesRenderer1);
        jSplitPane1.setTopComponent(jConstantsRenderer1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jStackElementUnitPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jStackElementUnitPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jStackElementUnitPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jStackElementUnitPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private glossa.ui.stackrenderer.components.JConstantsRenderer jConstantsRenderer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSplitPane jSplitPane1;
    private glossa.ui.stackrenderer.components.JProgramPartElement jStackElementUnitPanel1;
    private glossa.ui.stackrenderer.components.JProgramPartElement jStackElementUnitPanel2;
    private glossa.ui.stackrenderer.components.JVariablesRenderer jVariablesRenderer1;
    // End of variables declaration//GEN-END:variables

}
